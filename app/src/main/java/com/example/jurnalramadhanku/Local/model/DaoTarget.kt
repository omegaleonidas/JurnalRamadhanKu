package com.example.jurnalramadhanku.Local.model

import androidx.room.*
import com.example.jurnalramadhanku.Local.model.model.Target


@Dao
interface DaoTarget {

    @Query("SELECT * FROM target ")
    fun getAll(): List<Target>

    @Insert
    fun insert(jadwal: Target)

    @Update
    fun update(jadwal:Target)

    @Delete
    fun delete(jadwal: Target)
}