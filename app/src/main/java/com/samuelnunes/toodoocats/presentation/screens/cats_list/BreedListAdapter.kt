package com.samuelnunes.toodoocats.presentation.screens.cats_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.samuelnunes.toodoocats.databinding.ItemCatBinding
import com.samuelnunes.toodoocats.domain.dto.BreedDTO
import com.samuelnunes.toodoocats.entity.Breed
import com.samuelnunes.toodoocats.presentation.extensions.visibilityIf

internal class BreedListAdapter(private val onBreedDTOClick: (Breed) -> Unit) :
    ListAdapter<Breed, BreedListAdapter.BreedViewHolder>(BreedItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCatBinding.inflate(layoutInflater, parent, false)
        return BreedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class BreedViewHolder(private val binding: ItemCatBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Breed) {
            binding.apply {
                breed = item
                btWikipedia.setOnClickListener { onBreedDTOClick(item) }
            }
        }
    }

    internal object BreedItemCallback : DiffUtil.ItemCallback<Breed>() {
        override fun areItemsTheSame(first: Breed, second: Breed): Boolean =
            first.id == second.id

        override fun areContentsTheSame(first: Breed, second: Breed): Boolean =
            first == second

    }

}