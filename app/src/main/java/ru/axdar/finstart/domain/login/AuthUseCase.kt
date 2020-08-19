package ru.axdar.finstart.domain.login

class AuthUseCase(private val repository: IAuthorization) {

    fun authUserByEmailAndPassword(email: String, password: String) {
        repository.authByEmailAndPassword(email, password)
    }
}