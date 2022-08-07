package com.aj.jowal.ui.repository

import androidx.lifecycle.LiveData
import com.aj.jowal.ui.data.CardDao
import com.aj.jowal.ui.model.Card

class CardRepository(private val cardDao: CardDao) {

    val allCards: LiveData<List<Card>> = cardDao.getAllcards()

    suspend fun insert(card:Card){
        cardDao.insert(card)
    }
}