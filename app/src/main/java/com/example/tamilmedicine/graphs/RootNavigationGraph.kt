package com.example.tamilmedicine.graphs

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.tamilmedicine.BottomBarScreen
import com.example.tamilmedicine.MainScreen
import com.example.tamilmedicine.authentication.domain.Response
import com.example.tamilmedicine.authentication.presentation.AuthenticationViewmodel


@Composable
fun RootNavigationGraph(viewModel: AuthenticationViewmodel) {
    val navController = rememberNavController()
    val loginFlowState by viewModel.loginflow.collectAsState()




    NavHost(
        navController = navController,
        startDestination =  Graph.Authenticate,
        route = Graph.ROOT
    ) {
        // Authentication Flow

        authNavGraph(navController, viewModel)

        // Main Screen Flow



    }


}
