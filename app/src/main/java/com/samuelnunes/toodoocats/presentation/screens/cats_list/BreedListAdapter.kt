package com.samuelnunes.toodoocats.presentation.screens.cats_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.samuelnunes.toodoocats.databinding.ItemCatBinding
import com.samuelnunes.toodoocats.entity.remote.BreedDTO
import com.samuelnunes.toodoocats.presentation.extensions.visibilityIf

internal class BreedListAdapter(private val onBreedDTOClick: (BreedDTO) -> Unit) :
    ListAdapter<BreedDTO, BreedListAdapter.BreedDTOViewHolder>(BreedDTOItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedDTOViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCatBinding.inflate(layoutInflater, parent, false)
        return BreedDTOViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BreedDTOViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class BreedDTOViewHolder(private val binding: ItemCatBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BreedDTO) {
            binding.apply {
                val url = item.image?.url
                ivCat.visibilityIf(!url.isNullOrEmpty())
                ivCat.load(url)
                title.text = item.name
                description.text = item.description
                root.setOnClickListener { onBreedDTOClick(item) }
            }
        }
    }

    internal object BreedDTOItemCallback : DiffUtil.ItemCallback<BreedDTO>() {
        override fun areItemsTheSame(first: BreedDTO, second: BreedDTO): Boolean =
            first.id == second.id

        override fun areContentsTheSame(first: BreedDTO, second: BreedDTO): Boolean =
            first == second

    }

}