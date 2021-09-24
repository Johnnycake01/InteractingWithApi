package com.example.dependencyinjection.model.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dependencyinjection.model.data.Comments


@Dao
interface CommentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCommentToDb(comment:List<Comments>)

    @Query("SELECT * FROM comment_table WHERE postId == :postId")
    fun getCommentById(postId:Int):LiveData<List<Comments>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSingleCommentToDb(comment: Comments)

}