package ru.axdar.finstart.domain.login

import ru.axdar.finstart.domain.UseCase
import ru.axdar.finstart.models.Response

class AuthUseCase(
    private val repository: IAuthorization
) : UseCase<String, AuthUseCase.Params>() {

    data class Params(val email: String, val password: String)

    /*fun authUserByEmailAndPassword(email: String, password: String) {
        repository.authByEmailAndPassword(email, password)
    }*/

    override suspend fun run(params: Params): Response<String> {
        return repository.authByEmailAndPassword(params.email, params.password)
    }
}