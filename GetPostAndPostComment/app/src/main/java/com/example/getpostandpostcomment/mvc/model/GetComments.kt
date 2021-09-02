package com.example.getpostandpostcomment.mvc.model

data class GetComments(
    val postId:Int,
    val id:Int,
    val name:String,
    val email:String,
    val body:String
)
