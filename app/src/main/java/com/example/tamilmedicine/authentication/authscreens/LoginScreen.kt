package com.example.tamilmedicine.authentication.authscreens

import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tamilmedicine.R
import com.example.tamilmedicine.authentication.domain.Response
import com.example.tamilmedicine.authentication.presentation.AuthenticationViewmodel
import com.example.tamilmedicine.graphs.AuthScreen
import com.example.tamilmedicine.graphs.Graph
import com.example.tamilmedicine.graphs.MainScreen

@Composable
fun LoginScreen(navController: NavController, viewModel: AuthenticationViewmodel) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }

    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    val loginflow = viewModel.loginflow.collectAsState()
    var isLoggingIn by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.sriagathiyar3),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(150.dp)
                // Use size for both width and height
                .clip(CircleShape) // Clip the image to be circular
                .border(2.dp, Color.Gray, CircleShape) // Optional: Add a border if you like

        )



        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = null
            },
            label = { Text("Email") },
            isError = emailError != null,
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        if (emailError != null) {
            Text(
                text = emailError!!,
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(start = 8.dp, top = 4.dp)
                    .align(Alignment.Start)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = if (password.length < 8) {
                    "Password must contain at least 8 characters"

                } else {
                    null // Clear error when the password is valid
                }
            },
            label = { Text("Password") },
            leadingIcon = { Icon(imageVector = Icons.Default.Password, contentDescription = null) },
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        imageVector = if (passwordVisibility) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = null
                    )
                }
            },
            isError = passwordError != null,
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),


            )
        if (passwordError != null) {
            Text(
                text = passwordError!!,
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(start = 8.dp, top = 4.dp)
                    .align(Alignment.Start)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {


                var isValid = true

                // Email validation
                if (email.isEmpty()) {
                    emailError = "please enter the email"
                } else
                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        emailError = "Invalid email format"
                        isValid = false
                    } else

                    // Password validation
                        if (password.isEmpty()) {
                            passwordError = "Please enter the password"
                            isValid = false
                        } else if (password.length <= 8) {
                            passwordError = "Password must contain atleast 8 characters"
                        } else {
                            viewModel.login(email, password)
                            isLoggingIn=true
                        }
//

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = "Login")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = { navController.navigate(AuthScreen.SignUp.route) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = "Don't have an account? Register")
        }
        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = {
                // Navigate to Forgot Password screen
                navController.navigate(AuthScreen.Forgot.route)
            }
        ) {
            Text(text = "Forgot Password?")
        }
    }
if(isLoggingIn){

    loginflow.value.let {

        when (it) {
            is Response.Error -> {
                val context = LocalContext.current
                Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
            }

            Response.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is Response.Success -> {
                LaunchedEffect(Unit){
//
//                    navController.navigate(Graph.MainScreen) {
//                        popUpTo(AuthScreen.Login.route) { inclusive = true }
//                    }
                    navController.navigate(MainScreen.MedicineList.route) {
                        popUpTo(AuthScreen.Login.route) { inclusive = true }
                    }
                }
            }

            null -> {
                Log.e("TAG", "LoginScreen:null ")
            }
        }
    }
}


}