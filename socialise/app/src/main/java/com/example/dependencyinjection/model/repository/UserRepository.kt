package com.example.dependencyinjection.model.repository

import com.example.dependencyinjection.model.data.Comments
import com.example.dependencyinjection.model.data.UserDetail
import com.example.dependencyinjection.model.data.UserPost
import retrofit2.Response

interface UserRepository {
    suspend fun getAllUserPOst():Response<List<UserPost>>
    suspend fun getAllUserDetails():Response<List<UserDetail>>
    suspend fun getAllComments():Response<List<Comments>>
}
