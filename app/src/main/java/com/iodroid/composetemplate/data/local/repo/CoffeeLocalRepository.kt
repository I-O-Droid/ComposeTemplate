package com.iodroid.composetemplate.data.local.repo

import com.iodroid.composetemplate.data.local.CoffeeDao
import com.iodroid.composetemplate.data.models.Coffee
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CoffeeLocalRepository @Inject constructor(private val dao: CoffeeDao) {

    suspend fun getAllCoffee(): Flow<List<Coffee>> = withContext(Dispatchers.IO) {
        dao.getAllCoffee()
    }

    suspend fun insertCoffee(coffee: Coffee) = withContext(Dispatchers.IO) {
        dao.insertCoffee(coffee)
    }
}