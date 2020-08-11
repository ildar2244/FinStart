package ru.axdar.finstart.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.axdar.finstart.MainActivity
import ru.axdar.finstart.R

class LoginActivity : AppCompatActivity() {

    //todo поворот экрана: текущий фрагмент

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        checkAuth()
        initFields()
    }

    private fun initFields() {

    }

    private fun checkAuth() {
        //if true -> MainActivity
        if (false) {
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_login, AuthFragment.newInstance())
                .commit()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.popBackStack()
    }
}