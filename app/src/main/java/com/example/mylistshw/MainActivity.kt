package com.example.mylistshw

import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import kotlinx.parcelize.Parcelize

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

        val listFragment = supportFragmentManager.findFragmentById(R.id.listFragment) as ListFragment

        listFragment.setItemClickListener {
            val detailsFragment = supportFragmentManager.findFragmentById(R.id.detailsFragment) as? DetailsFragment
            var bundle = Bundle()
            bundle.putParcelable(PARCELABLE_VALUE, it)

            if(detailsFragment != null){
                // detailsFragment.show()
            } else{
                val detailsFragment = DetailsFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.listFragment, DetailsFragment::class.java, bundle)
                    .addToBackStack("details_fragment")
                    .commit()
            }
        }
    }
    companion object{
        const val PARCELABLE_VALUE = "parcelableValue"
    }
}
@Parcelize
data class Hero(val name:String?, val work:WorkBlock, val images: ImagesBlock, val biography: Biography):Parcelable
@Parcelize
data class ImagesBlock(val xs:String, val sm:String, val md:String, val lg:String):Parcelable
@Parcelize
data class WorkBlock(val occupation:String, val base:String):Parcelable
@Parcelize
data class Biography(val fullName:String, val alterEgos:String, val aliases: Array<String>,
                     val placeOfBirth: String, val firstAppearance: String, val publisher: String, val alignment: String):Parcelable