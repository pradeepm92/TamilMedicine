package com.example.tamilmedicine.authentication.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tamilmedicine.authentication.domain.Response
import com.example.tamilmedicine.authentication.domain.repository.AuthenticationRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewmodel @Inject constructor(
    private val repository: AuthenticationRepository
) : ViewModel() {
    private var _login = MutableStateFlow<Response<FirebaseUser>?>(null)
    val loginflow: StateFlow<Response<FirebaseUser>?> = _login

    private var _signup = MutableStateFlow<Response<FirebaseUser>?>(null)
    val signupflow: StateFlow<Response<FirebaseUser>?> = _signup

    val currentuser: FirebaseUser?
        get() = repository.currentUser
    init {
        if(repository.currentUser!=null){

            _login.value= Response.Success(repository.currentUser!!)
        }
    }



    fun login(email: String, password: String) = viewModelScope.launch {
        _login.value = Response.Loading
        val result = repository.firebaseLogin(email, password)

        _login.value = result
    }

    fun signup(name: String, email: String, password: String) = viewModelScope.launch {
        _signup.value = Response.Loading
        val result = repository.firebaseSignUp(name, email, password)
        _signup.value = result
    }

   fun logout() {
        repository.logout()
        _login.value = null
        _signup.value = null

    }

}