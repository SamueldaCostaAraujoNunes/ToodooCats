package com.samuelnunes.toodoocats.presentation.extensions

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE

fun View.visibilityIf(condition: Boolean) {
    visibility = if(condition) VISIBLE else GONE
}