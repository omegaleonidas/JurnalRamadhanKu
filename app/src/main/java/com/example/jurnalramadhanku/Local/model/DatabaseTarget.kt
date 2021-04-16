package com.example.jurnalramadhanku.Local.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jurnalramadhanku.Local.model.model.Alquran
import com.example.jurnalramadhanku.Local.model.model.Kajian
import com.example.jurnalramadhanku.Local.model.model.Target

@Database(entities = arrayOf(Target::class,Kajian::class,Alquran::class), version = 1)
abstract class DatabaseTarget : RoomDatabase(){


    abstract fun targetDao(): DaoTarget
    abstract fun kajianDao():DaoKajian
    abstract fun alquranDao():DaoAlquran

    companion object {
        private var INSTANCE: DatabaseTarget? = null
        fun getInstance(context: Context): DatabaseTarget? {

            if (INSTANCE == null) {
                synchronized(DatabaseTarget::class) {
                    INSTANCE =
                        Room.databaseBuilder(
                            context.applicationContext,
                            DatabaseTarget::class.java,
                            "dbtarget1.db"
                        ).build()
                }
            }
            return INSTANCE
        }

    }
}

