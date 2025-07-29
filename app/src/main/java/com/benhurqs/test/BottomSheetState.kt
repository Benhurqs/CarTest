package com.benhurqs.test

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Stable
class BottomSheetState @OptIn(ExperimentalMaterial3Api::class) constructor(
    private val coroutineScope: CoroutineScope,
    val sheetState: SheetState
) {
    private val selectedCar = mutableStateOf<Car?>(null)


    @OptIn(ExperimentalMaterial3Api::class)
    fun show(car: Car) {
        selectedCar.value = car
        coroutineScope.launch {
            sheetState.show()
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    fun hide() {
        selectedCar.value = null
        coroutineScope.launch {
            sheetState.hide()
        }
    }

    fun getSelectedCar(): Car? = selectedCar.value
}

@ExperimentalMaterial3Api
@Composable
fun rememberBottomSheetState(coroutineScope: CoroutineScope): BottomSheetState {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    return remember { BottomSheetState(coroutineScope, sheetState) }
}