package com.benhurqs.test.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import java.text.NumberFormat
import java.util.Locale
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

    CarList(uiState.list) { viewModel.dispatch(CarAction.OnClickCar(it)) }
}

@Composable
fun CarList(
    carList: List<Car>,
    onClick: (Car) -> Unit
) {
    LazyColumn(
        Modifier
            .background(color = Color.White)
            .padding(horizontal = 16.dp)
    ) {
        items(carList) { car ->
            CarCardsComponent(car, onClick)
        }
    }
}

@Composable
fun CarCardsComponent(
    car: Car,
    onClick: (Car) -> Unit
) {
    val valorFormatado = NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(car.valor)

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .clickable { onClick(car) }
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(color = MaterialTheme.colorScheme.primary),
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = "Modelo: ${car.nome_modelo}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Ano: ${car.ano}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Valor: $valorFormatado",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}

@Preview(apiLevel = 34)
@Preview
@Composable
fun PreviewCarList() {
    CarList(
        mutableListOf(
            Car(
                nome_modelo = "uno",
                ano = 2002,
                valor = 50000.00
            ),
            Car(
                nome_modelo = "gol",
                ano = 2002,
                valor = 50000.00
            ),
            Car(
                nome_modelo = "bmw",
                ano = 2002,
                valor = 50000.00
            ),
        )
    ){}
}
