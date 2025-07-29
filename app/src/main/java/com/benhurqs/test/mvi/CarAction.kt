package com.benhurqs.test.mvi

import com.benhurqs.test.data.Car
import com.benhurqs.test.data.CarResponse

interface CarAction {
    data object InitScreen: CarAction
    data class OnClickCar(val carResponse: Car): CarAction
}