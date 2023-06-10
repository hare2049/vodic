package com.example.vodic.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.vodic.database.entity.Odgovori
import com.example.vodic.database.entity.Pitanja

@Dao
interface OdgovoriDao {
    @Insert
    suspend fun insertOdgovor(odgovor: Odgovori)

    @Query("SELECT * FROM odgovori where pitanjeId = :id")
    fun dajOdgovore(id: Int): List<Odgovori>

    @Query("SELECT odgovor FROM odgovori where pitanjeId = :id")
    fun dajTextOdgovora(id: Int): MutableList<String>
}