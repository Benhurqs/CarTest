package com.benhurqs.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val carList = listOf(
            Car(
                id = 1,
                ano = 2020,
                combustivel = "Flex",
                numPortas = 4,
                cor = "Prata",
                nomeModelo = "Corolla",
                valor = 50000.00
            ),
            Car(
                id = 2,
                ano = 2022,
                combustivel = "Gasolina",
                numPortas = 4,
                cor = "Preto",
                nomeModelo = "Civic",
                valor = 95000.0
            )
        )

        setContent {
            val coroutineScope = rememberCoroutineScope()
            val sheetState = rememberBottomSheetState(coroutineScope)

            Scaffold(
                modifier = Modifier.fillMaxSize()
            ) { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {
                    MainScreen(
                        carList = carList,
                        onCarClick = { car -> sheetState.show(car) },
                        sheetState = sheetState
                    )
                }
            }
        }
    }
}