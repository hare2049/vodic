package com.example.vodic.database.dao

import androidx.room.*
import com.example.vodic.database.entity.Fakultet

@Dao
interface FakultetDao {

    @Query("SELECT * FROM fakulteti")
    fun getAllFakulteti(): List<Fakultet>

    @Insert
    suspend fun insertFakultet(fakultet: Fakultet)

    @Upsert
    fun upsertFakultet(fakultet: Fakultet)

//    @Delete
//    suspend fun deleteFakultet(fakultet: Fakultet)

    @Query("DELETE FROM fakulteti WHERE naziv='hamza'")
    fun deleteFakultet()
}