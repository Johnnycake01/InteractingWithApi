package com.example.getpostandpostcomment.mvvm.ui.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.getpostandpostcomment.R
import com.example.getpostandpostcomment.dialogue.action.DialogueListenerInterface
import com.example.getpostandpostcomment.dialogue.ui.AddPostDialogue
import com.example.getpostandpostcomment.mvvm.model.GetPost
import com.example.getpostandpostcomment.mvvm.model.ViewModelClass
import com.example.getpostandpostcomment.mvvm.model.ViewModelClassFactory
import com.example.getpostandpostcomment.mvvm.repository.Repository
import com.example.getpostandpostcomment.mvvm.ui.adapter.MvvmPostOfUserAdapter
import com.example.getpostandpostcomment.utility.ConnectivityLiveData

class MvvmDisplayUserPosts : AppCompatActivity() , DialogueListenerInterface {
    private lateinit var rvAdapter: MvvmPostOfUserAdapter
    private lateinit var rv: RecyclerView
    private  var userIdFromIntent:Int = 0
    private lateinit var viewModelClass: ViewModelClass
    private lateinit var addNewPostBotton:Button
    private lateinit var repository: Repository
    private lateinit var viewModelClassFactory: ViewModelClassFactory
    private lateinit var connectivityLiveData: ConnectivityLiveData
    private lateinit var networkError:LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvm_display_user_posts)
        connectivityLiveData = ConnectivityLiveData(application)
        networkError = findViewById(R.id.ErrorOnPostPage)

        addNewPostBotton = findViewById(R.id.mvvmAdd)

        repository = Repository()
        viewModelClassFactory = ViewModelClassFactory(repository)
        viewModelClass = ViewModelProvider(this, viewModelClassFactory)
            .get(ViewModelClass::class.java)
        rv = findViewById(R.id.rvMvvmPostOfUsers)



        rvAdapter = MvvmPostOfUserAdapter(this)


        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(this)



        userIdFromIntent = intent.getStringExtra("MvvmUserId")!!.toInt()
        connectivityLiveData.observe(this,{ isAvailable ->
            //2
            when (isAvailable) {
                true -> {
                    viewModelClass.fetchEachUserPost(userIdFromIntent)
                    networkError.visibility = View.GONE
                }
                false -> {
                    networkError.visibility = View.VISIBLE

                }
            }
        })


        viewModelClass.postData.observe(this,{
            rvAdapter.addListOfPosts(it)
        })
        viewModelClass.postPost.observe(this,{
            rvAdapter.addPostToRecyclerView(it)
        })
        addNewPostBotton.setOnClickListener {
            openDialogueBox()
        }

    }

    private fun openDialogueBox() {
        val dialogueBoxInstance = AddPostDialogue()
        dialogueBoxInstance.show(supportFragmentManager,"Show Add Post Dialogue")
    }

    override fun onClickOfAddPostButton(title: String, body: String) {
       viewModelClass.counting()
        val newCount = viewModelClass.num
        val postToSend = GetPost(
            userId = userIdFromIntent,
            id = newCount,
            title = title,
            body = body
        )
        connectivityLiveData.observe(this,{ isAvailable ->
            //2
            when (isAvailable) {
                true -> {
                    viewModelClass.postPostToApi(postToSend,newCount)
                }
                false -> {

                }
            }
        })


    }
}