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

internal class CatGifListAdapter(private val onImageDTOClick: (BreedDTO.ImageDTO) -> Unit) :
    ListAdapter<BreedDTO.ImageDTO, CatGifListAdapter.ImageDTOViewHolder>(ImageDTOItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageDTOViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemGifBinding.inflate(layoutInflater, parent, false)
        return ImageDTOViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageDTOViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ImageDTOViewHolder(private val binding: ItemGifBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BreedDTO.ImageDTO) {
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

    internal object ImageDTOItemCallback : DiffUtil.ItemCallback<BreedDTO.ImageDTO>() {
        override fun areItemsTheSame(first: BreedDTO.ImageDTO, second: BreedDTO.ImageDTO): Boolean =
            first.id == second.id

        override fun areContentsTheSame(first: BreedDTO.ImageDTO, second: BreedDTO.ImageDTO): Boolean =
            first == second

    }

}