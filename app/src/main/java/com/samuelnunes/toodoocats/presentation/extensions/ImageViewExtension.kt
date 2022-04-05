package com.samuelnunes.toodoocats.presentation.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("imageUrl")
fun imageUrl(iv: ImageView, url: String?) = iv.load(url)