package com.benhurqs.test.viewmodel

import androidx.lifecycle.AndroidViewModel
import android.app.Application
import androidx.lifecycle.viewModelScope
import com.benhurqs.test.data.model.Car
import com.benhurqs.test.data.CarRepository
import com.benhurqs.test.data.local.LeadData
import com.benhurqs.test.data.model.UserData
import com.benhurqs.test.data.remote.utils.ApiStatus
import com.benhurqs.test.mvi.CarAction
import com.benhurqs.test.mvi.CarViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@HiltViewModel
class CarViewModel @Inject constructor(
    private val repository: CarRepository,
    application: Application
) : AndroidViewModel(application) {

    private val POLLING_TIME = 30.toDuration(DurationUnit.SECONDS)
    private val initialState: CarViewState = CarViewState(list = listOf(), selectedCar = null)
    private val _uiState: MutableStateFlow<CarViewState> by lazy { MutableStateFlow(initialState) }
    val uiState: StateFlow<CarViewState> = _uiState

    private var stopPooling = false

    fun dispatch(event: CarAction) {
        when (event) {
            is CarAction.InitScreen -> callCarList()
            is CarAction.StopPooling -> { stopPooling = true }
            is CarAction.SendLeads -> callLead()
            is CarAction.OnClickCar -> selectCar(event.car)
            is CarAction.OnClickSaveCar -> {
                updateModalState()
                showUserDialog()
            }
            is CarAction.OnCloseDialog -> hideUserDialog()
            is CarAction.OnCloseModal -> updateModalState()
            is CarAction.OnSaveUser -> saveUser(event.user)
        }
    }

    private fun callLead() = viewModelScope.launch {
        stopPooling = false
        repository.getLeads().collect { leadSavedList ->
            if(leadSavedList.isEmpty()) {
                stopPooling = true
                return@collect
            }

            repository.sendLead(leadSavedList).collect { values ->
                if (values.status == ApiStatus.SUCCESS) {
                    repository.removeLeads()
                    delay(POLLING_TIME)
                    if(!stopPooling) {
                        dispatch(CarAction.SendLeads)
                    }
                }
            }
        }
    }

    private fun showUserDialog() {
        _uiState.value = uiState.value.copy(
            showDialog = true
        )
    }

    private fun hideUserDialog() {
        _uiState.value = uiState.value.copy(
            showDialog = false
        )
    }

    private fun saveUser(user: UserData) = viewModelScope.launch {
        uiState.value.selectedCar?.id?.let { carId ->
            repository.saveLead(
                leadData = LeadData(
                    userName = user.name,
                    userEmail = user.email,
                    carId = carId
                )
            )

            if(stopPooling){
                callLead()
            }
        }
    }

    private fun updateModalState() {
        _uiState.value = uiState.value.copy(
            openBottomSheet = false
        )
    }

    private fun selectCar(car: Car) {
        _uiState.value = uiState.value.copy(
            selectedCar = car,
            openBottomSheet = true
        )
    }

    private fun callCarList() = viewModelScope.launch {
        repository.getCarList().collect { values ->
            if (values.status == ApiStatus.SUCCESS) {
                _uiState.value = uiState.value.copy(
                    list = values.data?.cars.orEmpty()
                )
            } else {
                //TODO tratar erro da API e mostrar tela de erro
            }
        }
    }

}