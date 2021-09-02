package com.example.getpostandpostcomment.mvc.network

import com.example.getpostandpostcomment.mvc.model.GetComments
import com.example.getpostandpostcomment.mvc.model.GetPost
import com.example.getpostandpostcomment.mvc.model.PostResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiRequest {
    @GET("posts")
    suspend fun getPostFunction():Response<List<GetPost>>

    @GET("posts/{id}/comments")
    suspend fun getComments(@Path ("id") id:Int):Response<List<GetComments>>

    @POST("posts/{id}/comments")
    suspend fun postComment(@Path ("id") id:Int):Response<PostResponse>
}