package com.example.getpostandpostcomment.utility


const val BASE_URL = "https://jsonplaceholder.typicode.com/"
const val TAG = "mainActivity"

fun getFirstAndLastName(string: String):String{
    val eachString = string.split(" ")
    return if (eachString.size <= 1) eachString[0] else "${eachString[0]} ${eachString[1]}"
}
