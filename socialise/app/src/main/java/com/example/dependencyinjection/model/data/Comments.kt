package com.example.dependencyinjection.model.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "comment_table")
data class Comments(
    val postId:Int,
    @PrimaryKey
    val id:Int,
    val name:String,
    val body:String
):Parcelable
