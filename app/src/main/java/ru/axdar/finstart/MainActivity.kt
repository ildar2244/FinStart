package ru.axdar.finstart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.axdar.finstart.utilits.APP_ACTIVITY
import ru.axdar.finstart.utilits.showToast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        APP_ACTIVITY = this
        showToast("Welcome!")
    }
}