package com.example.myapplication.singleton

import com.example.myapplication.`interface`.PokemonEndpoint
import com.example.myapplication.utility.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    fun retrofitBuilder():PokemonEndpoint{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonEndpoint::class.java)
    }

    fun getNumberFromUrl(url:String):Int{
        val splitUrl = url.split("/")
        return splitUrl[splitUrl.lastIndex-1].toInt()
    }

}