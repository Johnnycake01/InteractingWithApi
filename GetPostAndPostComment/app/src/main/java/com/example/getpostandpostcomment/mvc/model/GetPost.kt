package com.example.getpostandpostcomment.mvc.model

data class GetPost(
    val userId:Int,
    val id:Int,
    val title:String,
    val body:String
)
