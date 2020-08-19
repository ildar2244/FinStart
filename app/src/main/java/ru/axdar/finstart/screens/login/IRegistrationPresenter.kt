package ru.axdar.finstart.screens.login

interface IRegistrationPresenter {

    fun setView(view: IRegistrationView)
    fun initFields()
    fun btnNextClicked()
    fun onCleared()
}