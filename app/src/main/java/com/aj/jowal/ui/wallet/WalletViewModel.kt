package com.aj.jowal.ui.wallet

import android.text.Editable
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

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
}