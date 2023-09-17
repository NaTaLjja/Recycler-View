package com.example.mylistshw

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.Collections

class ListFragment: Fragment() {

    private var onItemClick: (Hero) -> Unit = {}
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recycler_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var myAdapter = RecyclerViewAdapter(onItemClick = {})
        super.onViewCreated(view, savedInstanceState)
        val listRecycler: RecyclerView = view.findViewById(R.id.listRecycler)
        val api = ApiClient.client.create(ApiInterface::class.java)
        api.getHeroes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.isNotEmpty()) {
                    val items = it
                    myAdapter = RecyclerViewAdapter(items as MutableList<Hero>) {
                        onItemClick(it)
                    }
                    listRecycler.adapter = myAdapter
                }

            }, {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            })
        listRecycler.layoutManager = LinearLayoutManager(requireContext())
        listRecycler.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback(){
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                return makeMovementFlags(ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.END)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val fromIndex = viewHolder.adapterPosition
                val toIndex = target.adapterPosition
                Collections.swap(myAdapter.items, fromIndex, toIndex)
                myAdapter.notifyItemMoved(fromIndex,toIndex)
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if(direction == ItemTouchHelper.END){
                    myAdapter.items.removeAt(viewHolder.adapterPosition)
                    myAdapter.notifyItemRemoved(viewHolder.adapterPosition)
                }
            }

        })

        itemTouchHelper.attachToRecyclerView(listRecycler)
    }
    fun setItemClickListener(lambda: (Hero) -> Unit){
        onItemClick = lambda
    }
}