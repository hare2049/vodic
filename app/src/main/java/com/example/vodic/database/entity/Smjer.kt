package com.example.vodic.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "smjerovi",
    foreignKeys = [ForeignKey(
        entity = Fakultet::class,
        childColumns = ["fakultetId"],
        parentColumns = ["id"]
    )])
data class Smjer(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val naziv: String,
    @ColumnInfo(name = "fakultetId") val fakultetId: Int,
    val odsjek: String
)
