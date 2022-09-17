package com.iodroid.composetemplate.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Coffee(
    val description: String = "",
    @PrimaryKey(autoGenerate = false) val id: Int = -1,
    val image: String = "",
    val ingredients: List<String> = emptyList(),
    val title: String = ""
)