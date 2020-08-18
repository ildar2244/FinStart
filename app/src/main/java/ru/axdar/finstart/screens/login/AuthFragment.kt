package ru.axdar.finstart.screens.login

import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.TooltipCompat
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_auth.*
import ru.axdar.finstart.R
import ru.axdar.finstart.utilits.EditTextWatcher

class AuthFragment : Fragment(), IAuthView {

    private lateinit var presenter: IAuthPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_auth, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = AuthFragment()
    }

    override fun onStart() {
        super.onStart()
        initFields()
    }

    private fun initFields() {
        presenter = AuthPresenterImpl().apply { setView(this@AuthFragment) }

        //шрифт логотипа
        val typeface = Typeface.createFromAsset(activity?.assets, "fonts/campusOltSh.ttf")
        tv_app_logo.typeface = typeface
    }

    override fun setListeners() {
        btn_enter.setOnClickListener {
            presenter.btnEnterClicked(et_email.text.toString(), et_password.text.toString())
        }
        tv_registration.setOnClickListener { presenter.tvRegistrationClicked() }
        tv_anonymously.setOnClickListener { presenter.tvAnonymouslyClicked() }

        et_email.addTextChangedListener(EditTextWatcher {
            //todo check not exist (?)
        })

        //текст подсказки "анонимного" режима
        iv_anonymously_tooltip.setOnClickListener {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                Toast.makeText(activity, getString(R.string.tooltip_help_anonymously), Toast.LENGTH_SHORT).show()
                TooltipCompat.setTooltipText(it, getString(R.string.tooltip_help_anonymously))
            } else {
                //it.tooltipText = getString(R.string.tooltip_help_anonymously)
                //TooltipCompat.setTooltipText(it, getString(R.string.tooltip_help_anonymously))
                Toast.makeText(activity, getString(R.string.tooltip_help_anonymously), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun replaceToRegistrationFragment() {
        (activity as LoginActivity).replaceFragment(RegistrationFragment.newInstance())
    }
}