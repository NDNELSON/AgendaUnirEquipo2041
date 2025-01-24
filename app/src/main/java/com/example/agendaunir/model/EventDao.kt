package com.example.agendaunir.model

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

// ----------------------------------
// DAO (EventDao)
// ----------------------------------
@Dao
interface EventDao {
    // Insertar una entidad
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: Event)

    @Query("SELECT * FROM events ORDER BY startDate ASC")
    fun getAllEvents(): Flow<List<Event>> // Devuelve LiveData

    @Delete
    suspend fun deleteEvent(event: Event)

    @Query("SELECT * FROM events ORDER BY startDate ASC")
    fun getJsonEvents(): List<Event>
}

// ----------------------------------
// BASE DE DATOS (EventDatabase)
// ----------------------------------
@Database(entities = [Event::class], version = 1)
abstract class EventDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao

    companion object {
        @Volatile
        private var INSTANCE: EventDatabase? = null

        fun getDatabase(context: Context): EventDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EventDatabase::class.java,
                    "event_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

// ----------------------------------
// REPOSITORIO (EventRepository)
// ----------------------------------
class EventRepository(private val eventDao: EventDao) {
    val allEvents: Flow<List<Event>> = eventDao.getAllEvents()
    suspend fun insert(event: Event) {
        eventDao.insert(event)
    }
    suspend fun deleteEvent(event: Event) {
        eventDao.deleteEvent(event)
    }

    suspend fun getAllEvents(): List<Event> {
        return withContext(Dispatchers.IO) {
            eventDao.getJsonEvents()
        }
    }
}