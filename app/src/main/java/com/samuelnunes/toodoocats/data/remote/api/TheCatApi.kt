package com.samuelnunes.toodoocats.data.remote.api

import com.samuelnunes.toodoocats.domain.dto.BreedDTO
import retrofit2.http.GET

interface TheCatApi {

    @GET("breeds")
    suspend fun getAllBreeds(): List<BreedDTO>

    @GET("images/search?mime_types=gif")
    suspend fun getRandomGif(): List<BreedDTO.ImageDTO>

}