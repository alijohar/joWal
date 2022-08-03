package com.aj.jowal.ui.wallet

import android.text.Editable
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aj.jowal.ui.adapters.CardsAdapter
import com.aj.jowal.ui.models.Card

class WalletViewModel : ViewModel() {
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



    fun getCardData():ArrayList<Card>{
        val cardArrayList = ArrayList<Card>()
        cardArrayList.add(Card(1,2, "6104997563778555", "Ali johar", "0406", "34563554", "IR4485748574875487548"))
        cardArrayList.add(Card(1,2, "6037997563778555", "Ali johar", "0406", "34563554", "IR4485748574875487548"))
        cardArrayList.add(Card(1,2, "6444997563778555", "Ali johar", "0406", "34563554", "IR4485748574875487548"))
        cardArrayList.add(Card(1,2, "6037997563778555", "Ali johar", "0406", "34563554", "IR4485748574875487548"))
        cardArrayList.add(Card(1,2, "6037997563778555", "Ali johar", "0406", "34563554", "IR4485748574875487548"))

        return cardArrayList
    }
}