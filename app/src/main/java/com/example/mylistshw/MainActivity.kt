package com.example.mylistshw

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class MainActivity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        val listView:RecyclerView = findViewById(R.id.listView)
    }
}
data class HeroesResponse(val heroes:List<Hero>)
data class Hero(val name:String, val images: ImagesBlock)

data class ImagesBlock(val xs:String, val sm:String, val md:String, val lg:String)