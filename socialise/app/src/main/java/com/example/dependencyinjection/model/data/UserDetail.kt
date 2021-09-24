package com.example.dependencyinjection.model.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user_detail")
data class UserDetail(
    @PrimaryKey
    val id:Int,
    val name:String,
): Parcelable
