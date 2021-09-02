package com.example.getpostandpostcomment.mvc.ui.mvc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.getpostandpostcomment.R
import com.example.getpostandpostcomment.mvc.ui.adapter.ListOfUsersAdapter
import com.example.getpostandpostcomment.mvc.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListOfUsers : AppCompatActivity() {
    private lateinit var rvAdapter: ListOfUsersAdapter
    private lateinit var rv:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvc)

        rv = findViewById(R.id.rvListOfUsers)
        rvAdapter = ListOfUsersAdapter(this)

        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(this)

        makeNetworkCallToApi()

    }

    private fun makeNetworkCallToApi() {

        GlobalScope.launch(Dispatchers.IO) {

                val posts = RetrofitInstance.retrofitBuilder().getPostFunction()
                if (posts.isSuccessful){
                    val postBody = posts.body()
                    if (postBody != null){
                        val uniqueSetOfList:MutableSet<Int> = mutableSetOf()
                        for (post in postBody){
                            uniqueSetOfList.add(post.userId)
                        }
                        withContext(Dispatchers.Main){
                            rvAdapter.addListOfUsers(uniqueSetOfList)
                        }
                    }

                }

        }
    }
    }
