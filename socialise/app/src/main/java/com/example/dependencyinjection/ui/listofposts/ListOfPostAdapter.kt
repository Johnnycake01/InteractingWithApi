package com.example.dependencyinjection.ui.listofposts



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.dependencyinjection.R
import com.example.dependencyinjection.model.data.UserDetail
import com.example.dependencyinjection.model.data.UserPost
import com.example.dependencyinjection.utils.arrayOfImages


class ListOfPostAdapter:RecyclerView.Adapter<ListOfPostAdapter.ListOfPostViewHolder>() {
    private var itemList:ArrayList<UserPost> = arrayListOf()
    private var userDetailList:ArrayList<UserDetail> = arrayListOf()
    private val arrayOfImagesFromConstants = arrayOfImages
    inner class ListOfPostViewHolder(items: View):RecyclerView.ViewHolder(items){
        val txBody:TextView = items.findViewById(R.id.tvUserPostBody)
        val txName:TextView = items.findViewById(R.id.tvUserId)
        val imgView:ImageView = items.findViewById(R.id.rvUserImage)
        val commentLayout: LinearLayout = items.findViewById(R.id.containerComment)
        val likeCount:TextView = items.findViewById(R.id.likeCount)
        //val commentCount:TextView = items.findViewById(R.id.commentCount)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListOfPostViewHolder {
      val binding = LayoutInflater.from(parent.context)
          .inflate(R.layout.list_of_posts_layout_view,parent,false)
        return ListOfPostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListOfPostViewHolder, position: Int) {
        val itemPosition = itemList[position]
        val randomPosition = (0..9).random()
        val rnd = (0..20).random()
        val userPosition = userDetailList[randomPosition]
        val imagePosition = arrayOfImagesFromConstants[randomPosition]
        holder.txBody.text = itemPosition.body
        holder.txName.text = userPosition.name
        holder.imgView.setBackgroundResource(imagePosition)
        holder.likeCount.text = rnd.toString()


        holder.commentLayout.setOnClickListener {
            val action = ShowListDirections.actionShowListToAddNewCommentBlankFragment(itemPosition,
                imagePosition,1,userPosition)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int = itemList.size


    fun addAllItemToList(it: List<UserPost>) {
        itemList.clear()
        itemList.addAll(it)
        notifyDataSetChanged()
    }

    fun addAllUserDetailsToList(it: List<UserDetail>) {
        userDetailList.clear()
        userDetailList.addAll(it)
        notifyDataSetChanged()
    }
}