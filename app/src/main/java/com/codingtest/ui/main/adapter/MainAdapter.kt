package com.codingtest.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codingtest.data.model.Dog
import com.codingtest.databinding.MainItemLayoutBinding
import java.util.*

class MainAdapter(private val dogsList: ArrayList<Dog>) :
    RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    inner class DataViewHolder(private val binding: MainItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dog: Dog) {
            Glide.with(binding.imageViewAvatar)
                .load(dog.url)
                .into(binding.imageViewAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            MainItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(dogsList[position])


    override fun getItemCount(): Int = dogsList.size

    fun addData(list: List<Dog>) {
        dogsList.clear()
        dogsList.addAll(list)
    }
}