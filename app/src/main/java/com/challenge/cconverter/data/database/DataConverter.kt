package com.challenge.cconverter.data.database

import androidx.room.TypeConverter
import com.challenge.cconverter.data.model.Quotes
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataConverter {

    @TypeConverter
    fun fromList(value: Quotes): String {
        return Gson().toJson(value, object : TypeToken<Quotes>() {}.type)
    }

    @TypeConverter
    fun toList(value: String): Quotes {
        return Gson().fromJson(value, object : TypeToken<Quotes>() {}.type)
    }
}