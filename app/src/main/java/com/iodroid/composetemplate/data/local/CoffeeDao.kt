package com.iodroid.composetemplate.data.local

import androidx.room.*
import com.iodroid.composetemplate.data.models.Coffee
import kotlinx.coroutines.flow.Flow

@Dao
interface CoffeeDao {

    @Query("SELECT * FROM Coffee")
    fun getAllCoffee() : Flow<List<Coffee>>

    @Insert
    suspend fun insertCoffee(coffee: Coffee)

    @Update
    suspend fun updateCoffee(coffee: Coffee)

    @Delete
    suspend fun deleteCoffee(coffee: Coffee)
}