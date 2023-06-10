package com.example.vodic.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pitanja")
data class Pitanja(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val text: String
)