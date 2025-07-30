package com.benhurqs.test.data

import android.content.Context
import androidx.annotation.WorkerThread
import com.benhurqs.test.data.local.LeadDao
import com.benhurqs.test.data.local.LeadData
import com.benhurqs.test.data.model.CarResponse
import com.benhurqs.test.data.remote.RemoteDataSource
import com.benhurqs.test.data.remote.utils.NetWorkResult
import com.benhurqs.test.data.remote.utils.toResultFlow
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ActivityRetainedScoped
class CarRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val leadDao: LeadDao,
    @ApplicationContext private val context: Context
) {

    suspend fun getCarList(): Flow<NetWorkResult<CarResponse>> {
        return toResultFlow(context){
            remoteDataSource.getCarList()
        }
    }

    suspend fun sendLead(leads: List<LeadData>): Flow<NetWorkResult<Unit>> {
        return toResultFlow(context){
            remoteDataSource.sendLead(leads)
        }
    }

    fun getLeads() = leadDao.getLead()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun saveLead(leadData: LeadData) {
        leadDao.insertLead(leadData)
    }

    @WorkerThread
    suspend fun removeLeads() {
        leadDao.deleteLeads()
    }
}