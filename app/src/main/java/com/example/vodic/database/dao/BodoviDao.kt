package com.example.vodic.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.vodic.database.entity.Bodovi
import kotlinx.coroutines.flow.Flow

@Dao
interface BodoviDao {

    @Query("SELECT * FROM bodovi")
    fun getAllBodovi(): Flow<List<Bodovi>>

    @Upsert
    suspend fun UpsertBodovi(bodovi: Bodovi)

    @Delete
    suspend fun deleteFirma(bodovi: Bodovi)
}