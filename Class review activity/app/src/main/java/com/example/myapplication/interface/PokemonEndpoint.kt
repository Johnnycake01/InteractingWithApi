package com.example.myapplication.`interface`

import com.example.myapplication.model.ResponseFromApi
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface PokemonEndpoint {
    @GET("pokemon?limit=100&offset=0")
    fun getPokemon():Call<ResponseFromApi>
//    suspend fun getPokemonr():Response<ResponseFromApi>
}