package ru.axdar.finstart.data.login

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.tasks.await
import ru.axdar.finstart.domain.login.IRegistration
import ru.axdar.finstart.utilits.PrefsManager
import ru.axdar.finstart.models.Response
import ru.axdar.finstart.utilits.*

class RegistrationRepository : IRegistration {

    override fun registrationUserWithEmailAndPassword(
        email: String, password: String, name: String
    ): Response<String> {
        return when(registrationWithFirebaseByEmailAndPassword(email, password, name)) {
            is Response.Success -> Response.success("Welcome!")
            else -> Response.error("Failed.")
        }
    }

    private fun registrationWithFirebaseByEmailAndPassword(
        email: String, password: String, name: String
    ): Response<String> {
        //регистрация юзера по email&pass через FirebaseAuth
        return try {
            AUTH.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener() { task ->
                    val lis = task.result
                    if (task.isSuccessful) {
                        val uid = AUTH.currentUser?.uid.toString()
                        //сохраняем данные юзера в FirebaseDatabase
                        saveInFirebaseDB(uid, name)
                    }
                }
            Response.Success("Registration in Firebase")
        } catch (e: Exception) {
            Response.Error(e.message.toString())
        }
    }

    //сохраняем данные юзера в FirebaseDatabase (RealtimeDatabase)
    private fun saveInFirebaseDB(uid: String, name: String): Response<Boolean> {
        val dataMap = mutableMapOf<String, Any>()
        dataMap[CHILD_ID] = uid
        dataMap[CHILD_USERNAME] = name
        REF_DATABASE_ROOT.child(NODE_USERS).child(uid)
            .updateChildren(dataMap)
            .addOnSuccessListener { Log.d("9999", "saveInFirebaseDB: IF")
                 }
            .addOnFailureListener { Log.d("9999", "saveInFirebaseDB: ELSE") }
        return Response.Success(true)
    }

    override fun saveAuthStateInLocal() {
        //статус "Авторизован" в SharedPref
        PrefsManager.saveAuthState(true)
    }

    //потом может пригодится
    @ExperimentalCoroutinesApi
    fun regFire(email: String, password: String, name: String) = flow<Response<String>> {
        emit(Response.loading())
        val snapshot= AUTH.createUserWithEmailAndPassword(email, password).await()
        val response = snapshot.toString()
        emit(Response.success(response))
    }.catch {
        emit(Response.error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}