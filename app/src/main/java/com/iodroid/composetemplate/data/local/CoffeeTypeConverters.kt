package com.iodroid.composetemplate.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class CoffeeTypeConverters {
    @TypeConverter
    fun fromString(string: String): List<String> {
        val listType: Type = object : TypeToken<ArrayList<String?>?>() {}.type
        return Gson().fromJson(string, listType)
    }

    @TypeConverter
    fun fromList(list: List<String>): String = Gson().toJson(list)
}