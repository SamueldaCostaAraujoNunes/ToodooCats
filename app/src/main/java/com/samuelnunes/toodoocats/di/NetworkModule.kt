package com.samuelnunes.toodoocats.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.samuelnunes.toodoocats.data.remote.api.TheCatApi
import com.samuelnunes.toodoocats.data.repository.CatsRepository
import com.samuelnunes.toodoocats.domain.repository.ICatsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providerGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun providerTheCatApiRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Singleton
    @Provides
    fun providerTheCatApiService(retrofit: Retrofit): TheCatApi = retrofit.create(TheCatApi::class.java)

    @Singleton
    @Provides
    fun providerTheCatsRepository(theCatApi: TheCatApi): ICatsRepository = CatsRepository(theCatApi)

}