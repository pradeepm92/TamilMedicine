package com.example.tamilmedicine

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.rememberNavController
import com.example.tamilmedicine.authentication.presentation.AuthenticationViewmodel
import com.example.tamilmedicine.graphs.RootNavigationGraph
import com.example.tamilmedicine.ui.theme.TamilMedicineTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val viewModel by viewModels<AuthenticationViewmodel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TamilMedicineTheme {
                val navController = rememberNavController()
                RootNavigationGraph(viewModel)
//                MainScreen(navController = navController,viewModel)
            }


        }
    }
}