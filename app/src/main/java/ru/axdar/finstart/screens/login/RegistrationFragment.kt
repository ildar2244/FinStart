package ru.axdar.finstart.screens.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_registration.*
import ru.axdar.finstart.MainActivity
import ru.axdar.finstart.R
import ru.axdar.finstart.data.login.RegistrationRepository
import ru.axdar.finstart.domain.login.RegistrationUseCase

class RegistrationFragment : Fragment(), IRegistrationView {

    private lateinit var presenter: IRegistrationPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = RegistrationFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = RegistrationPresenterImpl(
            RegistrationUseCase(RegistrationRepository())
        ).apply {
            setView(this@RegistrationFragment)
            initFields()
        }
    }

    override fun setListeners() {
        toolbar_registration.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        btn_register_next.setOnClickListener { presenter.btnNextClicked() }
    }

    override fun editTextNameRead(): String {
        return et_register_name.text.toString()
    }

    override fun editTextEmailRead(): String {
        return et_register_email.text.toString()
    }

    override fun editTextPasswordRead(): String {
        return et_register_password.text.toString()
    }

    override fun editTextPasswordConfirmRead(): String {
        return et_register_password_confirm.text.toString()
    }

    override fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun replaceToMainActivity() {
        activity?.let {
            startActivity(Intent(it, MainActivity::class.java))
            it.finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onCleared()
    }
}