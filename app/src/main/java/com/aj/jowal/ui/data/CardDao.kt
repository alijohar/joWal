package com.aj.jowal.ui.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.aj.jowal.ui.model.Card

@Dao
interface CardDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(card: Card)

    @Query("SELECT * FROM card_table")
    fun getAllcards(): LiveData<List<Card>>

    @Update
    suspend fun updateCard(card:Card)

    @Query("DELETE FROM card_table")
    suspend fun deleteAllCards()

    @Delete
    suspend fun deleteCard(card: Card)

}