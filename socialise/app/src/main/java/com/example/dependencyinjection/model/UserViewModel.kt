package com.example.dependencyinjection.model

import android.util.Log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dependencyinjection.model.data.Comments
import com.example.dependencyinjection.model.data.UserDetail
import com.example.dependencyinjection.model.data.UserPost
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.dependencyinjection.model.repository.UserRepository
import com.example.dependencyinjection.utils.TAG


@HiltViewModel
class UserViewModel @Inject constructor(private val repository: UserRepository):ViewModel() {
    private var _data: MutableLiveData<List<UserPost>> = MutableLiveData()
    val data: LiveData<List<UserPost>> = _data

    private var _userDetails: MutableLiveData<List<UserDetail>> = MutableLiveData()
    val userDetail: LiveData<List<UserDetail>> = _userDetails

    private var _commentValue:MutableLiveData<List<Comments>> = MutableLiveData()
    val commentValue:LiveData<List<Comments>> = _commentValue

    fun fetchUserDataFromApi() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getAllUserPOst()
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    _data.postValue(responseBody)
                    Log.d(TAG, "fetchUserDataFromApi: $responseBody")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d(TAG, "fetchUserDataFromApi: ${e.message}")
            }


        }
    }

    fun fetchUserDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getAllUserDetails()
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    _userDetails.postValue(responseBody)
                    Log.d(TAG, "fetchUserDetails: successful")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d(TAG, "fetchUserDataFromApi: ${e.message}")
            }


        }
    }

    fun fetchAllComments(){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = repository.getAllComments()
                if (response.isSuccessful){
                    val responseBody = response.body()
                    _commentValue.postValue(responseBody)
                }

            }catch (e: Exception){
                e.printStackTrace()
                Log.d(TAG, "fetchAllComments: ${e.message}")
                _commentValue.postValue(null)
            }
        }
    }
}

//@HiltViewModel
//class DonutViewModel @Inject constructor(handle: SavedStateHandle?, repository: RecipeRepository?) :
//    ViewModel()