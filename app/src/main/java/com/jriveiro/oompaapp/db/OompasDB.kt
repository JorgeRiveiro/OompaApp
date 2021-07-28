package com.jriveiro.oompaapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jriveiro.oompaapp.model.api.Result
import com.jriveiro.oompaapp.service.OompaDao

@Database(
    entities = [Result::class],
    version = 1,
    exportSchema = false
)
abstract class OompasDB: RoomDatabase() {
    abstract fun oompaDao() : OompaDao

    companion object{
        @Volatile
        private var INSTANCE :OompasDB? = null

        fun getDatabase(context: Context): OompasDB{
            val tempInstance = INSTANCE

            if (tempInstance!= null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    OompasDB::class.java,
                    "oompas_db"
                ).build()

                INSTANCE = instance

                return instance
            }
        }

    }
}