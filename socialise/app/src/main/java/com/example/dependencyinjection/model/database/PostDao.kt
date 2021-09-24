package com.example.dependencyinjection.model.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dependencyinjection.model.data.UserPost

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToDatabase(post: List<UserPost>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSinglePostToDb(post:UserPost)

    @Query("SELECT * FROM user_post_table ORDER BY post_id DESC")
    fun readAllData(): LiveData<List<UserPost>>

}