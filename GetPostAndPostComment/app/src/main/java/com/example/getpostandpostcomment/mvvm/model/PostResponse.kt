package com.example.getpostandpostcomment.mvvm.model

data class PostResponse (
    val userId:Int,
    val id:Int,
    val title:String,
    val body:String
        )