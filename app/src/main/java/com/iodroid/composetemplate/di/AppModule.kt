package com.iodroid.composetemplate.di

import android.content.Context
import androidx.room.Room
import com.iodroid.composetemplate.data.local.CoffeeDb
import com.iodroid.composetemplate.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.gson.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideKtorHttpClient(): HttpClient = HttpClient {
        expectSuccess = true
        install(ContentNegotiation) {
            gson()
        }
        install(HttpTimeout) {
            socketTimeoutMillis = 30_000
            requestTimeoutMillis = 30_000
            connectTimeoutMillis = 30_000
        }
        defaultRequest {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
    }

    @Singleton
    @Provides
    fun provideRoomDb(@ApplicationContext context: Context) : CoffeeDb =
        Room.databaseBuilder(context, CoffeeDb::class.java, Constants.DB_NAME).build()

    @Singleton
    @Provides
    fun provideDao(coffeeDb: CoffeeDb) = coffeeDb.getCoffeeDao()
}