package com.challenge.cconverter.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.challenge.cconverter.data.database.constants.DBConstants

@Entity(tableName = DBConstants.TABLE_CURRENCY)
data class Currency(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = DBConstants.COL_ID) val serial: Int,
    @ColumnInfo(name = DBConstants.COL_TIMESTAMP, defaultValue = "") val timestamp: Int,
    @ColumnInfo(name = DBConstants.COL_SOURCE) val source: Int,
    @ColumnInfo(name = DBConstants.COL_CONVERSION) val conversion: String,
)