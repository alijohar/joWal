package com.aj.jowal.ui.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_table")
class Card(
    @PrimaryKey(autoGenerate = true)
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


}