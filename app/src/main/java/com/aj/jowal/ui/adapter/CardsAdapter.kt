package com.aj.jowal.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.aj.jowal.Helper
import com.aj.jowal.R
import com.aj.jowal.databinding.ItemCardBinding
import com.aj.jowal.ui.UpdateCardActivity
import com.aj.jowal.ui.model.BankName
import com.aj.jowal.ui.model.Card
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.switchmaterial.SwitchMaterial
import com.szagurskii.patternedtextwatcher.PatternedTextWatcher
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
        val first6Number = card.cardNumber.substring(0,6)

        val drawableResource = context.resources.getIdentifier(
            (BankName.from(first6Number).name).lowercase(), "drawable",
            context.packageName
        )

        val colorResource = context.resources.getIdentifier(
            (BankName.from(first6Number).name), "color",
            context.packageName
        )

        holder.itemCardBinding.card = card
        holder.itemCardBinding.cardNumber.addTextChangedListener(PatternedTextWatcher("####    ####    ####    ####"))
        holder.itemCardBinding.expireDate.addTextChangedListener(PatternedTextWatcher("##   ##"))
        if (drawableResource != 0) {
            holder.itemCardBinding.bankImage.setImageResource(drawableResource)
            holder.itemCardBinding.bk.setImageResource(drawableResource)

        }
        if (colorResource != 0) {
            holder.itemCardBinding.cardView.setCardBackgroundColor(
                context.getColor(
                    colorResource
                )
            )

        }
        holder.itemCardBinding.executePendingBindings()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun swapData(data: List<Card>) {
        this.data = data

        Collections.sort(data,
            Comparator<Card?> { lhs, rhs -> lhs.nameOnCard.compareTo(rhs.nameOnCard) })
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
                val cardNumberSiwtch = bottomSheetDialog.findViewById<SwitchMaterial>(R.id.card_number_switch)
                val nameSwitch =
                    bottomSheetDialog.findViewById<SwitchMaterial>(R.id.name_on_card_switch)
                val expireSwitch =
                    bottomSheetDialog.findViewById<SwitchMaterial>(R.id.expire_date_switch)
                val shibaSwitch = bottomSheetDialog.findViewById<SwitchMaterial>(R.id.shiba_switch)
                val shareAsTextBu = bottomSheetDialog.findViewById<Button>(R.id.share_text)
                val shareAsImageBu = bottomSheetDialog.findViewById<Button>(R.id.share_image)

                if (cardNumberSiwtch!!.isChecked){
                    finalCardItemToShare += "\n Card number: " + card.cardNumber
                }

                if (cardIdSwitch!!.isChecked) {
                    finalCardItemToShare += "\n Card id: " + card.cardId
                }

                if (nameSwitch!!.isChecked) {
                    finalCardItemToShare += "\n" + card.nameOnCard
                }

                if (expireSwitch!!.isChecked) {
                    finalCardItemToShare += "\n Expire date: " + card.expireDate
                }

                if (shibaSwitch!!.isChecked) {
                    finalCardItemToShare += "\n Sheba number: " + card.shebaNumber
                }


                cardIdSwitch.setOnCheckedChangeListener { _, b ->
                    if (b && !finalCardItemToShare.contains("\n Card id: ${card.cardId}")){
                        finalCardItemToShare += "\n Card id: " + card.cardId
                    }else{
                        finalCardItemToShare = finalCardItemToShare.replace(" Card id: " + card.cardId, "")
                    }
                }

                cardNumberSiwtch.setOnCheckedChangeListener { _, b ->
                    if (b && !finalCardItemToShare.contains("\n Card number: ${card.cardNumber}")){
                        finalCardItemToShare += "\n Card number: " + card.cardNumber
                    }else{
                        finalCardItemToShare = finalCardItemToShare.replace(" Card number: " + card.cardNumber, "")
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
                    if (b && !finalCardItemToShare.contains("\n Expire date: ${card.expireDate}")){
                        finalCardItemToShare += "\n Expire date: " + card.expireDate
                    }else{
                        finalCardItemToShare = finalCardItemToShare.replace(" Expire date: " + card.expireDate, "")
                    }
                }

                shibaSwitch.setOnCheckedChangeListener { _, b ->
                    if (b && !finalCardItemToShare.contains("\n Sheba number: ${card.shebaNumber}")){
                        finalCardItemToShare += "\n Sheba number: " + card.shebaNumber
                    }else{
                        finalCardItemToShare = finalCardItemToShare.replace(" Sheba number: " + card.shebaNumber, "")
                    }
                }

                shareAsTextBu!!.setOnClickListener{
                    Helper.shareText(context, context.getString(R.string.card_info), finalCardItemToShare)
                }

                bottomSheetDialog.show()
            }
            itemCardBinding.cardView.setOnClickListener {
                val intent = Intent(context, UpdateCardActivity::class.java)
                intent.putExtra("UPDATE_CARD", data[bindingAdapterPosition])
                context.startActivity(intent)
            }
        }
    }
}