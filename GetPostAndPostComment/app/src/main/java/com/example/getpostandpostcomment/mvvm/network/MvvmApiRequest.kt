package com.example.getpostandpostcomment.mvvm.network


import com.example.getpostandpostcomment.mvvm.model.GetComments
import com.example.getpostandpostcomment.mvvm.model.GetPost
import com.example.getpostandpostcomment.mvvm.model.PostResponse
import com.example.getpostandpostcomment.mvvm.model.Users
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MvvmApiRequest {
    @GET("posts")
    suspend fun getPostFunction():Response<List<GetPost>>

    @GET("users")
    suspend fun getUsersFunction():Response<List<Users>>

    @GET("posts/{id}/comments")
    suspend fun getComments(@Path ("id") id:Int):Response<List<GetComments>>

    @POST("posts/{id}/comments")
    suspend fun postComment(@Path ("id") id:Int):Response<PostResponse>


    @POST("posts")
    suspend fun postPost(@Body post: GetPost):Response<GetPost>
}