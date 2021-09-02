package com.example.getpostandpostcomment.mvvm.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.getpostandpostcomment.R
import com.example.getpostandpostcomment.mvvm.model.GetComments
import com.example.getpostandpostcomment.utility.getFirstAndLastName

class MvvmCommentsAdapter:RecyclerView.Adapter<MvvmCommentsAdapter.UserCommentsList>(){
    private val postItems:ArrayList<GetComments> = ArrayList()


    inner class UserCommentsList(viewItem: View): RecyclerView.ViewHolder(viewItem){
        var userName:TextView = viewItem.findViewById(R.id.mvvmUserName)
        var userEmail:TextView = viewItem.findViewById(R.id.mvvmUserEmail)
        val userComment:TextView = viewItem.findViewById(R.id.mvvmUserComments)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserCommentsList {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.mvvm_comment_layout,
            parent,false)
        return UserCommentsList(view)
    }

    override fun onBindViewHolder(holder: UserCommentsList, position: Int) {
        val itemPosition = postItems[position]
        holder.userName.text = getFirstAndLastName(itemPosition.name)
        holder.userEmail.text = itemPosition.email
        holder.userComment.text = itemPosition.body

    }

    override fun getItemCount(): Int {

        return postItems.size
    }
    fun addListOfPosts(ListOfComments: List<GetComments>) {
        postItems.clear()
        postItems.addAll(ListOfComments)
        notifyDataSetChanged()
    }
    fun addComment(comment:GetComments) {
        postItems.add(comment)
        notifyDataSetChanged()
    }
}