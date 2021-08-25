package com.example.enqueuepractice.ui

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.enqueuepractice.model.Characteristics
import com.example.enqueuepractice.model.PokemonRequest
import com.example.enqueuepractice.objects.RetrofitInstance
import com.example.enqueuepractice.services.PokeApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitViewModelBuilder: ViewModel() {
    private  val _instanceOfMutableLiveData:MutableLiveData<PokemonRequest> = MutableLiveData()
    val instanceOfMutableLiveData:LiveData<PokemonRequest> = _instanceOfMutableLiveData
    private  val _instanceOfMutableLiveDataChar:MutableLiveData<Characteristics> = MutableLiveData()
    val instanceOfMutableLiveDataChar:LiveData<Characteristics> = _instanceOfMutableLiveDataChar

     fun fetchPokemonData(limited:Int, offset: Int) {


        val pokemonRequestCall = RetrofitInstance.RetrofitBuilder().getPokemon(limited, offset)

        pokemonRequestCall.enqueue(object : Callback<PokemonRequest> {
            override fun onResponse( //on successful network call
                call: Call<PokemonRequest>,
                response: Response<PokemonRequest>
            ) {


                //assert responseBody not null
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    _instanceOfMutableLiveData.postValue(responseBody)

                }else{
                    _instanceOfMutableLiveData.postValue(null)
                }
            }

            //on failure
            override fun onFailure(call: Call<PokemonRequest>, t: Throwable) {
                //on failure display error message
              // reloadButton.visibility = View.VISIBLE
                _instanceOfMutableLiveData.postValue(null)
            }

        })
    }

    fun fetchPokemonDataChar(url:String) {


        val pokemonRequestCall = RetrofitInstance.RetrofitBuilder().getPokemonChar(url)

        pokemonRequestCall.enqueue(object : Callback<Characteristics> {
            override fun onResponse( //on successful network call
                call: Call<Characteristics>,
                response: Response<Characteristics>
            ) {


                //assert responseBody not null
                if (response.isSuccessful) {
                    val responseBody2 = response.body()
                    _instanceOfMutableLiveDataChar.postValue(responseBody2)

                }else{
                    _instanceOfMutableLiveDataChar.postValue(null)
                }
            }

            //on failure
            override fun onFailure(call: Call<Characteristics>, t: Throwable) {
                //on failure display error message
                _instanceOfMutableLiveDataChar.postValue(null)
            }

        })
    }
}