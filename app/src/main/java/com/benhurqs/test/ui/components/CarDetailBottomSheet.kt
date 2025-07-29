package com.benhurqs.test.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.benhurqs.test.data.Car
import com.benhurqs.test.mvi.CarAction
import com.benhurqs.test.viewmodel.CarViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarDetailBottomSheet(
    viewModel: CarViewModel = hiltViewModel()
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val car = uiState.selectedCar
    showBottomSheet = uiState.openBottomSheet

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                viewModel.dispatch(CarAction.OnCloseModal)
                showBottomSheet = false
            },
            sheetState = sheetState
        ) {
            BottomSheetContent(car) {
                viewModel.dispatch(CarAction.OnClickSaveCar)
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        showBottomSheet = false
                    }
                }
            }
        }
    }
}

@Composable
fun BottomSheetContent(car: Car?, onClick: () -> Unit){
    Column(
        modifier = Modifier
            .background(color = Color.White)
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Detalhes do Carro",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(Modifier.height(16.dp))

        Text(text = "Modelo: ${car?.nome_modelo}", fontSize = 16.sp)
        Spacer(Modifier.height(8.dp))
        Text(text = "Ano: ${car?.ano}", fontSize = 16.sp)
        Spacer(Modifier.height(8.dp))
        Text(text = "Valor: R$ ${car?.valor}", fontSize = 16.sp)
        Spacer(Modifier.height(8.dp))
        Text(text = "Combustível: ${car?.combustivel}", fontSize = 16.sp)
        Spacer(Modifier.height(8.dp))
        Text(text = "Cor: ${car?.cor}", fontSize = 16.sp)
        Spacer(Modifier.height(8.dp))
        Text(text = "Número de Portas: ${car?.num_portas}", fontSize = 16.sp)
        Spacer(Modifier.height(16.dp))

        ButtonComponent { onClick.invoke() }
    }
}

@Composable
fun ButtonComponent(onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .height(40.dp)
            .fillMaxWidth(),
        onClick = { onClick() }) {
        Text(
            fontSize = 20.sp,
            text = "Eu Quero"
        )
    }
}

@Preview(apiLevel = 34)
@Composable
fun PreviewBottomSheet() {
    BottomSheetContent(Car(
        nome_modelo = "bmw",
        ano = 2002,
        valor = 50000.00
    )) { }
}
