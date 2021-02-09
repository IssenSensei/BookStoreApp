package com.issen.ebooker.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson

class TypeConverters {
    @TypeConverter
    fun listToJson(value: List<String>?) = if (value == null) "" else Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = if (value != "") Gson().fromJson(value, Array<String>::class.java).toList() else listOf()
}