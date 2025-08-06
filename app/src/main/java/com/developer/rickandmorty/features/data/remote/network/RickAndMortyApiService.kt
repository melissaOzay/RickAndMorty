package com.developer.rickandmorty.features.data.remote.network

import com.developer.rickandmorty.features.data.remote.models.response.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApiService {

    @GET("character")
    suspend fun getCharacters(
        @Query("page") pageNumber: Int,
    ): CharacterResponse

}