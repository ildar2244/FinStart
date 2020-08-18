package ru.axdar.finstart.data.login

import ru.axdar.finstart.domain.login.IAuthorization

class AuthRepository : IAuthorization {

    override fun authByEmailAndPassword(email: String, password: String) {
        TODO("Not yet implemented")
    }
}