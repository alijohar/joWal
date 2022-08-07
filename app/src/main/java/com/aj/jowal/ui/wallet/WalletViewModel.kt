package com.aj.jowal.ui.wallet

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aj.jowal.ui.data.CardDatabase
import com.aj.jowal.ui.model.Card
import com.aj.jowal.ui.repository.CardRepository

class WalletViewModel(application: Application) : AndroidViewModel(application) {
    private val repo: CardRepository
    val allCards: LiveData<List<Card>>

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


    fun openAddCardActivity(view: View){
        openActivity.value = true
    }



}