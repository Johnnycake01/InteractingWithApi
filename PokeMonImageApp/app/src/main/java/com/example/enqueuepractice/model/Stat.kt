package com.example.enqueuepractice.model

import com.google.gson.annotations.SerializedName

data class Stat (
    val base_stat:Int,
    val stat: SomethingStat
        )
data class SomethingStat(
    val name:String
)


