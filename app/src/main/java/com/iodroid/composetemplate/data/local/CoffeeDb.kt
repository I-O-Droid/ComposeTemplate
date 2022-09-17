package com.iodroid.composetemplate.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.iodroid.composetemplate.data.models.Coffee

@Database(entities = [Coffee::class], version = 1, exportSchema = false)
@TypeConverters(CoffeeTypeConverters::class)
abstract class CoffeeDb : RoomDatabase() {
    abstract fun getCoffeeDao(): CoffeeDao
}