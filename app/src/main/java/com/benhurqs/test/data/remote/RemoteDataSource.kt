package com.benhurqs.test.data.remote

import com.benhurqs.test.data.local.LeadData
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: CarService){
    suspend fun getCarList() = apiService.getCarList()
    suspend fun sendLead(leads: List<LeadData>) = apiService.sendLead(leads)
}