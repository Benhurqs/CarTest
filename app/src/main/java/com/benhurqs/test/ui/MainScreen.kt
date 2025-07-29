package com.benhurqs.test.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.benhurqs.test.data.Car
import com.benhurqs.test.mvi.CarAction
import com.benhurqs.test.mvi.LaunchOne
import com.benhurqs.test.viewmodel.CarViewModel

@Composable
fun MainScreen(
    viewModel: CarViewModel = hiltViewModel()
) { 
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchOne { viewModel.dispatch(CarAction.InitScreen) }

    CarList(uiState.list)
}

@Composable
fun CarList(carList: List<Car>){
    LazyColumn(
        Modifier
            .background(color = Color.White)
            .padding(horizontal = 16.dp)
    ) {
        items(carList) { car ->
            CarCardsComponent(car.nome_modelo.orEmpty())
        }
    }
}

@Composable
fun CarCardsComponent(name: String){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .height(100.dp)
    ) {
        Row(modifier = Modifier.padding(all = 8.dp)) {
            Text(
                text = name,
                color = MaterialTheme.colorScheme.secondary
            )

            Spacer(modifier = Modifier.width(4.dp))
            Text(text = name)
        }
    }
}

@Preview
@Composable
fun PreviewMessageCard() {
    CarList(mutableListOf(
        Car(nome_modelo = "uno"),
        Car(nome_modelo = "gol"),
        Car(nome_modelo = "bmw"),
    ))
}
