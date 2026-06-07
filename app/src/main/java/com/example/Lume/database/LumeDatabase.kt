package com.example.Lume.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.Lume.dao.LivroDao
import com.example.Lume.entity.LivroEntity

@Database(
    entities = [LivroEntity::class],
    version = 1,
    exportSchema = false
)
abstract class LumeDatabase : RoomDatabase() {

    abstract fun livroDao(): LivroDao

    companion object {
        private const val DATABASE_NAME = "lume_database"

        @Volatile
        private var INSTANCE: LumeDatabase? = null

        fun getDatabase(context: Context): LumeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LumeDatabase::class.java,
                    DATABASE_NAME
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}