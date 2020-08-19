package ru.axdar.finstart.domain.login

import ru.axdar.finstart.models.Response

interface IRegistration {

    fun registrationUserWithEmailAndPassword(
        email: String, password: String, name: String
    ): Response<String>

    fun saveAuthStateInLocal()
}