package com.aj.jowal.ui

import android.app.AlertDialog
import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.aj.jowal.ui.data.CardDatabase
import com.aj.jowal.ui.model.BankName
import com.aj.jowal.ui.model.Card
import com.aj.jowal.ui.repository.CardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.suspendCoroutine

class UpdateCardViewModel(application: Application) : AndroidViewModel(application) {
    val cardNumber = MutableLiveData<String>()
    val cardId = MutableLiveData<String>()
    val cardName = MutableLiveData<String>()
    val expireDate = MutableLiveData<String>()
    val shebaNumber = MutableLiveData<String>()
    val onFinishFillCard = MutableLiveData<Boolean>().apply {
        value = false
    }
    val onDeleteItemClicked = MutableLiveData<Boolean>().apply {
        value = false
    }
    val card = MutableLiveData<Card>()

    private val repo: CardRepository
    private val allCards: LiveData<List<Card>>

    init {
        val dao = CardDatabase.getDatabase(application).dao()
        repo = CardRepository(dao)
        allCards = repo.allCards

    }
    fun update() = viewModelScope.launch(Dispatchers.IO) {
        if (!cardNumber.value.isNullOrBlank() &&
            !cardName.value.isNullOrBlank() &&
            !expireDate.value.isNullOrBlank() &&
            !shebaNumber.value.isNullOrBlank()
        ) {
            val cardAfterUpdate = card.value
            cardAfterUpdate!!.cardId = cardId.value!!
            cardAfterUpdate.nameOnCard = cardName.value!!
            cardAfterUpdate.expireDate = expireDate.value!!
            cardAfterUpdate.shebaNumber = shebaNumber.value!!
            cardAfterUpdate.cardNumber = cardNumber.value!!

            repo.update(cardAfterUpdate)
            onFinishFillCard.postValue(true)
        }
    }


    fun delete() = viewModelScope.launch(Dispatchers.IO) {
        card.value?.let { repo.delete(it) }
        onFinishFillCard.postValue(true)
    }

    fun alertForDelete(view:View){
        onDeleteItemClicked.value = true
    }

     fun updateFields() {
        cardId.value = card.value?.cardId
        cardNumber.value = card.value?.cardNumber
        shebaNumber.value = card.value?.shebaNumber
        expireDate.value = card.value?.expireDate
        cardName.value = card.value?.nameOnCard
    }
}