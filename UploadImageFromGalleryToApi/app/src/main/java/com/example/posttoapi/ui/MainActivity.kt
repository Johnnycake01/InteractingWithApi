package com.example.posttoapi.ui

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.posttoapi.R
import com.example.posttoapi.interfaces.ApiPostMethod
import com.example.posttoapi.model.ImageUploadResponse
import com.example.posttoapi.model.UploadRequestBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

const val READ_GALLERY = 1
const val BASE_URL = "https://darot-image-upload-service.herokuapp.com/api/v1/"
class MainActivity : AppCompatActivity(), UploadRequestBody.UploadCallBack {

    //declaration of variables
    private lateinit var selectButton: AppCompatButton
    private lateinit var message: TextView
    private lateinit var uploadImage:AppCompatButton
    private lateinit var imageView:ImageView
    private var imageUri:Uri? = null
    private lateinit var main:ConstraintLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var statusLabel:TextView
    private lateinit var messageLabel:TextView
    private lateinit var downloadLabel:TextView
    private lateinit var status:Button
    private lateinit var message4:Button
    private lateinit var download:Button
    private lateinit var reloadButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //assign variables to views and instantiate other variable
        selectButton = findViewById(R.id.selectImage)
        message = findViewById(R.id.messageBox)
        imageView = findViewById(R.id.ivImage)
        uploadImage = findViewById(R.id.uploadImageButton)
        main = findViewById(R.id.main)
        progressBar = findViewById(R.id.progress_bar)
        statusLabel = findViewById(R.id.status)
        messageLabel = findViewById(R.id.message)
        downloadLabel = findViewById(R.id.download)
        status = findViewById(R.id.statuslabel)
        message4 = findViewById(R.id.messagelabel)
        download = findViewById(R.id.downloadlabel)
        reloadButton = findViewById(R.id.buttonReload)

      hideViews()
       readStorage()

        reloadButton.setOnClickListener {
            hideViews()
            readStorage()
            reloadButton.visibility = View.GONE
            message.visibility = View.GONE
        }

        //button to select image from gallery
        selectButton.setOnClickListener {
            uploadImage()
        }
        //upload image to server on button clicked
        uploadImage.setOnClickListener {
            if (imageView.drawable != null || imageView.background != null){
                sendImageToApi()
            }else{
                main.snackbar("select an image first")
            }
        }

    }

    private fun hideViews() {
        imageView.visibility = View.GONE
        uploadImage.visibility =View.GONE
        progressBar.visibility = View.GONE
        statusLabel.visibility = View.GONE
        messageLabel.visibility = View.GONE
        downloadLabel.visibility =View.GONE
        selectButton.visibility = View.GONE
        status.visibility = View.GONE
        message4.visibility = View.GONE
        download.visibility = View.GONE
    }
    private fun showViews() {
        imageView.visibility = View.VISIBLE
        uploadImage.visibility = View.VISIBLE
        progressBar.visibility =  View.VISIBLE
        statusLabel.visibility =  View.VISIBLE
        messageLabel.visibility =  View.VISIBLE
        downloadLabel.visibility = View.VISIBLE
        selectButton.visibility =  View.VISIBLE
        status.visibility =  View.VISIBLE
        message4.visibility =  View.VISIBLE
        download.visibility =  View.VISIBLE
    }


    //function to upload image to api
    private fun sendImageToApi() {
        if (imageUri == null) {
            main.snackbar("Select an image first")
        }
        val profileDescriptor = contentResolver.openFileDescriptor(
            imageUri!!, "r",
            null
        ) ?: return
        val file = File(cacheDir, contentResolver.getFileName(imageUri!!))
        val inputStream = FileInputStream(profileDescriptor.fileDescriptor)
        val outputStream = FileOutputStream(file)
        inputStream.copyTo(outputStream)
        progressBar.progress = 0
        val body = UploadRequestBody(file, "image", this)
        ApiPostMethod().postImage(
            MultipartBody.Part.createFormData("file", file.name, body),
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(), "json")
        ).enqueue(object : retrofit2.Callback<ImageUploadResponse> {
            override fun onResponse(
                call: Call<ImageUploadResponse>,
                response: Response<ImageUploadResponse>
            ) {
                val responseBody = response.body()
                progressBar.progress = 100

                if (responseBody != null){
                    statusLabel.text = responseBody.status.toString()
                    messageLabel.text = responseBody.message
                    downloadLabel.text = responseBody.payload.downloadUri
                }

            }

            override fun onFailure(call: Call<ImageUploadResponse>, t: Throwable) {
                main.snackbar(t.message!!)
                progressBar.progress = 0
            }

        })


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 100){
            Glide.with(this)
                .load(data?.data)
                .centerCrop()
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView)
            imageUri = data?.data!!
        }
    }

    private fun uploadImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 100)
    }

    //function you read phone contact
    private fun readStorage(){
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), READ_GALLERY
            )

        } else {

            showViews()


        }

    }
    //on permission request
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == READ_GALLERY){
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                showViews()
            }else{
               message.visibility = View.VISIBLE
                reloadButton.visibility = View.VISIBLE

            }
        }
    }

    override fun onProgressUpdate(percentage: Int) {
        progressBar.progress = percentage
    }
}