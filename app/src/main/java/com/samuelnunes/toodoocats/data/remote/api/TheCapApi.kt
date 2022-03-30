package com.samuelnunes.toodoocats.data.remote.api

import com.samuelnunes.toodoocats.entity.remote.BreedDTO
import retrofit2.http.GET

interface TheCapApi {

    @GET("breeds")
    suspend fun getAllBreeds(): List<BreedDTO>

    @GET("images/search?mime_types=gif")
    suspend fun getRandomGif(): List<BreedDTO.ImageDTO>

}