package com.example.dependencyinjection.model.repository

import com.example.dependencyinjection.model.data.Comments
import com.example.dependencyinjection.model.data.UserDetail
import com.example.dependencyinjection.model.data.UserPost
import com.example.dependencyinjection.network.UserNetwork
import retrofit2.Response

class UserRepository_impl(private val retro: UserNetwork): UserRepository {
    override suspend fun getAllUserPOst():Response<List<UserPost>>{
        return retro.getAllUserPOst()
    }

    override suspend fun getAllUserDetails(): Response<List<UserDetail>> {
        return retro.getAllUserDetails()
    }

    override suspend fun getAllComments(): Response<List<Comments>> {
        return retro.getAllComments()
    }
}