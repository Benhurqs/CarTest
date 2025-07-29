package com.benhurqs.test.data

import android.content.Context
import com.benhurqs.test.data.remote.RemoteDataSource
import com.benhurqs.test.data.remote.utils.NetWorkResult
import com.benhurqs.test.data.remote.utils.toResultFlow
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ActivityRetainedScoped
class CarRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) {

    suspend fun getCarList(context: Context): Flow<NetWorkResult<CarResponse>> {
        return toResultFlow(context){
            remoteDataSource.getCarList()
        }
    }
}