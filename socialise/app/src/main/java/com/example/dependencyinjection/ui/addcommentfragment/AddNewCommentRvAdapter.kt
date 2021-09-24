package com.example.dependencyinjection.ui.addcommentfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dependencyinjection.R
import com.example.dependencyinjection.model.data.Comments
import com.example.dependencyinjection.utils.arrayOfImages


class AddNewCommentRvAdapter:RecyclerView.Adapter<AddNewCommentRvAdapter.NewCommentAdapterViewHolder>() {
    private val arrayOfImagesFromConstants = arrayOfImages
    private var commentList:ArrayList<Comments> = arrayListOf()

    inner class NewCommentAdapterViewHolder(view: View):RecyclerView.ViewHolder(view){
        val commentTitle:TextView = view.findViewById(R.id.txCommentTitle)
        val commentBody:TextView = view.findViewById(R.id.txCommentBody)
        val image:ImageView = view.findViewById(R.id.rvCommentUserImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewCommentAdapterViewHolder {
      val bind = LayoutInflater.from(parent.context).inflate(R.layout.list_of_comment_recycler_view_layout,parent,false)
        return NewCommentAdapterViewHolder(bind)
    }

    override fun onBindViewHolder(holder: NewCommentAdapterViewHolder, position: Int) {
        val itemPosition = commentList[position]
            holder.commentTitle.text = itemPosition.name
            holder.commentBody.text = itemPosition.body
            val randomPosition = (0..9).random()
            val imagePosition = arrayOfImagesFromConstants[randomPosition]
            holder.image.setBackgroundResource(imagePosition)
    }

    override fun getItemCount(): Int = commentList.size
    fun addAllCommentsToList(it: List<Comments>) {
        commentList.clear()
        commentList.addAll(it)
        notifyDataSetChanged()
    }

    fun addtoList(newComment: Comments) {
        commentList.add(newComment)
        notifyDataSetChanged()
    }
}