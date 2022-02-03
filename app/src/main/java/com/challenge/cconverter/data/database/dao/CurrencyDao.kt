package com.challenge.cconverter.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.challenge.cconverter.data.database.constants.DBConstants
import com.challenge.cconverter.data.model.ResponseCurrency

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrencyRecord(record: ResponseCurrency)

    @Query("SELECT * FROM " + DBConstants.TABLE_CURRENCY)
    fun getCurrencyRecord(): ResponseCurrency

    @Query("SELECT COUNT("+ DBConstants.COL_ID +") FROM "+DBConstants.TABLE_CURRENCY)
    fun getRecordCount(): Int
}