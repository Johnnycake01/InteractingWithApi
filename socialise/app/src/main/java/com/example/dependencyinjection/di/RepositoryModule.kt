package com.example.dependencyinjection.di

import com.example.dependencyinjection.model.repository.UserRepository
import com.example.dependencyinjection.model.repository.UserRepository_impl
import com.example.dependencyinjection.network.UserNetwork
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideUserRepository(retro: UserNetwork): UserRepository {
        return UserRepository_impl(retro)
    }
}