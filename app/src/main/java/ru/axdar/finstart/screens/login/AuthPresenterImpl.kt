package ru.axdar.finstart.screens.login

import ru.axdar.finstart.utilits.AUTH

class AuthPresenterImpl : IAuthPresenter {

    private lateinit var view: IAuthView

    override fun setView(view: IAuthView) {
        this.view = view
        view.setListeners()
    }

    override fun btnEnterClicked(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            view.hideKeyboard()
            //check in FB/DB
            AUTH.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        view.replaceToMainActivity()
                    } else {
                        view.showToast("Auth error: ${task.exception?.message}")
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