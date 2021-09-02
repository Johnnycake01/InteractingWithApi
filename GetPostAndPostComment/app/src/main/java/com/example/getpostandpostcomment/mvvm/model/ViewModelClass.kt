package com.example.getpostandpostcomment.mvvm.model


import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getpostandpostcomment.mvvm.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewModelClass(private val repository: Repository):ViewModel(){
    private var _data: MutableLiveData<List<Users>> = MutableLiveData()
    val data: LiveData<List<Users>> = _data

    private var _postData: MutableLiveData<MutableList<GetPost>> = MutableLiveData()
    val postData: LiveData<MutableList<GetPost>> = _postData

    private var _listOfComment:MutableLiveData<List<GetComments>> = MutableLiveData()
    val listOfComment = _listOfComment

    private var _comment:MutableLiveData<GetComments> = MutableLiveData()
    val comment:LiveData<GetComments>  = _comment

    private var _postPost:MutableLiveData<GetPost> = MutableLiveData()
    val postPost:LiveData<GetPost>  = _postPost


    var num = 10

    fun counting(){

        num++
    }


    fun fetchUserDataFromApi(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getUsersFunction()
            if (response.isSuccessful){
               val responseBody = response.body()
                if (responseBody != null){
                    withContext(Dispatchers.Main){
                        _data.value = responseBody
                    }

                }

            }else{
                _data.value = null
            }

        }
    }

    fun fetchEachUserPost(id:Int){
        viewModelScope.launch(Dispatchers.IO){
            val response = repository.getPostFunction()
            if (response.isSuccessful){
                val responseBody = response.body()
                val mutableListOfPosts = mutableListOf<GetPost>()
                if (responseBody != null){
                    for (i in responseBody){
                        if (i.userId == id){
                            mutableListOfPosts.add(i)
                        }
                    }
                    withContext(Dispatchers.Main){
                        _postData.value = mutableListOfPosts
                    }
                }

            }else{
                _postData.value = null
            }
        }
    }

    fun fetchDummyCommentFromApi(postId:Int){
        viewModelScope.launch(Dispatchers.IO){
            val response = repository.getComments(postId)
            if (response.isSuccessful){
                val responseBody = response.body()
                if (responseBody != null){
                    withContext(Dispatchers.Main){
                        _listOfComment.value = responseBody
                    }

                }
            }else{
                _listOfComment.value = null
            }

        }
    }

    fun postCommentToApi(postId:Int, com:EditText){
        viewModelScope.launch(Dispatchers.IO) {
            val comments = repository.postComment(postId)
            if (comments.isSuccessful) {
                val commentBody = comments.body()
                if (commentBody != null) {
                    val postIdd = commentBody.userId
                    val idd = commentBody.id

                    withContext(Dispatchers.Main) {
                        val commentData = GetComments(
                            name = "Oyesina Johnson",
                            postId = postIdd,
                            body = com.text.toString(),
                            email = "oyesinajohnson@gmail.com",
                            id = idd
                        )
                        withContext(Dispatchers.Main){
                            _comment.value = commentData
                        }

                    }
                }

            }else{
                _comment.value = null
            }
        }
    }

    fun postPostToApi(post: GetPost, postId: Int){
        viewModelScope.launch(Dispatchers.IO){
            val response = repository.postPost(post)
            if (response.isSuccessful){
                val responseBody = response.body()
                if (responseBody != null){
                    val addPost = GetPost(
                        id = responseBody.id,
                        title = responseBody.title,
                        body = responseBody.body,
                        userId = responseBody.userId
                    )
                    withContext(Dispatchers.Main){
                        _postPost.value = addPost
                    }



                }
            }else{
                _postPost.value = null
            }
        }
    }



}