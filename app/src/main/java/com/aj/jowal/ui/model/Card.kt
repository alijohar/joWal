package com.aj.jowal.ui.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_table")
class Card(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val bankName: BankName,
    val cardNumber: String,
    val nameOnCard: String,
    val expireDate: String,
    val cardId: String,
    val shebaNumber: String
)