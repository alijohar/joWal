package com.aj.jowal.ui

import android.app.Application
import androidx.lifecycle.*
import com.aj.jowal.ui.data.CardDatabase
import com.aj.jowal.ui.model.BankName
import com.aj.jowal.ui.model.Card
import com.aj.jowal.ui.repository.CardRepository
import com.szagurskii.patternedtextwatcher.PatternedTextWatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddCardViewModel(application: Application) : AndroidViewModel(application) {
    val cardNumber = MutableLiveData<String>()
    val cardId = MutableLiveData<String>()
    val cardName = MutableLiveData<String>()
    val expireDate = MutableLiveData<String>()
    val shebaNumber = MutableLiveData<String>()
    val onFinishFillCard = MutableLiveData<Boolean>().apply {
         value = false
     }

    private val repo: CardRepository
    private val allCards: LiveData<List<Card>>

    init {
        val dao = CardDatabase.getDatabase(application).dao()
        repo = CardRepository(dao)
        allCards = repo.allCards
    }

    fun insert() = viewModelScope.launch(Dispatchers.IO) {
        if (!cardNumber.value.isNullOrBlank() &&
            !cardName.value.isNullOrBlank() &&
            !expireDate.value.isNullOrBlank() &&
            !shebaNumber.value.isNullOrBlank()
        ) {
            val card = Card(0, BankName.MELLAT, cardNumber.value!!, cardName.value!!, expireDate.value!!, cardId.value!!, shebaNumber.value!!)
            repo.insert(card)
            onFinishFillCard.postValue(true)
        }
    }

}
