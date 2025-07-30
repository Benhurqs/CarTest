package com.benhurqs.test.mvi

import com.benhurqs.test.data.model.Car

data class CarViewState(
    val list: List<Car>,
    val selectedCar: Car?,
    val openBottomSheet: Boolean = false,
    val showDialog: Boolean = false
)