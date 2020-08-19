package ru.axdar.finstart.screens.login

interface IAuthPresenter {

    fun setView(view: IAuthView)
    fun btnEnterClicked(email: String, password: String)
    fun tvRegistrationClicked()
    fun tvAnonymouslyClicked()
}