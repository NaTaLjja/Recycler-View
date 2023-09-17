package com.example.mylistshw

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_layout)
        val listView:RecyclerView = findViewById(R.id.listView)
        val api = ApiClient.client.create(ApiInterface::class.java)
        api.getHeroes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                       if(it.isNotEmpty()){
                           val items = it
                           val myAdapter = RecyclerViewAdapter(items)
                           listView.adapter = myAdapter
                       }

            },{

                Toast.makeText(this, "Error",Toast.LENGTH_SHORT).show()
            })
        listView.layoutManager = LinearLayoutManager(this)
        listView.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))
    }
}
data class HeroesResponse(val heroes:List<Hero>)
data class Hero(val name:String, val work:WorkBlock, val images: ImagesBlock)

data class ImagesBlock(val xs:String, val sm:String, val md:String, val lg:String)
data class WorkBlock(val occupation:String, val base:String)