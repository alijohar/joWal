package com.aj.jowal.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddCardViewModel: ViewModel() {
    val cardNumber = MutableLiveData<String>()
    val cardName = MutableLiveData<String>()
    val expireDate = MutableLiveData<String>()
    val shibaCard = MutableLiveData<String>()


}