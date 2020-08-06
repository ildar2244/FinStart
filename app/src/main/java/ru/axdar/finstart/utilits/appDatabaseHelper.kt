package ru.axdar.finstart.utilits

import com.google.firebase.auth.FirebaseAuth
import ru.axdar.finstart.models.User

lateinit var AUTH: FirebaseAuth
lateinit var CURRENT_UID: String
lateinit var USER: User

const val NODE_USERS = "users"

const val CHILD_ID = "id"
const val CHILD_USERNAME = "username"