package com.challenge.cconverter.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.challenge.cconverter.data.model.ResponseCurrency

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrencyRecord(record: ResponseCurrency)
}