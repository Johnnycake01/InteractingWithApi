package com.example.dependencyinjection.di

import android.content.Context
import com.example.dependencyinjection.ui.MyUserApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideApp(@ApplicationContext context: Context): MyUserApp {
        return context as MyUserApp
    }
}