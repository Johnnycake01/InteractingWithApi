package com.example.posttoapi.model
//response callback function
data class ImageUploadResponse(
    val message: String,
    val payload: Payload,
    val status: Int
)