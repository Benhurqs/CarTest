package com.benhurqs.test.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

private const val DB_NAME = "car_lead_database"

@Database(entities = [(LeadData::class)], version = 1)
abstract class LeadDataBase : RoomDatabase() {

    abstract fun leadDao(): LeadDao

    companion object {
        fun create(context: Context): LeadDataBase {

            return Room.databaseBuilder(
                context,
                LeadDataBase::class.java,
                DB_NAME
            ).build()
        }
    }
}