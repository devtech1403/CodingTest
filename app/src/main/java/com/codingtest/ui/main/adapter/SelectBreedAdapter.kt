package com.codingtest.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codingtest.data.model.DogBreed
import com.codingtest.databinding.BreedItemLayoutBinding
import com.codingtest.ui.main.interfaces.OnBreedSelectListener
import java.util.*

class SelectBreedAdapter(
    private val dogBreedList: ArrayList<DogBreed>,
    private val selectListener: OnBreedSelectListener
) : RecyclerView.Adapter<SelectBreedAdapter.DataViewHolder>() {

    inner class DataViewHolder(private val binding: BreedItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dogBreed: DogBreed, selectListener: OnBreedSelectListener) {
            binding.tvTitle.text = dogBreed.name
            binding.tvTitle.setOnClickListener {
                selectListener.onBreedSelect(dogBreed)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            BreedItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(dogBreedList[position], selectListener)

    override fun getItemCount(): Int = dogBreedList.size

    fun addData(list: List<DogBreed>) {
        dogBreedList.addAll(list)
    }
}