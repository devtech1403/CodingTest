package com.codingtest.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.codingtest.data.api.ApiHelper
import com.codingtest.data.api.ApiServiceImpl
import com.codingtest.data.model.Dog
import com.codingtest.data.model.DogBreed
import com.codingtest.databinding.ActivityMainBinding
import com.codingtest.ui.base.ViewModelFactory
import com.codingtest.ui.main.adapter.MainAdapter
import com.codingtest.ui.main.interfaces.OnBreedSelectListener
import com.codingtest.ui.main.viewmodel.MainViewModel
import com.codingtest.utils.Status

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding

    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        binding.recyclerView.layoutManager = GridLayoutManager(this, 3)
        adapter = MainAdapter(arrayListOf())
        binding.recyclerView.adapter = adapter

        binding.tvSelectBreed.setOnClickListener {
            SelectBreedFragment()
                .onItemSelect(object : OnBreedSelectListener {
                    override fun onBreedSelect(dogBreed: DogBreed) {
                        binding.tvSelectBreed.text = dogBreed.name
                        mainViewModel.fetchDogsBreedData(dogBreed.id)
                    }
                })
                .show(supportFragmentManager, TAG)
        }
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(ApiServiceImpl()))
        ).get(MainViewModel::class.java)
    }

    private fun setupObserver() {
        mainViewModel.getDogsBreedData().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { dogsList -> renderList(dogsList) }
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    //Handle Error
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(dogsList: List<Dog>) {
        adapter.addData(dogsList)
        adapter.notifyDataSetChanged()
    }
}
