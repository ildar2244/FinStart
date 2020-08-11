package ru.axdar.finstart.login

interface IAuthView {

    fun setListeners()
    fun showToast(message: String)
    fun replaceToRegistrationFragment()
}