package com.example.enqueuepractice.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.enqueuepractice.R

class  ViewPagerClass( private var names: Context):
        RecyclerView.Adapter<ViewPagerClass.ViewPagerHolder>() {
    private var contents:ArrayList<String> = ArrayList()

        inner class ViewPagerHolder(viewItems: View): RecyclerView.ViewHolder(viewItems){
            val imageView = viewItems.findViewById<ImageView>(R.id.imageView)

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.view_pager_design,parent,false)
            return ViewPagerHolder(view)
        }

        override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
            val callContents = contents[position]
            Glide.with(names)
                .load(callContents)
                .centerCrop()
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView)
        }

        override fun getItemCount(): Int {
            return contents.size
        }

    fun additionalListOfPokemon(arrayOfResponse: List<String?>) {
        for (i in arrayOfResponse){
            if (i != null){
                contents.add(i)
            }
        }
        notifyDataSetChanged()//notify adapter of any changes either adding or substracting from the list
    }


}
