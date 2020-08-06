package ru.axdar.finstart.login

interface ILoginPresenter {

    fun setView(view: ILoginView)
    fun btnEnterClicked(email: String, password: String)
    fun btnRegistrationClicked()
    fun btnNoRegistrationClicked()
}