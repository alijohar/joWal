package com.aj.jowal.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.aj.jowal.Helper
import com.aj.jowal.R
import com.aj.jowal.databinding.ItemCardBinding
import com.aj.jowal.ui.model.Card
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.switchmaterial.SwitchMaterial
import java.util.*


class CardsAdapter(var context: Context) : RecyclerView.Adapter<CardsAdapter.CardsViewHolder>() {

    private var data: List<Card> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsViewHolder {
        val layoutInflator = LayoutInflater.from(parent.context)
        val itemCardBinding = ItemCardBinding.inflate(layoutInflator, parent, false)
        return CardsViewHolder(itemCardBinding)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: CardsViewHolder, position: Int) {
        val card = data[position]
        holder.itemCardBinding.card = card
        holder.itemCardBinding.executePendingBindings()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun swapData(data: List<Card>) {
        this.data = data
        notifyDataSetChanged()
    }

    inner class CardsViewHolder(var itemCardBinding: ItemCardBinding) :
        RecyclerView.ViewHolder(itemCardBinding.root) {
        init {
            itemCardBinding.share.setOnClickListener {
                var finalCardItemToShare: String = ""
                val card = data[bindingAdapterPosition]

                val bottomSheetDialog = BottomSheetDialog(context)
                bottomSheetDialog.setContentView(R.layout.bottom_sheet)
                val cardIdSwitch =
                    bottomSheetDialog.findViewById<SwitchMaterial>(R.id.card_id_switch)
                val nameSwitch =
                    bottomSheetDialog.findViewById<SwitchMaterial>(R.id.name_on_card_switch)
                val expireSwitch =
                    bottomSheetDialog.findViewById<SwitchMaterial>(R.id.expire_date_switch)
                val shibaSwitch = bottomSheetDialog.findViewById<SwitchMaterial>(R.id.shiba_switch)
                val shareAsTextBu = bottomSheetDialog.findViewById<Button>(R.id.share_text)
                val shareAsImageBu = bottomSheetDialog.findViewById<Button>(R.id.share_image)

                if (cardIdSwitch!!.isChecked) {
                    finalCardItemToShare += "\n" + card.cardId
                }

                if (nameSwitch!!.isChecked) {
                    finalCardItemToShare += "\n" + card.nameOnCard
                }

                if (expireSwitch!!.isChecked) {
                    finalCardItemToShare += "\n" + card.expireDate
                }

                if (shibaSwitch!!.isChecked) {
                    finalCardItemToShare += "\n" + card.shebaNumber
                }


                cardIdSwitch.setOnCheckedChangeListener { _, b ->
                    if (b && !finalCardItemToShare.contains("\n${card.cardId}")){
                        finalCardItemToShare += "\n" + card.cardId
                    }else{
                        finalCardItemToShare = finalCardItemToShare.replace(card.cardId, "")
                    }
                }

                nameSwitch.setOnCheckedChangeListener { _, b ->
                    if (b && !finalCardItemToShare.contains("\n${card.nameOnCard}")){
                        finalCardItemToShare += "\n" + card.nameOnCard
                    }else{
                        finalCardItemToShare = finalCardItemToShare.replace(card.nameOnCard, "")
                    }
                }


                expireSwitch.setOnCheckedChangeListener { _, b ->
                    if (b && !finalCardItemToShare.contains("\n${card.expireDate}")){
                        finalCardItemToShare += "\n" + card.expireDate
                    }else{
                        finalCardItemToShare = finalCardItemToShare.replace(card.expireDate, "")
                    }
                }

                shibaSwitch.setOnCheckedChangeListener { _, b ->
                    if (b && !finalCardItemToShare.contains("\n${card.shebaNumber}")){
                        finalCardItemToShare += "\n" + card.shebaNumber
                    }else{
                        finalCardItemToShare = finalCardItemToShare.replace(card.shebaNumber, "")
                    }
                }

                shareAsTextBu!!.setOnClickListener{
                    Helper.shareText(context, context.getString(R.string.card_info), finalCardItemToShare)
                }

                bottomSheetDialog.show()
            }
        }
    }
}