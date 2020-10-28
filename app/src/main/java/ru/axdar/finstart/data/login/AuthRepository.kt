package ru.axdar.finstart.data.login

import ru.axdar.finstart.domain.login.IAuthorization
import ru.axdar.finstart.models.Response
import ru.axdar.finstart.utilits.AUTH
import ru.axdar.finstart.utilits.PrefsManager

class AuthRepository : IAuthorization {

    override fun authByEmailAndPassword(email: String, password: String): Response<String> {
        return when(authWithFirebaseByEmailAndPassword(email, password)) {
            is Response.Success -> Response.success("Welcome!")
            else -> Response.error("Failed.")
        }
    }

    private fun authWithFirebaseByEmailAndPassword(
        email: String, password: String
    ): Response<String> {
        //todo refactor
        return try {
            AUTH.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        saveAuthStateInLocal()
                    }
                }
            Response.Success("Success authorization")
        } catch (e: Exception) {
            Response.Error(e.message.toString())
        }

    }

    override fun saveAuthStateInLocal() {
        //статус "Авторизован" в SharedPref
        PrefsManager.saveAuthState(true)
    }
}