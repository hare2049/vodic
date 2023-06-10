package com.example.vodic.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "bodovi",
    foreignKeys = [ForeignKey(
        entity = Smjer::class,
        childColumns = ["smjerId"],
        parentColumns = ["id"]
    )])
data class Bodovi(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val brojPitanja: Int,
    val odgovor: Int,
    @ColumnInfo(name = "smjerId") val smjerId: Int,
    val bodovi: Int
)
