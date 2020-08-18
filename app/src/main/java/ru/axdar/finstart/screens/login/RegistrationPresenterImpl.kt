package ru.axdar.finstart.screens.login

import ru.axdar.finstart.domain.login.RegistrationUseCase
import ru.axdar.finstart.utilits.Response

class RegistrationPresenterImpl(
    val useCase: RegistrationUseCase
) : IRegistrationPresenter {

    private lateinit var view: IRegistrationView

    override fun setView(view: IRegistrationView) {
        this.view = view
    }

    override fun initFields() {
        view.setListeners()
    }

    override fun btnNextClicked() {
        val name: String = view.editTextNameRead()
        val email: String = view.editTextEmailRead()
        val password: String = view.editTextPasswordRead()
        val passwordConfirm: String = view.editTextPasswordConfirmRead()

        //input data validation
        if (email.isNotEmpty() && password.isNotEmpty()) {
            if (password == passwordConfirm) {
                registerUser(email, password, name)
            } else {
                view.showToast("Пароли не совпадают.")
            }
        } else {
            view.showToast("Please fill up Email and Password")
        }
    }

    private fun registerUser(email: String, password: String, name: String) {
        useCase(RegistrationUseCase.Params(email, password, name)) {
            when(it) {
                is Response.Success -> view.replaceToMainActivity()
                is Response.Error -> view.showToast(it.exception.toString())
            }
        }
    }

    override fun onCleared() {
        useCase.unsubscribe()
    }
}