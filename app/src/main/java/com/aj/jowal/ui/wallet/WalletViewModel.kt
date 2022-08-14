package com.aj.jowal.ui.wallet

import android.app.Application
import android.view.View
import androidx.lifecycle.*
import com.aj.jowal.ui.data.CardDatabase
import com.aj.jowal.ui.model.Card
import com.aj.jowal.ui.repository.CardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WalletViewModel(application: Application) : AndroidViewModel(application) {
    private val repo: CardRepository
    val allCards: LiveData<List<Card>>
    val onDeleteItemClicked = MutableLiveData<Boolean>().apply {
        value = false
    }

    init {
        val dao = CardDatabase.getDatabase(application).dao()
        repo = CardRepository(dao)
        allCards = repo.allCards
    }

    val openActivity = MutableLiveData<Boolean>().apply {
        value = false
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is Wallet Fragment"
    }
    val text: LiveData<String> = _text


    fun openAddCardActivity(view: View) {
        openActivity.value = true
    }

    fun alertForDelete(view: View) {
        onDeleteItemClicked.value = true
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repo.deleteAll()
    }

}