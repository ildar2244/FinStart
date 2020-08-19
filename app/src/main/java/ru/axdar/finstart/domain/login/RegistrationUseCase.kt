package ru.axdar.finstart.domain.login

import ru.axdar.finstart.domain.UseCase
import ru.axdar.finstart.models.Response

class RegistrationUseCase(
    private val repository: IRegistration
) : UseCase<String, RegistrationUseCase.Params>() {

    data class Params(val email: String, val password: String, val name: String)

    override suspend fun run(params: Params): Response<String> {
        val response =  repository.registrationUserWithEmailAndPassword(params.email, params.password, params.name)
        if (response is Response.Success) {
            //статус "Авторизован" в локальное хранилище
            repository.saveAuthStateInLocal()
        }
        return response
    }
}