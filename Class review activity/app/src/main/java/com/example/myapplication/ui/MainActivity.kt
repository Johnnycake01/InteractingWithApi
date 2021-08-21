package com.example.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapter.RecyclerViewAdapter
import com.example.myapplication.model.ResponseFromApi
import com.example.myapplication.singleton.RetrofitInstance
import com.example.myapplication.utility.TAG
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var rv:RecyclerView
    private lateinit var rvAdapter: RecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG,"on create view")
        rv = findViewById(R.id.recyclerView)

        rvAdapter = RecyclerViewAdapter(this)
        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(this)


        fetchPokemonData()
    }

    private fun fetchPokemonData() {
        val pokemonReturnedInterface = RetrofitInstance.retrofitBuilder()
        val responseFromApi = pokemonReturnedInterface.getPokemon()
        responseFromApi.enqueue(object :Callback<ResponseFromApi>{
            override fun onResponse(
                call: Call<ResponseFromApi>,
                response: Response<ResponseFromApi>
            ) {
                Log.d(TAG,"success")
                val responseBody = response.body()
                Log.d(TAG, responseBody.toString())
                if(responseBody != null){
                    val arrayOfResult = responseBody.results
                   rvAdapter.assignValueToArrayInAdapter(arrayOfResult)
                }
            }

            override fun onFailure(call: Call<ResponseFromApi>, t: Throwable) {
                Log.d(TAG,"failure")
            }

        })
    }
}