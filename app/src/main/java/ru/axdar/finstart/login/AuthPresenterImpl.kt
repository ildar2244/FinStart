package ru.axdar.finstart.login

class AuthPresenterImpl : IAuthPresenter {

    private lateinit var view: IAuthView

    override fun setView(view: IAuthView) {
        this.view = view
        view.setListeners()
    }

    override fun btnEnterClicked(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            //check in FB/DB
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