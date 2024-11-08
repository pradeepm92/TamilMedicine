package com.example.tamilmedicine.homescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.tamilmedicine.authentication.presentation.AuthenticationViewmodel


@Composable
fun TamilMedicineGrid(navController: NavHostController, viewModel: AuthenticationViewmodel) {
    val tamilMedicines = listOf(
        "நிலவம்பலகு (Nilavembu Kudineer)",
        "சுக்கு (Sukku - Dry Ginger Powder)",
        "வேப்பம் பூ (Neem Flower)",
        "துளசி (Tulasi - Holy Basil)",
        "மஞ்சள் (Manjal - Turmeric)",
        "முடக்காத்தான் (Mudakathan - Balloon Vine)",
        "கஸ்தூரி மஞ்சள் (Kasthuri Manjal - Wild Turmeric)",
        "அருகம்புல் (Arugampul - Bermuda Grass)",
        "பூனார் நவநீதம் (Poonai Navaneetham - Lippia Nodiflora)",
        "சிறுகுறிஞ்சான் (Sirukurinjan - Gymnema)",
        "வெற்றிலை (Vetrilai - Betel Leaf)",
        "கரிசலாங்கண்ணி (Karisanakanni - False Daisy)",
        "அதிமதுரம் (Athimadhuram - Licorice Root)",
        "அரத்தமண் (Araththamann - Red Sandalwood Powder)"
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),  // Adjust column count as needed
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(tamilMedicines) { medicine ->
            MedicineCard(medicine)
        }
    }
}

@Composable
fun MedicineCard(medicine: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = medicine,
                fontSize = 16.sp,
                color = Color.Black
            )
        }
    }
}

