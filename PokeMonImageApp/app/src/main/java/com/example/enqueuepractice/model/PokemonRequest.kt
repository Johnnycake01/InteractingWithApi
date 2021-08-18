package com.example.enqueuepractice.model

//pokemon request class
data class PokemonRequest(
    val count:Int,
    val next:Any,
    val Previous:Any,
    val results:List<Result>
)
