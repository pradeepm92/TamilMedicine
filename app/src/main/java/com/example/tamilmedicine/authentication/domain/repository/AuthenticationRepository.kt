package com.example.tamilmedicine.authentication.domain.repository

import com.example.tamilmedicine.authentication.domain.Response
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow



interface AuthenticationRepository {
    val currentUser: FirebaseUser?

    suspend fun firebaseSignUp(
        name: String,
        email: String,
        password: String
    ): Response<FirebaseUser> // Correct syntax for return type

    suspend fun firebaseLogin(
        email: String,
        password: String
    ): Response<FirebaseUser> // Correct syntax for return type

   fun logout()
}
