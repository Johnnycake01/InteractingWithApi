package com.example.getpostandpostcomment.mvc.ui.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.getpostandpostcomment.R
import com.example.getpostandpostcomment.mvc.ui.mvc.DisplayUserPosts
import java.util.*
import kotlin.collections.ArrayList

class ListOfUsersAdapter(val context:Context):RecyclerView.Adapter<ListOfUsersAdapter.ListViewHolder>() {
    val items:ArrayList<Int> = ArrayList()
    inner class ListViewHolder(viewItem: View): RecyclerView.ViewHolder(viewItem){
    val colorUI:View = viewItem.findViewById(R.id.viewColor)
        val userId:TextView = viewItem.findViewById(R.id.userId)
        val colorUiEnd:View = viewItem.findViewById(R.id.viewColorEnd)


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.mvc_user_list_layout,
           parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val itemPosition = items[position]
        val rnd = Random()
        val genColor: Int = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        holder.colorUI.setBackgroundColor(genColor)
        holder.colorUiEnd.setBackgroundColor(genColor)

        holder.userId.text = itemPosition.toString()
        holder.itemView.setOnClickListener {
            val intent = Intent(context,DisplayUserPosts::class.java)
            intent.putExtra("UserId",itemPosition.toString())
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addListOfUsers(uniqueSetOfList: MutableSet<Int>) {
        items.clear()
       items.addAll(uniqueSetOfList)
        notifyDataSetChanged()
    }

}