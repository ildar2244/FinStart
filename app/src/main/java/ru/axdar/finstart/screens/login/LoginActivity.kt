package ru.axdar.finstart.screens.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.axdar.finstart.MainActivity
import ru.axdar.finstart.R
import ru.axdar.finstart.utilits.PrefsManager
import ru.axdar.finstart.utilits.initFirebase

class LoginActivity : AppCompatActivity() {

    private lateinit var mFragment: Fragment
    private val stateFragment = "state_fragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        PrefsManager.init(this)
        initFirebase()

        //if PrefsManager.getAuthState() - true
        Log.d("9999", "onCreate: PREF_AUTH:${PrefsManager.getAuthState()}")
        if (PrefsManager.getAuthState()) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            if (savedInstanceState != null) {
                mFragment = supportFragmentManager.getFragment(savedInstanceState, stateFragment)
                    ?: AuthFragment.newInstance()
            } else {
                mFragment = AuthFragment.newInstance()
                replaceFragment(mFragment)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        supportFragmentManager.putFragment(outState, stateFragment, mFragment)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        mFragment = if (supportFragmentManager.fragments.size > 0) {
            supportFragmentManager.fragments.last()
        } else AuthFragment.newInstance()
        supportFragmentManager.popBackStack()
    }

    fun replaceFragment(fragment: Fragment) { //needToAddToBackStack: Boolean = true
        mFragment = fragment
        val name = fragment.javaClass.simpleName
        with(supportFragmentManager.beginTransaction()) {
            replace(R.id.container_login, fragment, name)
            if (name != "AuthFragment") addToBackStack(name)
            commit()
        }
    }
}