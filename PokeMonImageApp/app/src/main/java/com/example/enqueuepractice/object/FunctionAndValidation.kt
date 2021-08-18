package com.example.enqueuepractice.`object`

object FunctionAndValidation {
    fun getNumber(url: String):Int{
        val splitNumber = url.split("/")
        return splitNumber[splitNumber.lastIndex-1].toInt()
    }
}