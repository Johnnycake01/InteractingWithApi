package com.example.getpostandpostcomment.mvvm.network

import com.example.getpostandpostcomment.utility.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MvvmRetrofitInstance{
    fun retrofitBuilder():MvvmApiRequest{
        val logging = HttpLoggingInterceptor()
        logging.level = (HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(logging)
        return Retrofit.Builder()
            .client(client.build())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MvvmApiRequest::class.java)
    }
}