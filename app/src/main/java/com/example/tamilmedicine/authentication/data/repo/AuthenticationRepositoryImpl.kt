package com.example.tamilmedicine.authentication.data.repo

import androidx.collection.emptyIntSet
import com.example.tamilmedicine.authentication.domain.Response
import com.example.tamilmedicine.authentication.domain.Response.*
import com.example.tamilmedicine.authentication.domain.repository.AuthenticationRepository
import com.example.tamilmedicine.authentication.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
) : AuthenticationRepository {

    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun firebaseSignUp(
        name: String,
        email: String,
        password: String
    ): Response<FirebaseUser> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()

            result.user?.let { user ->
                // Update display name
                user.updateProfile(
                    UserProfileChangeRequest.Builder().setDisplayName(name).build()
                ).await()

                Response.Success(user) // Return success response with non-null user
            } ?: Response.Error("User creation failed: user is null") // Handle the null case
        } catch (e: Exception) {
            Response.Error(e.localizedMessage ?: "An unknown error occurred") // Return error response directly
        }
    }


    override suspend fun firebaseLogin(email: String, password: String): Response<FirebaseUser> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()

            result.user?.let { user ->
                Response.Success(user) // Return success response with non-null user
            } ?: Response.Error("Login failed: user is null") // Handle the null case
        } catch (e: Exception) {
            Response.Error(e.localizedMessage ?: "An unknown error occurred") // Return error response directly
        }
    }



    override fun logout() {
        firebaseAuth.signOut()
    }
}
