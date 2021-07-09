package com.codingtest.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.codingtest.data.api.ApiHelper
import com.codingtest.data.api.ApiServiceImpl
import com.codingtest.data.model.DogBreed
import com.codingtest.databinding.FragmentSelectBreedBinding
import com.codingtest.ui.base.BaseBottomSheetFragment
import com.codingtest.ui.base.ViewModelFactory
import com.codingtest.ui.main.adapter.SelectBreedAdapter
import com.codingtest.ui.main.interfaces.OnBreedSelectListener
import com.codingtest.ui.main.viewmodel.SelectBreedViewModel
import com.codingtest.utils.Status


class SelectBreedFragment : BaseBottomSheetFragment() {
    private lateinit var binding: FragmentSelectBreedBinding

    private lateinit var listener: OnBreedSelectListener
    private lateinit var selectBreedViewModel: SelectBreedViewModel
    private lateinit var adapter: SelectBreedAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSelectBreedBinding.inflate(inflater, container, false)
        selectBreedViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(ApiServiceImpl()))
        ).get(SelectBreedViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(binding.root.context)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )

        adapter = SelectBreedAdapter(arrayListOf(), object : OnBreedSelectListener {
            override fun onBreedSelect(dogBreed: DogBreed) {
                listener.onBreedSelect(dogBreed)
                dismiss()
            }
        })
        binding.recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        selectBreedViewModel.getDogsBreed().observe(this, {
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
                    Toast.makeText(binding.root.context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(dogsList: List<DogBreed>) {
        adapter.addData(dogsList)
        adapter.notifyDataSetChanged()
    }

    fun onItemSelect(listener: OnBreedSelectListener): SelectBreedFragment {
        this.listener = listener
        return this
    }
}