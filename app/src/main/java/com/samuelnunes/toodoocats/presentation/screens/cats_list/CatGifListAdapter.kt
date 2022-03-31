package com.samuelnunes.toodoocats.presentation.screens.cats_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.samuelnunes.toodoocats.databinding.ItemGifBinding
import com.samuelnunes.toodoocats.domain.dto.BreedDTO
import com.samuelnunes.toodoocats.entity.Breed

internal class CatGifListAdapter(private val onImageDTOClick: (Breed.Image) -> Unit) :
    ListAdapter<Breed.Image, CatGifListAdapter.ImageViewHolder>(ImageItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemGifBinding.inflate(layoutInflater, parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ImageViewHolder(private val binding: ItemGifBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Breed.Image) {
            binding.apply {
                val context = binding.root.context
                val imageLoader = ImageLoader.Builder(context)
                    .components {
                        add(ImageDecoderDecoder.Factory())
                    }.build()

                val request = ImageRequest.Builder(context)
                    .data(item.url)
                    .crossfade(true)
                    .target(ivCat)
                    .build()

                imageLoader.enqueue(request)
                root.setOnClickListener { onImageDTOClick(item) }
            }
        }
    }

    internal object ImageItemCallback : DiffUtil.ItemCallback<Breed.Image>() {
        override fun areItemsTheSame(first: Breed.Image, second: Breed.Image): Boolean =
            first.id == second.id

        override fun areContentsTheSame(first: Breed.Image, second: Breed.Image): Boolean =
            first == second

    }

}