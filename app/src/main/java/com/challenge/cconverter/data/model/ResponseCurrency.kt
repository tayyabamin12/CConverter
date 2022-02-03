package com.challenge.cconverter.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.challenge.cconverter.data.database.constants.DBConstants

@Entity(tableName = DBConstants.TABLE_CURRENCY)
data class ResponseCurrency(
    @ColumnInfo(name = DBConstants.COL_PRIVACY) val privacy: String,
    @ColumnInfo(name = DBConstants.COL_QUOTES) val quotes: Quotes,
    @ColumnInfo(name = DBConstants.COL_SOURCE) val source: String,
    @ColumnInfo(name = DBConstants.COL_SUCCESS) val success: Boolean,
    @ColumnInfo(name = DBConstants.COL_TERM) val terms: String,
    @ColumnInfo(name = DBConstants.COL_TIMESTAMP) val timestamp: Int,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DBConstants.COL_ID) var serial: Int = 0,
    @ColumnInfo(name = DBConstants.COL_TIME) var time: Long = System.currentTimeMillis()
)