package com.example.getpostandpostcomment.mvvm.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.getpostandpostcomment.R
import com.example.getpostandpostcomment.mvvm.model.Users
import com.example.getpostandpostcomment.mvvm.ui.mvvm.MvvmDisplayUserPosts

import kotlin.collections.ArrayList

class MvvmListOfUsersAdapter(val context:Context):RecyclerView.Adapter<MvvmListOfUsersAdapter.ListViewHolder>() {
    val items:ArrayList<Users> = ArrayList()
    inner class ListViewHolder(viewItem: View): RecyclerView.ViewHolder(viewItem){
        val userImage: ImageView = viewItem.findViewById(R.id.ivUserImageReal)
        val userName:TextView = viewItem.findViewById(R.id.tvUserId)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.mvvm_list_of_user_rv_layout,
           parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val itemPosition = items[position]
        holder.userImage.setBackgroundResource(R.drawable.my_image)
        holder.userName.text = itemPosition.name
        holder.itemView.setOnClickListener {
            val intent = Intent(context, MvvmDisplayUserPosts::class.java)
            intent.putExtra("MvvmUserId",itemPosition.id.toString())
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addListOfUsers(listOfUsers: List<Users>) {
        items.clear()
       items.addAll(listOfUsers)
        notifyDataSetChanged()
    }

}