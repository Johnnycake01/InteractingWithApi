package com.example.getpostandpostcomment.mvvm.ui.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.getpostandpostcomment.R
import com.example.getpostandpostcomment.dialogue.action.DialogueListenerInterface
import com.example.getpostandpostcomment.mvvm.model.GetPost
import com.example.getpostandpostcomment.mvvm.model.ViewModelClass
import com.example.getpostandpostcomment.mvvm.model.ViewModelClassFactory
import com.example.getpostandpostcomment.mvvm.repository.Repository
import com.example.getpostandpostcomment.mvvm.ui.adapter.MvvmCommentsAdapter

class MvvmCommentOnPost : AppCompatActivity() {

    private lateinit var rvComments: RecyclerView
    private lateinit var commentTextView: TextView
    private lateinit var rvCommentAdapter: MvvmCommentsAdapter
    private lateinit var commentCount: TextView
    private lateinit var titleOfPost: TextView
    private lateinit var postBody: TextView
    private lateinit var comment: EditText
    private lateinit var post: Button
    private var postId:Int = 0
    private lateinit var viewModelClass: ViewModelClass
    private lateinit var repository: Repository
    private lateinit var viewModelClassFactory: ViewModelClassFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvm_comment_on_post)
        initViews()

        postId = intent.getStringExtra("mvvmpostId")!!.toInt()
        titleOfPost.text = intent.getStringExtra("mvvmpostTitle")
        postBody.text = intent.getStringExtra("mvvmpostBody")

        rvCommentAdapter = MvvmCommentsAdapter()
        rvComments.adapter = rvCommentAdapter
        rvComments.layoutManager = LinearLayoutManager(this)

        viewModelClass.fetchDummyCommentFromApi(postId)
        viewModelClass.listOfComment.observe(this,{
            rvCommentAdapter.addListOfPosts(it)
            commentCount.text = rvCommentAdapter.itemCount.toString()
        })

        commentTextView.setOnClickListener {
            if (rvComments.visibility == View.GONE){
                rvComments.visibility = View.VISIBLE
            }else{
                rvComments.visibility = View.GONE
            }
        }
        viewModelClass.comment.observe(this,{
            rvCommentAdapter.addComment(it)
            Toast.makeText(this@MvvmCommentOnPost, "successful", Toast.LENGTH_SHORT)
                .show()
            commentCount.text = rvCommentAdapter.itemCount.toString()
            comment.text.clear()
        })

        post.setOnClickListener {
            if (comment.text.isNotEmpty() && comment.text != null && comment.text.isNotBlank()){
                viewModelClass.postCommentToApi(postId,comment)

            }
        }
    }

    private fun initViews() {
        rvComments = findViewById(R.id.mvvmRvComments)
        rvComments.visibility = View.GONE
        commentCount = findViewById(R.id.mvvmAllCommentsCount)
        commentTextView = findViewById(R.id.mvvmAllCommentsToggle)
        titleOfPost = findViewById(R.id.mvvmUserPostTitle)
        postBody = findViewById(R.id.mvvmUserPostBody)
        comment = findViewById(R.id.mvvmComment)
        post = findViewById(R.id.mvvmPost)
        repository = Repository()
        viewModelClassFactory = ViewModelClassFactory(repository)
        viewModelClass = ViewModelProvider(this,viewModelClassFactory).get(ViewModelClass::class.java)
    }
}