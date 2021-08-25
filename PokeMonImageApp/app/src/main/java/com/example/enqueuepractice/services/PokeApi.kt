package com.example.enqueuepractice.services


import com.example.enqueuepractice.model.Characteristics
import com.example.enqueuepractice.model.PokemonRequest
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface PokeApi {
    //get method and endpoint address to get all pokemon
    //according to limit and offset set
    @GET("pokemon")
    fun getPokemon(@Query("limit") limit:Int, @Query("offset") offset:Int): Call<PokemonRequest>

    //get function which url is set dynamically
    @GET
    fun getPokemonChar(@Url url:String): Call<Characteristics>

}