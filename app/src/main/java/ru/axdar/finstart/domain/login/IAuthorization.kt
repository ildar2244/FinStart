package ru.axdar.finstart.domain.login

interface IAuthorization {

    fun authByEmailAndPassword(email: String, password: String)
}