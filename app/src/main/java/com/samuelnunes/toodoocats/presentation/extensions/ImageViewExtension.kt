package com.samuelnunes.toodoocats.presentation.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("imageUrl")
fun ImageView.url(url: String?) = load(url)