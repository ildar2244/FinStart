package ru.axdar.finstart.domain.login

import ru.axdar.finstart.domain.UseCase
import ru.axdar.finstart.utilits.Response

class RegistrationUseCase(
    private val repository: ILoginRepo
) : UseCase<String, RegistrationUseCase.Params>() {

    data class Params(val email: String, val password: String, val name: String)

    override suspend fun run(params: Params): Response<String> {
        return repository.registrationUserWithEmailAndPassword(params.email, params.password, params.name)
    }
}