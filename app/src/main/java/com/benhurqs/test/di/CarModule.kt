package com.benhurqs.test.di

import android.content.Context
import com.benhurqs.test.data.local.LeadDao
import com.benhurqs.test.data.local.LeadDataBase
import com.benhurqs.test.data.remote.CarService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CarModule {

    private val BASE_URL = "https://wswork.com.br/"

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): CarService =
        retrofit.create(CarService::class.java)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): LeadDataBase =
        LeadDataBase.create(context)

    @Provides
    fun provideDao(database: LeadDataBase): LeadDao {
        return database.leadDao()
    }
}