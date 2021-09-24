package com.example.dependencyinjection.network

import com.example.dependencyinjection.model.data.Comments
import com.example.dependencyinjection.model.data.UserDetail
import com.example.dependencyinjection.model.data.UserPost
import retrofit2.Response
import retrofit2.http.GET

interface UserNetwork {
    @GET("posts")
    suspend fun getAllUserPOst(): Response<List<UserPost>>

    @GET("users")
    suspend fun getAllUserDetails():Response<List<UserDetail>>

    @GET("comments")
    suspend fun getAllComments():Response<List<Comments>>
}