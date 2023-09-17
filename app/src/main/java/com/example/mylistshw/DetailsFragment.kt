package com.example.mylistshw

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class DetailsFragment:Fragment() {

    var fullName: TextView? = null
    var alterEgos: TextView? = null
    var placeOfBirth: TextView? = null
    var firstAppearance: TextView? = null
    var publisher: TextView? = null
    var alignment: TextView? = null
    var aliases: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_layout, container, false)
    }

}