package com.samuelnunes.toodoocats

import android.app.Application
import androidx.viewbinding.BuildConfig
import com.google.android.material.color.DynamicColors
import timber.log.Timber

class ToodooCatsApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}