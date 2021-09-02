package com.example.getpostandpostcomment.mvc.ui.mvc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.getpostandpostcomment.R
import com.example.getpostandpostcomment.mvc.ui.adapter.PostOfUserAdapter
import com.example.getpostandpostcomment.mvc.model.GetPost
import com.example.getpostandpostcomment.mvc.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DisplayUserPosts : AppCompatActivity() {
    private lateinit var rvAdapter: PostOfUserAdapter
    private lateinit var rv: RecyclerView
    private  var userIdFromIntent:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_user_posts)
        rv = findViewById(R.id.rvPostOfUsers)


        rvAdapter = PostOfUserAdapter(this)


        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(this)



        userIdFromIntent = intent.getStringExtra("UserId")!!.toInt()


        GlobalScope.launch(Dispatchers.IO) {
            val posts = RetrofitInstance.retrofitBuilder().getPostFunction()
            if (posts.isSuccessful) {
                val postBody = posts.body()
                if (postBody != null) {
                    var ListOfPost: ArrayList<GetPost> = ArrayList()
                    for (post in postBody) {
                        if (post.userId == userIdFromIntent)
                            ListOfPost.add(post)
                    }

                    withContext(Dispatchers.Main) {
                        rvAdapter.addListOfPosts(ListOfPost)
                    }
                }

            }
        }
    }

}