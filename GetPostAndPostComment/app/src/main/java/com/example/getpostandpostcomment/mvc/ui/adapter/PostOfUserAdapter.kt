package com.example.getpostandpostcomment.mvc.ui.adapter


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.getpostandpostcomment.R
import com.example.getpostandpostcomment.mvc.model.GetPost
import com.example.getpostandpostcomment.mvc.ui.mvc.CommentOnPost
import com.example.getpostandpostcomment.utility.getFirstAndLastName


import kotlin.collections.ArrayList

class PostOfUserAdapter(val context: Context):RecyclerView.Adapter<PostOfUserAdapter.UserPostList>(){
    private val postItems:ArrayList<GetPost> = ArrayList()
    private var id:Int = 0


    inner class UserPostList(viewItem:View):RecyclerView.ViewHolder(viewItem){
        var postTitle:TextView = viewItem.findViewById(R.id.userPostTitle)
        var post:TextView = viewItem.findViewById(R.id.userPost)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserPostList {
                val view = LayoutInflater.from(parent.context).inflate(
           R.layout.mvc_user_post_list,
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
            val intent = Intent(context,CommentOnPost::class.java)
            intent.putExtra("postId",id.toString())
            intent.putExtra("postTitle",postTitle)
            intent.putExtra("postBody",postBody)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return postItems.size
    }
    fun addListOfPosts(ListOfPost: ArrayList<GetPost>) {
        postItems.clear()
        postItems.addAll(ListOfPost)
        notifyDataSetChanged()
    }
}

//
//class PostOfUserAdapter (val context: Context): RecyclerView.Adapter<PostOfUserAdapter.PostViewHolder>() {
//    val items:ArrayList<GetPost> = ArrayList()
//    inner class PostViewHolder(viewItem: View): RecyclerView.ViewHolder(viewItem){
//
//
//
//
//    }
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(
//            R.layout.activity_display_user_posts,
//            parent,false)
//        return PostViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

//        val rnd = Random()
//        val genColor: Int = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
//        holder.colorUI.setBackgroundColor(genColor)


//
////        holder.itemView.setOnClickListener {
//////            val intent = Intent(context, DisplayUserPosts::class.java)
//////            intent.putExtra("UserId",itemPosition.toString())
//////            context.startActivity(intent)
////        }
//
//    }
