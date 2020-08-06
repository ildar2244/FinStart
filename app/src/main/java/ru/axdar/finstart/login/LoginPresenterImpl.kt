package ru.axdar.finstart.login

class LoginPresenterImpl : ILoginPresenter {

    private lateinit var view: ILoginView

    override fun setView(view: ILoginView) {
        this.view = view
    }

    override fun btnEnterClicked(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            //check in FB/DB
        } else {
            view.showToast("Input values does not can to be empty")
        }
    }

    override fun btnRegistrationClicked() {}

    override fun btnNoRegistrationClicked() {}
}