package ru.axdar.finstart.screens.login

interface IRegistrationView {

    fun setListeners()
    fun editTextNameRead(): String
    fun editTextEmailRead(): String
    fun editTextPasswordRead(): String
    fun editTextPasswordConfirmRead(): String
    fun showToast(message: String)
    fun replaceToMainActivity()
}