package com.example.enqueuepractice.objects

object FunctionAndValidation {
    fun getNumber(url: String):Int{
        val splitNumber = url.split("/")
        return splitNumber[splitNumber.lastIndex-1].toInt()
    }
}