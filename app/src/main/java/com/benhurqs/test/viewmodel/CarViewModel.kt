package com.benhurqs.test.viewmodel

import androidx.lifecycle.AndroidViewModel
import android.app.Application
import androidx.lifecycle.viewModelScope
import com.benhurqs.test.data.model.Car
import com.benhurqs.test.data.CarRepository
import com.benhurqs.test.data.model.UserData
import com.benhurqs.test.data.remote.utils.ApiStatus
import com.benhurqs.test.mvi.CarAction
import com.benhurqs.test.mvi.CarViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarViewModel @Inject constructor(private val repository: CarRepository, application: Application):  AndroidViewModel(application)  {
    private val context
        get() = getApplication<Application>()

    private val initialState: CarViewState = CarViewState(list = listOf(), selectedCar = null)

    private val _uiState: MutableStateFlow<CarViewState> by lazy { MutableStateFlow(initialState) }
    val uiState: StateFlow<CarViewState> = _uiState

    fun dispatch(event: CarAction) {
        when(event) {
            is CarAction.InitScreen -> callCarList()
            is CarAction.OnClickCar -> selectCar(event.car)
            is CarAction.OnClickSaveCar -> {
                updateModalState()
                saveCar()
            }
            is CarAction.OnCloseModal -> updateModalState()
            is CarAction.OnSaveUser -> saveUser(event.user)
        }
    }

    private fun saveUser(user: UserData){

    }

    private fun updateModalState(){
        _uiState.value = uiState.value.copy(
            openBottomSheet = false
        )
    }

    private fun saveCar(){}

    private fun selectCar(car: Car) {
        _uiState.value = uiState.value.copy(
            selectedCar = car,
            openBottomSheet = true
        )
    }

    private fun callCarList()  = viewModelScope.launch {
        repository.getCarList(context).collect { values ->
            if(values.status == ApiStatus.SUCCESS) {
                _uiState.value = uiState.value.copy(
                    list = values.data?.cars.orEmpty()
                )
            } else {
                //Tratar erro
            }
        }
    }

}