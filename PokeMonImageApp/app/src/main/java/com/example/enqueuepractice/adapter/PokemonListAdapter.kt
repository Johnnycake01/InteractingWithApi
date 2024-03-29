package com.example.enqueuepractice.adapter

import android.content.Context
import android.content.Intent
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.enqueuepractice.R
import com.example.enqueuepractice.objects.FunctionAndValidation
import com.example.enqueuepractice.model.Result
import com.example.enqueuepractice.ui.DisplayPokemonDetails
import java.util.logging.Handler


class PokemonListAdapter(private val context:Context):
    RecyclerView.Adapter<PokemonListAdapter.ContactHolder>(){
    private val listItem:ArrayList<Result> = ArrayList()
//    val progressImage:ProgressBar = findViewById(R.id.progressingImage)


    //inner class of recycler view that extends View holder
    inner class ContactHolder(contactView: View):RecyclerView.ViewHolder(contactView){
       val postId:ImageView = contactView.findViewById(R.id.Images)
        val postTitle:TextView = contactView.findViewById(R.id.titleText)
        //val progressImage:ProgressBar = contactView.findViewById(R.id.progressingImage)
    }

    //on create of view holder the recyler view adapter should inflate the view layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val view = LayoutInflater.from(parent.context).
        inflate(R.layout.post_list_item_layout,parent,false)
        return  ContactHolder(view)
    }

    //this binds the view together
    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        val itemPosition = listItem[position]
        holder.postTitle.text = itemPosition.name

        val name = itemPosition.name //get current pokemon name
        val currentUrl = itemPosition.url//get current pokemon url

        //function to extract number from url
        val getImageNumber = FunctionAndValidation.getNumber(currentUrl)

        //using glide to display image
        Glide.with(context)
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${getImageNumber}.png")
            .centerCrop()
            .circleCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.postId)

        //set onclick listener to each views
        holder.itemView.setOnClickListener {
            val intent = Intent(context, DisplayPokemonDetails::class.java)
            intent.putExtra("positionName", name )
            intent.putExtra("position",position.toString())
            context.startActivity(intent)
        }
    }
    fun removeProgressBar(view:View){
        view.visibility = View.GONE

    }

    //function to get count of item
    override fun getItemCount(): Int {
      return listItem.size
    }

    //function that adds list of pokemon to adapter
    fun additionalListOfPokemon(arrayOfResponse: List<Result>) {
        listItem.clear()
        listItem.addAll(arrayOfResponse)//add all element
        notifyDataSetChanged()//notify adapter of any changes either adding or substracting from the list
    }

}