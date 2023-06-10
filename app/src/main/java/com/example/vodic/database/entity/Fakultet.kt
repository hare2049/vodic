package com.example.vodic.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fakulteti")
data class Fakultet(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val naziv: String
)
