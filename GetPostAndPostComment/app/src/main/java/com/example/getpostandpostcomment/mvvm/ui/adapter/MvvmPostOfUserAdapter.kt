package com.example.getpostandpostcomment.mvvm.ui.adapter


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.getpostandpostcomment.R
import com.example.getpostandpostcomment.mvvm.model.GetPost
import com.example.getpostandpostcomment.mvvm.ui.mvvm.MvvmCommentOnPost
import com.example.getpostandpostcomment.utility.getFirstAndLastName


import kotlin.collections.ArrayList

class MvvmPostOfUserAdapter(val context: Context):RecyclerView.Adapter<MvvmPostOfUserAdapter.UserPostList>(){
    private val postItems:ArrayList<GetPost> = ArrayList()
    private var id:Int = 0


    inner class UserPostList(viewItem:View):RecyclerView.ViewHolder(viewItem){
        var postTitle:TextView = viewItem.findViewById(R.id.mvvmUserPostTitle)
        var post:TextView = viewItem.findViewById(R.id.mvvmUserPost)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserPostList {
                val view = LayoutInflater.from(parent.context).inflate(
           R.layout.mvvm_post_list_layout,
            parent,false)

        return UserPostList(view)
    }

    override fun onBindViewHolder(holder: UserPostList, position: Int) {
        val itemPosition = postItems[position]
        holder.postTitle.text = getFirstAndLastName(itemPosition.title)
        holder.post.text = itemPosition.body
        val postTitle = getFirstAndLastName(itemPosition.title)
        val postBody = itemPosition.body

        id = itemPosition.id
        holder.itemView.setOnClickListener {
            val intent = Intent(context,MvvmCommentOnPost::class.java)
            intent.putExtra("mvvmpostId",id.toString())
            intent.putExtra("mvvmpostTitle",postTitle)
            intent.putExtra("mvvmpostBody",postBody)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return postItems.size
    }
    fun addListOfPosts(ListOfPost: MutableList<GetPost>) {
        postItems.clear()
        postItems.addAll(ListOfPost)
        notifyDataSetChanged()
    }

    fun addPostToRecyclerView(post: GetPost) {
        postItems.add(post)
        notifyDataSetChanged()
    }
}
