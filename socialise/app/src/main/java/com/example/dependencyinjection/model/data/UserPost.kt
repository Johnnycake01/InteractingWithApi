package com.example.dependencyinjection.model.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user_post_table")
data class UserPost (
    @ColumnInfo(name = "user_id") val userId:Int,
    @PrimaryKey
    @ColumnInfo(name = "post_id")val id:Int,
    @ColumnInfo(name = "post_title")val title:String,
    @ColumnInfo(name = "post_body")val body:String
        ):Parcelable


