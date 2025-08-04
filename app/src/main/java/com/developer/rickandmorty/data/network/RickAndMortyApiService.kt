package com.developer.rickandmorty.data.network

import com.developer.rickandmorty.data.models.response.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApiService {

   @GET("character")
   suspend fun getCharacters(
       @Query("page") pageNumber: Int,
   ): CharacterResponse

}