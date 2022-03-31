package com.samuelnunes.toodoocats.domain.repository

import com.samuelnunes.toodoocats.domain.dto.BreedDTO
import com.samuelnunes.toodoocats.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ICatsRepository {

    fun getAllBreeds(): Flow<Resource<List<BreedDTO>>>

    fun getCatsGifs(): Flow<Resource<List<BreedDTO.ImageDTO>>>
}