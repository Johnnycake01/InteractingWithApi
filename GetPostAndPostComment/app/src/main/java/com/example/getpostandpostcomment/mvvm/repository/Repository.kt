package com.example.getpostandpostcomment.mvvm.repository

import com.example.getpostandpostcomment.mvvm.model.GetComments
import com.example.getpostandpostcomment.mvvm.model.GetPost
import com.example.getpostandpostcomment.mvvm.model.PostResponse
import com.example.getpostandpostcomment.mvvm.model.Users
import com.example.getpostandpostcomment.mvvm.network.MvvmRetrofitInstance
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

class Repository {
    suspend fun getUsersFunction(): Response<List<Users>>{
        return MvvmRetrofitInstance.retrofitBuilder().getUsersFunction()
    }

    suspend fun getPostFunction():Response<List<GetPost>>{
        return MvvmRetrofitInstance.retrofitBuilder().getPostFunction()
    }


    suspend fun getComments(id:Int):Response<List<GetComments>>{
        return MvvmRetrofitInstance.retrofitBuilder().getComments(id)
    }


    suspend fun postComment(id:Int):Response<PostResponse>{
        return MvvmRetrofitInstance.retrofitBuilder().postComment(id)
    }


    suspend fun postPost(post: GetPost):Response<GetPost>{
        return MvvmRetrofitInstance.retrofitBuilder().postPost(post)
    }
}