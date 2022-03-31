package com.samuelnunes.toodoocats.data.remote.api

import com.samuelnunes.toodoocats.domain.dto.BreedDTO
import retrofit2.Response
import retrofit2.http.GET

interface TheCatApi {

    @GET("breeds")
    suspend fun getAllBreeds(): Response<List<BreedDTO>>

    @GET("images/search?mime_types=gif")
    suspend fun getRandomGif(): Response<List<BreedDTO.ImageDTO>>

}