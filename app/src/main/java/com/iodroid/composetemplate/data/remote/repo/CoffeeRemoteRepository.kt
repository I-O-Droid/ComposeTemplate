package com.iodroid.composetemplate.data.remote.repo

import com.iodroid.composetemplate.data.models.Coffee
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import javax.inject.Inject


class CoffeeRemoteRepository @Inject constructor(private val httpClient: HttpClient) {

    suspend fun getCoffee(route: String) : List<Coffee> = httpClient.get {
        url(route)
    }.body()

}