package com.example.getpostandpostcomment.mvvm.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.getpostandpostcomment.mvvm.repository.Repository

class ViewModelClassFactory(private val repository: Repository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewModelClass(repository) as T
    }
}