package com.aj.jowal.ui.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aj.jowal.R
import com.aj.jowal.databinding.ItemCardBinding
import com.aj.jowal.ui.models.Card
import java.util.*

class CardsAdapter : RecyclerView.Adapter<CardsAdapter.CardsViewHolder>() {

    private var data: List<Card> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsViewHolder {
        val layoutInflator = LayoutInflater.from(parent.context)
        val itemCardBinding = ItemCardBinding.inflate(layoutInflator, parent, false)
        return CardsViewHolder(itemCardBinding)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: CardsViewHolder, position: Int){
        val card = data[position]
        holder.itemCardBinding.card = card
        holder.itemCardBinding.executePendingBindings()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun swapData(data: List<Card>) {
        this.data = data
        notifyDataSetChanged()
    }

    class CardsViewHolder(var itemCardBinding: ItemCardBinding) :
        RecyclerView.ViewHolder(itemCardBinding.root) {
    }
}