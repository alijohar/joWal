package com.aj.jowal.ui

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.*
import com.aj.jowal.ui.data.CardDao
import com.aj.jowal.ui.data.CardDatabase
import com.aj.jowal.ui.model.Card
import com.aj.jowal.ui.repository.CardRepository
import kotlinx.android.synthetic.main.activity_add_card.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddCardViewModel(application: Application) : AndroidViewModel(application) {
    val cardNumber = MutableLiveData<String>()
    val cardName = MutableLiveData<String>()
    val expireDate = MutableLiveData<String>()
    val shibaCard = MutableLiveData<String>()

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
            !shibaCard.value.isNullOrBlank()
        ) {
            val card = Card(0, cardNumber.value!!, cardName.value!!, expireDate.value!!, "000", shibaCard.value!!)
            repo.insert(card)
        }
    }
}
