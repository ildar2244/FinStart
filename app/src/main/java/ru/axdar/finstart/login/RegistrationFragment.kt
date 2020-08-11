package ru.axdar.finstart.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_registration.*
import ru.axdar.finstart.R

class RegistrationFragment : Fragment() {

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

    override fun onResume() {
        super.onResume()
        toolbar_registration.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}