package ru.axdar.finstart.screens.login

interface IAuthView {

    fun setListeners()
    fun showToast(message: String)
    fun replaceToRegistrationFragment()
    fun replaceToMainActivity()
    fun hideKeyboard()
}