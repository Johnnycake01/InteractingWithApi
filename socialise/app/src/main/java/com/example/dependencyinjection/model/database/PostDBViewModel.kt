package com.example.dependencyinjection.model.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.dependencyinjection.model.data.Comments
import com.example.dependencyinjection.model.data.UserDetail
import com.example.dependencyinjection.model.data.UserPost
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostDBViewModel(application: Application): AndroidViewModel(application) {
    private val postDao = PostDatabase.getDatabase(application).postDao()
    private val userDetailsDao = PostDatabase.getDatabase(application).userDetailsDao()
    private val commentDao = PostDatabase.getDatabase(application).commentDao()
    private val repository = Repository(postDao,userDetailsDao, commentDao)

    val readAllData: LiveData<List<UserPost>> = repository.readAllData
    val readAllUserDetail:LiveData<List<UserDetail>> = repository.readAllUsers

    fun readAllComments(id:Int):LiveData<List<Comments>>{
        return repository.readCommentsFromDb(id)
    }
    fun insertSingleCommentToDatabase(comment: Comments){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertSingleCommentToDatabase(comment)
        }
    }
    fun insertNewPost(userPost: List<UserPost>){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertToDatabase(userPost)
        }
    }
    fun insertSingleUserPost(user:UserPost){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertSinglePostToDatabase(user)
        }
    }
    fun insertAllUser(users: List<UserDetail>){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertUsersToDatabase(users)
        }
    }

    fun insertAllCommentsToDB(comment: List<Comments>){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertCommentsToDataBase(comment)
        }
    }
}