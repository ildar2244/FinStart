package ru.axdar.finstart.domain.login

import kotlinx.coroutines.flow.Flow
import ru.axdar.finstart.utilits.Response

interface ILoginRepo {

    fun registrationUserWithEmailAndPassword(
        email: String, password: String, name: String
    ): Response<String>

    fun authByEmailAndPassword(email: String, password: String)
}