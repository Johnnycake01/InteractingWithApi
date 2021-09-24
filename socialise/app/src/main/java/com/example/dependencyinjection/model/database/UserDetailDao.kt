package com.example.dependencyinjection.model.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dependencyinjection.model.data.UserDetail

@Dao
interface UserDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUsers(user:List<UserDetail>)

    @Query("SELECT * FROM user_detail ORDER BY id ASC")
    fun readAllUserFromDb(): LiveData<List<UserDetail>>
}