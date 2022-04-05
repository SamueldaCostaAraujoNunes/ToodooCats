package com.samuelnunes.toodoocats.presentation.screens.cats_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.samuelnunes.toodoocats.domain.use_case.GetAllBreedsUseCase
import com.samuelnunes.toodoocats.domain.use_case.GetCatsGifsUseCase
import com.samuelnunes.toodoocats.domain.utils.Resource
import com.samuelnunes.toodoocats.domain.entity.Breed
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CatsListViewModel @Inject constructor(
    private var getAllBreedsUseCase: GetAllBreedsUseCase,
    private var getCatsGifsUseCase: GetCatsGifsUseCase
): ViewModel() {

    fun getAllBreeds(): LiveData<Resource<List<Breed>>> = getAllBreedsUseCase().asLiveData()

    fun getCatsGifs(): LiveData<Resource<List<Breed.Image>>> = getCatsGifsUseCase().asLiveData()

}