package com.example.vodic.database.dao

import androidx.room.*
import com.example.vodic.database.entity.Fakultet
import com.example.vodic.database.entity.Smjer
import kotlinx.coroutines.flow.Flow

@Dao
interface SmjerDao {

    @Query("SELECT * FROM smjerovi")
    fun getAllSmjerovi(): Flow<List<Smjer>>

    @Insert
    suspend fun insertSmjer(smjer: Smjer)

    @Delete
    suspend fun deleteSmjer(smjer: Smjer)
}