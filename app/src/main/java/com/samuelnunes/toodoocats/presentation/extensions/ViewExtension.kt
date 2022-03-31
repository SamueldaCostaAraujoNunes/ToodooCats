package com.samuelnunes.toodoocats.presentation.extensions

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.databinding.BindingAdapter

@BindingAdapter("visibilityIf")
fun View.visibilityIf(condition: Boolean) {
    visibility = if(condition) VISIBLE else GONE
}