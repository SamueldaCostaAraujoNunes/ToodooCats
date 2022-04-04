package com.samuelnunes.toodoocats.domain.use_case

import com.samuelnunes.toodoocats.domain.repository.ICatsRepository
import com.samuelnunes.toodoocats.domain.utils.Resource
import com.samuelnunes.toodoocats.entity.Breed
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCatsGifsUseCase @Inject constructor(
    private var repository: ICatsRepository
) {

    operator fun invoke(): Flow<Resource<List<Breed.Image>>> {
        return repository.getCatsGifs().map {
            it.convert { imageList ->
                imageList.map { dto ->
                    dto.toImage()
                }
            }
        }
    }

}