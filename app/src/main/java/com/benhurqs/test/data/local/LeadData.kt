package com.benhurqs.test.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lead_table")
data class LeadData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userName: String,
    val userEmail: String,
    val carId: Int,
)