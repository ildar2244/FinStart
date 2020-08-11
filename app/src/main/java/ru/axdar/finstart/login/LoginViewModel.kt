package ru.axdar.finstart.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    val currentFragment: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}