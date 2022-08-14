package com.aj.jowal.ui.model

import android.graphics.drawable.Drawable
import android.os.Parcelable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aj.jowal.ui.adapter.CardsAdapter
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "card_table")
class Card(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val bankName: BankName,
    var cardNumber: String,
    var nameOnCard: String,
    var expireDate: String,
    var cardId: String,
    var shebaNumber: String
): Parcelable