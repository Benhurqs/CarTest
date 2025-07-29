package com.benhurqs.test

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import java.text.NumberFormat
import java.util.Locale

@ExperimentalMaterial3Api
@Composable
fun MainScreen(
    carList: List<Car>,
    onCarClick: (Car) -> Unit,
    sheetState: BottomSheetState
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Carros à Venda",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        LazyColumn(Modifier.fillMaxSize()) {
            items(carList) { car ->
                CarCardsComponent(car = car, onClick = { onCarClick(car) })
            }
        }
    }

    if (sheetState.getSelectedCar() != null) {
        ModalBottomSheet(
            sheetState = sheetState.sheetState,
            onDismissRequest = { sheetState.hide() }
        ) {
            BottomSheetContent(car = sheetState.getSelectedCar()!!)
        }
    }
}

@Composable
fun BottomSheetContent(car: Car) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Detalhes do Carro",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(Modifier.height(16.dp))

        Text(text = "Modelo: ${car.nomeModelo}", fontSize = 16.sp)
        Spacer(Modifier.height(8.dp))
        Text(text = "Ano: ${car.ano}", fontSize = 16.sp)
        Spacer(Modifier.height(8.dp))
        Text(text = "Valor: R$ ${car.valor}", fontSize = 16.sp)
        Spacer(Modifier.height(8.dp))
        Text(text = "Combustível: ${car.combustivel}", fontSize = 16.sp)
        Spacer(Modifier.height(8.dp))
        Text(text = "Cor: ${car.cor}", fontSize = 16.sp)
        Spacer(Modifier.height(8.dp))
        Text(text = "Número de Portas: ${car.numPortas}", fontSize = 16.sp)
        Spacer(Modifier.height(16.dp))


        androidx.compose.material3.Button(
            onClick = {

                println("Botão 'Eu quero' clicado para o modelo ${car.nomeModelo}")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(text = "Eu quero", fontSize = 16.sp)
        }
    }
}

@Composable
fun CarCardsComponent(
    car: Car,
    onClick: () -> Unit
) {
    val valorFormatado = NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(car.valor)

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .clickable { onClick() }
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
                    text = "Modelo: ${car.nomeModelo}",
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

