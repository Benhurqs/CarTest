package com.benhurqs.test.data

import android.content.Context
import androidx.annotation.WorkerThread
import com.benhurqs.test.data.local.LeadDao
import com.benhurqs.test.data.local.LeadData
import com.benhurqs.test.data.model.CarResponse
import com.benhurqs.test.data.remote.RemoteDataSource
import com.benhurqs.test.data.remote.utils.NetWorkResult
import com.benhurqs.test.data.remote.utils.toResultFlow
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ActivityRetainedScoped
class CarRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val leadDao: LeadDao
) {

    suspend fun getCarList(context: Context): Flow<NetWorkResult<CarResponse>> {
        return toResultFlow(context){
            remoteDataSource.getCarList()
        }
    }

    fun getLeads() = leadDao.getLead()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun saveLead(leadData: LeadData) {
        leadDao.insertLead(leadData)
    }
}