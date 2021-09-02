package com.example.getpostandpostcomment.mvvm.ui.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.getpostandpostcomment.R
import com.example.getpostandpostcomment.mvvm.ui.adapter.MvvmListOfUsersAdapter
import com.example.getpostandpostcomment.mvvm.model.ViewModelClass
import com.example.getpostandpostcomment.mvvm.model.ViewModelClassFactory
import com.example.getpostandpostcomment.mvvm.repository.Repository
import com.example.getpostandpostcomment.utility.TAG

class MvvmListOfUsers : AppCompatActivity() {
    private lateinit var rvAdapter: MvvmListOfUsersAdapter
    private lateinit var rv: RecyclerView
    private lateinit var viewModelInstance:ViewModelClass
    private lateinit var repository: Repository
    private lateinit var viewModelClassFactory: ViewModelClassFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvm_list_of_users)
        repository = Repository()
        viewModelClassFactory = ViewModelClassFactory(repository)
        viewModelInstance = ViewModelProvider(this, viewModelClassFactory).get(ViewModelClass::class.java)
        rv = findViewById(R.id.mvvmRecyclerView)
        rvAdapter = MvvmListOfUsersAdapter(this)

        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(this)
        viewModelInstance.fetchUserDataFromApi()

        viewModelInstance.data.observe(this,{
            rvAdapter.addListOfUsers(it)
        })


    }
}