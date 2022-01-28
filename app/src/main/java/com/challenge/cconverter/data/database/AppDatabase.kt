package com.challenge.cconverter.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.challenge.cconverter.data.database.constants.DBConstants
import com.challenge.cconverter.data.database.dao.CurrencyDao
import com.challenge.cconverter.data.database.entity.Currency
import com.challenge.cconverter.data.model.ResponseCurrency

@TypeConverters(DataConverter::class)
@Database(entities = arrayOf(ResponseCurrency::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun CurrencyDao(): CurrencyDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, DBConstants.APP_DATABASE
                    ).allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
