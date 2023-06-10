package com.example.vodic.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "odgovori",
    foreignKeys = [ForeignKey(
        entity = Pitanja::class,
        childColumns = ["pitanjeId"],
        parentColumns = ["id"]
    )])
data class Odgovori(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val odgovor: String,
    @ColumnInfo(name = "pitanjeId") val pitanjeId: Int,
)