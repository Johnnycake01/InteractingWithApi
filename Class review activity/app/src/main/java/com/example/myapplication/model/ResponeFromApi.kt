package com.example.myapplication.model

data class ResponseFromApi(
    val count:Int,
    val next:String? = null,
    val previous:String? = null,
    val results:List<Results>
)

data class Results(
    val name:String,
    val url:String
)