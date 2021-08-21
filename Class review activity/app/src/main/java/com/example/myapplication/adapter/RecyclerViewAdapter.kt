package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.myapplication.R
import com.example.myapplication.model.Results
import com.example.myapplication.singleton.RetrofitInstance

class RecyclerViewAdapter(val context: Context): RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>(){
    val pokemonList:ArrayList<Results> = ArrayList()
   inner class RecyclerViewHolder(pokemonItems:View):RecyclerView.ViewHolder(pokemonItems){
        val pokemonName = pokemonItems.findViewById<TextView>(R.id.tvTextName)
        val pokemonNumber = pokemonItems.findViewById<TextView>(R.id.tvTextNumber)
        val pokemonImageName = pokemonItems.findViewById<ImageView>(R.id.ivImageName)
        val pokemonImageNumber = pokemonItems.findViewById<ImageView>(R.id.ivImageNumber)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.poke_layout,
            parent,false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val itemPosition = pokemonList[position]
        val name = itemPosition.name
        val url = itemPosition.url
        val getUrlNumber = RetrofitInstance.getNumberFromUrl(url)
        holder.pokemonName.text = name
        holder.pokemonNumber.text = getUrlNumber.toString()

        Glide.with(context)
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${getUrlNumber}.png")
            .centerCrop()
            .circleCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.pokemonImageNumber)

        Glide.with(context)
            .load("https://img.pokemondb.net/artwork/large/$name.jpg")
            .centerCrop()
            .circleCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.pokemonImageName)


    }

    override fun getItemCount(): Int {
       return pokemonList.size
    }

    fun assignValueToArrayInAdapter(arrayOfResult: List<Results>) {
        pokemonList.clear()
        pokemonList.addAll(arrayOfResult)
        notifyDataSetChanged()
    }
}