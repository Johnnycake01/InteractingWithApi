package com.example.posttoapi.interfaces


import com.example.posttoapi.model.ImageUploadResponse
import com.example.posttoapi.ui.BASE_URL
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiPostMethod {
    @Multipart
    @POST("upload")
    fun postImage(
        @Part image:MultipartBody.Part,
        @Part("file") file:RequestBody
    ): Call<ImageUploadResponse>


    companion object{
      operator fun invoke():ApiPostMethod{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiPostMethod::class.java)
        }
    }
}