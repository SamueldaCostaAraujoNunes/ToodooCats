package com.samuelnunes.toodoocats.data.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.samuelnunes.toodoocats.data.remote.api.TheCatApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TheCatApiManager {

    private val gson: Gson = GsonBuilder().create()

    private val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    operator fun invoke(): TheCatApi = instance.create(TheCatApi::class.java)
}