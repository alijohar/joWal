package com.aj.jowal.ui.models

import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.net.URL

class Card(
    val id: Int,
    val bankLogo: Int,
    val cardNumber: String,
    val nameOnCard: String,
    val expireDate: String,
    val cardId: String,
    val shebaNumber: String
) {
    fun checkCard(): Boolean{
        return cardNumber.contains("6037")
    }

    companion object{
    @JvmStatic
     @BindingAdapter("android:loadImage")
     fun loadImage(imageView: ImageView, imageURL: Int){
//         imageView.setImageResource(imageURL)
     }


    }
}