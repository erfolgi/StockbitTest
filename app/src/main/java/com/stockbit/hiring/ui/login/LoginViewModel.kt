package com.stockbit.hiring.ui.login

import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData

class LoginViewModel {
    val isStringEmpty = MutableLiveData<Boolean>()
    val inputUsername = MutableLiveData<String>()
    private val inputPassword = MutableLiveData<String>()

}