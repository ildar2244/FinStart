package ru.axdar.finstart.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import ru.axdar.finstart.R
import ru.axdar.finstart.utilits.EditTextWatcher

class LoginActivity : AppCompatActivity(), ILoginView {
    private lateinit var presenter: ILoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initFields()
    }

    private fun initFields() {
        presenter = LoginPresenterImpl().apply { setView(this@LoginActivity) }
    }

    override fun setListeners() {
        btn_enter.setOnClickListener {
            presenter.btnEnterClicked(et_email.text.toString(), et_password.text.toString())
        }
        btn_registration.setOnClickListener { presenter.btnRegistrationClicked() }
        btn_no_registration.setOnClickListener { presenter.btnNoRegistrationClicked() }

        et_email.addTextChangedListener(EditTextWatcher {
            //todo check not exist (?)
        })
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}