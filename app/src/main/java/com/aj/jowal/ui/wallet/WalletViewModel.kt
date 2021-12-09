package com.aj.jowal.ui.wallet

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WalletViewModel : ViewModel() {
    val cardNumber = MutableLiveData<String>()
    val cardName = MutableLiveData<String>()
    val expireDate = MutableLiveData<String>()
    val shibaCard = MutableLiveData<String>()

    private val _text = MutableLiveData<String>().apply {
        value = "This is Wallet Fragment"
    }
    val text: LiveData<String> = _text

}