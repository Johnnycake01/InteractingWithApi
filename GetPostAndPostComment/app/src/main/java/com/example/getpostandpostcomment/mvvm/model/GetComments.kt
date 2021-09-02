package com.example.getpostandpostcomment.mvvm.model

data class GetComments(
    val postId:Int,
    val id:Int,
    val name:String,
    val email:String,
    val body:String
)
