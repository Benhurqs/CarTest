package com.benhurqs.test.data.remote

import com.benhurqs.test.data.CarResponse
import retrofit2.Response
import retrofit2.http.GET

interface CarService {
    @GET("cars.json")
    suspend fun getCarList(): Response<CarResponse>
}