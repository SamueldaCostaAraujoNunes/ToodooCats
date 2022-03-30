package com.samuelnunes.toodoocats.data.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.samuelnunes.toodoocats.data.remote.api.TheCapApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TheCapApiManager {

    private val gson: Gson = GsonBuilder().create()

    private val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    operator fun invoke(): TheCapApi = instance.create(TheCapApi::class.java)
}