package com.example.vodic.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.vodic.database.dao.*
import com.example.vodic.database.entity.*

@Database(entities = [Fakultet::class, Smjer::class, Bodovi::class, Pitanja::class, Odgovori::class], version = 1, exportSchema = false)
abstract class VodicDatabase : RoomDatabase() {

    abstract val fakultetDao: FakultetDao
    abstract val smjerDao: SmjerDao
    abstract val bodoviDao: BodoviDao
    abstract val pitanjaDao: PitanjaDao
    abstract val odgovoriDao: OdgovoriDao

    companion object {
        @Volatile
        private var Instance: VodicDatabase? = null

        fun getDatabase(context: Context): VodicDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, VodicDatabase::class.java, "vodic_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}