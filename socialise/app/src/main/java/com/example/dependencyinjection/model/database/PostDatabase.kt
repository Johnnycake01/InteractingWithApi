package com.example.dependencyinjection.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dependencyinjection.model.data.Comments
import com.example.dependencyinjection.model.data.UserDetail
import com.example.dependencyinjection.model.data.UserPost

@Database(entities = [UserPost::class,UserDetail::class,Comments::class],version = 1,exportSchema = false)
abstract class PostDatabase:RoomDatabase() {
    abstract  fun postDao():PostDao
    abstract fun userDetailsDao():UserDetailDao
    abstract fun commentDao():CommentDao
    companion object{
        @Volatile
        private var InstanceOfDatabase:PostDatabase? = null

        fun getDatabase(context: Context):PostDatabase{
            val tempInstance = InstanceOfDatabase
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PostDatabase::class.java,
                    "post_database"
                ).build()
                InstanceOfDatabase = instance
                return instance
            }
        }
    }
}