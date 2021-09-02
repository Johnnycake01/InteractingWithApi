package com.example.getpostandpostcomment.mvc.ui.mvc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.getpostandpostcomment.R
import com.example.getpostandpostcomment.mvc.model.GetComments
import com.example.getpostandpostcomment.mvc.network.RetrofitInstance
import com.example.getpostandpostcomment.mvc.ui.adapter.CommentsAdapter
import com.example.getpostandpostcomment.utility.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CommentOnPost : AppCompatActivity() {
    private lateinit var rvComments: RecyclerView
    private lateinit var commentTextView: TextView
    private lateinit var rvCommentAdapter: CommentsAdapter
    private lateinit var commentCount: TextView
    private lateinit var titleOfPost:TextView
    private lateinit var postBody:TextView
    private lateinit var comment:EditText
    private lateinit var post:Button
    private var postId:Int = 0
//    private lateinit var model: ViewModelClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment_on_post)
        rvComments = findViewById(R.id.rvComments)
        rvComments.visibility = View.GONE
        commentCount = findViewById(R.id.allCommentsCount)
        commentTextView = findViewById(R.id.allCommentsToggle)
        titleOfPost = findViewById(R.id.userPostTitle)
        postBody = findViewById(R.id.userPostBody)
        comment = findViewById(R.id.comment)
        post = findViewById(R.id.post)
        
        postId = intent.getStringExtra("postId")!!.toInt()
        titleOfPost.text = intent.getStringExtra("postTitle")
        postBody.text = intent.getStringExtra("postBody")

        rvCommentAdapter = CommentsAdapter()
        rvComments.adapter = rvCommentAdapter
        rvComments.layoutManager = LinearLayoutManager(this)

        post.setOnClickListener {

            if (comment.text.isNotEmpty() && comment.text != null && comment.text.isNotBlank()){
                GlobalScope.launch(Dispatchers.IO) {
                    val comments = RetrofitInstance.retrofitBuilder().postComment(postId)
                    if (comments.isSuccessful) {
                        val commentBody = comments.body()
                        if (commentBody != null) {
                            val postIdd = commentBody.userId
                            val idd = commentBody.id

                            withContext(Dispatchers.Main) {
                                val commentData = GetComments(
                                    name = "Oyesina Johnson",
                                    postId = postIdd,
                                    body = comment.text.toString(),
                                    email = "oyesinajohnson@gmail.com",
                                    id = idd
                                )
                                rvCommentAdapter.addComment(commentData)
                                Toast.makeText(this@CommentOnPost, "successful", Toast.LENGTH_SHORT)
                                    .show()
                                commentCount.text = rvCommentAdapter.itemCount.toString()
                                comment.text.clear()

                            }
                        }

                    }
                }
            }
        }

        commentTextView.setOnClickListener {
            if (rvComments.visibility == View.GONE){
                rvComments.visibility = View.VISIBLE
            }else{
                rvComments.visibility = View.GONE
            }
        }
        Log.d(TAG, "onCreate: $postId")

        GlobalScope.launch(Dispatchers.IO) {
            val comments = RetrofitInstance.retrofitBuilder().getComments(postId)
            if (comments.isSuccessful){
                val commentBody = comments.body()
                if (commentBody != null){
                    val listOfComments:List<GetComments> = commentBody


                    withContext(Dispatchers.Main){
                        rvCommentAdapter.addListOfPosts(listOfComments)
                        commentCount.text = rvCommentAdapter.itemCount.toString()

                    }
                }

            }
        }


    }
}