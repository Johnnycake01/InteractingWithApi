package com.example.dependencyinjection.model.database

import androidx.lifecycle.LiveData
import com.example.dependencyinjection.model.data.Comments
import com.example.dependencyinjection.model.data.UserDetail
import com.example.dependencyinjection.model.data.UserPost

class Repository (private val postDao: PostDao, private val userDetails:UserDetailDao, private val commentsDao: CommentDao){
    val readAllData: LiveData<List<UserPost>> = postDao.readAllData()
    val readAllUsers:LiveData<List<UserDetail>> = userDetails.readAllUserFromDb()


    fun readCommentsFromDb(id:Int):LiveData<List<Comments>>{
        return commentsDao.getCommentById(id)
    }
    suspend fun insertToDatabase(post:List<UserPost>){
        postDao.insertToDatabase(post)
    }

    suspend fun insertSinglePostToDatabase(post:UserPost){
        postDao.insertSinglePostToDb(post)
    }
    suspend fun insertSingleCommentToDatabase(comment: Comments){
        commentsDao.insertSingleCommentToDb(comment)
    }
    suspend fun insertUsersToDatabase(user:List<UserDetail>){
        userDetails.insertAllUsers(user)
    }

    suspend fun insertCommentsToDataBase(comment:List<Comments>){
        commentsDao.insertAllCommentToDb(comment)
    }


}