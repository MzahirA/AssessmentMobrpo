package org.d3if2114.newassessment.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LuasEntity::class], version = 1, exportSchema = false)
abstract class LuasDb : RoomDatabase() {
    abstract val dao: LuasDao
    companion object {
        @Volatile
        private var INSTANCE: LuasDb? = null
        fun getInstance(context: Context): LuasDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LuasDb::class.java,
                        "luas.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

    

}