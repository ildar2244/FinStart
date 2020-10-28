package ru.axdar.finstart.screens.login

import ru.axdar.finstart.domain.login.AuthUseCase
import ru.axdar.finstart.models.Response

class AuthPresenterImpl(
    val useCase: AuthUseCase
) : IAuthPresenter {

    private lateinit var view: IAuthView

    override fun setView(view: IAuthView) {
        this.view = view
        view.setListeners()
    }

    override fun btnEnterClicked(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            view.hideKeyboard()
            //check in FB/DB
            useCase(AuthUseCase.Params(email, password)) {
                when (it) {
                    is Response.Success -> view.replaceToMainActivity()
                    is Response.Error -> view.showToast("Auth error: ${it.exception}")
                }
            }
        } else {
            view.showToast("Input values does not can to be empty")
        }
    }

    override fun tvRegistrationClicked() {
        view.replaceToRegistrationFragment()
    }

    override fun tvAnonymouslyClicked() {
        //todo create local user profile: save SharedPref and intent to MainActivity
    }
}