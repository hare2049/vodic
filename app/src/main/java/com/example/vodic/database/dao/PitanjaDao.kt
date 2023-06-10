package com.example.vodic.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.vodic.database.entity.Fakultet
import com.example.vodic.database.entity.Pitanja

@Dao
interface PitanjaDao {

    @Insert
    suspend fun insertPitanje(pitanje: Pitanja)

    @Query("SELECT * FROM pitanja")
    fun getAllPitanja(): List<Pitanja>

    @Query("SELECT text FROM pitanja where id = :id")
    fun dajPitanje(id: Int): String

    @Query("SELECT id FROM pitanja where text = :text")
    fun dajIdPitanja(text: String): Int

}