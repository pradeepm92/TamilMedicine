package com.example.tamilmedicine.authentication.di

import com.example.tamilmedicine.authentication.data.repo.AuthenticationRepositoryImpl
import com.example.tamilmedicine.authentication.domain.repository.AuthenticationRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton




@InstallIn(SingletonComponent::class)
@Module
object AuthenticationModule {

    @Provides
    @Singleton
    fun providesAuthenticationRepository(
        firebaseAuth: FirebaseAuth,

    ): AuthenticationRepository {
        return AuthenticationRepositoryImpl(
            firebaseAuth = firebaseAuth,

        )
    }


}
