package ru.axdar.finstart.domain.login

import ru.axdar.finstart.models.Response

interface IAuthorization {

    fun authByEmailAndPassword(email: String, password: String): Response<String>
    fun saveAuthStateInLocal()
}