package com.example.mylistshw

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerViewAdapter(private val items:List<Hero>):RecyclerView.Adapter<RecycleViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleViewHolder {
        val listItemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_layout,parent, false)
        return RecycleViewHolder(listItemView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecycleViewHolder, position: Int) {
        holder.name.text = items[position].name
        Glide.with(holder.itemView)
            .load(items[position].images)
            .into(holder.image)
    }
}

class RecycleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val name:TextView = itemView.findViewById(R.id.name)
    val image: ImageView = itemView.findViewById(R.id.image)
}