package com.benhurqs.test.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: CarService){
    suspend fun getCarList() = apiService.getCarList()
}