package com.benhurqs.test.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface LeadDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLead(lead: LeadData)

    @Query("DELETE FROM lead_table")
    suspend fun deleteLocations()

    @Query("SELECT * FROM lead_table ORDER BY id")
    fun getLead(): Flow<List<LeadData>>
}