package com.example.tamilmedicine.graphs

import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.tamilmedicine.authentication.authscreens.ForgotPasswordScreen
import com.example.tamilmedicine.authentication.authscreens.LoginScreen
import com.example.tamilmedicine.authentication.authscreens.RegisterScreen
import com.example.tamilmedicine.authentication.authscreens.SplashScreen
import com.example.tamilmedicine.authentication.presentation.AuthenticationViewmodel


fun NavGraphBuilder.authNavGraph(navController: NavHostController,viewModel:AuthenticationViewmodel) {
    Log.e("TAG", "NavigationGraph: ", )
    navigation(route = Graph.Authenticate, startDestination = AuthScreen.SplashScreen.route) {


        composable(route = AuthScreen.SplashScreen.route){

            SplashScreen(navController = navController)
        }
        composable(route = AuthScreen.Login.route) {

            LoginScreen(navController = navController,viewModel=viewModel)
        }
        composable(route = AuthScreen.SignUp.route) {
            RegisterScreen(navController = navController,viewModel=viewModel)
        }
        composable(route = AuthScreen.Forgot.route) {
             ForgotPasswordScreen(navController = navController,viewModel=viewModel)
        }

    }

}

