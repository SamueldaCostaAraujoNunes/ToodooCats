package com.samuelnunes.toodoocats.data.repository

import com.samuelnunes.toodoocats.data.remote.api.TheCatApi
import com.samuelnunes.toodoocats.domain.dto.BreedDTO
import com.samuelnunes.toodoocats.domain.repository.ICatsRepository
import com.samuelnunes.toodoocats.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CatsRepository @Inject constructor(private val api: TheCatApi): BaseRepository(), ICatsRepository {

    override fun getAllBreeds(): Flow<Resource<List<BreedDTO>>> = networkBoundResource(api::getAllBreeds)

    override fun getCatsGifs(): Flow<Resource<List<BreedDTO.ImageDTO>>> = networkBoundResource(api::getRandomGif)

}