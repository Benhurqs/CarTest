package com.benhurqs.test.data.remote

import com.benhurqs.test.data.local.LeadData
import com.benhurqs.test.data.model.CarResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CarService {
    @GET("cars.json")
    suspend fun getCarList(): Response<CarResponse>

    @POST("cars/leads")
    suspend fun sendLead(@Body leadData: List<LeadData>): Response<Unit>
}