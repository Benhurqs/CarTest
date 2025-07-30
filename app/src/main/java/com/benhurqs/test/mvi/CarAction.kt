package com.benhurqs.test.mvi

import com.benhurqs.test.data.model.Car
import com.benhurqs.test.data.model.UserData

interface CarAction {
    data object InitScreen: CarAction
    data class OnClickCar(val car: Car): CarAction
    data object OnClickSaveCar: CarAction
    data object OnCloseModal: CarAction
    data object OnCloseDialog: CarAction
    data class OnSaveUser(val user: UserData): CarAction
}