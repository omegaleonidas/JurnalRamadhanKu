package com.example.jurnalramadhanku.Local.model

import androidx.room.*
import com.example.jurnalramadhanku.Local.model.model.Alquran
import com.example.jurnalramadhanku.Local.model.model.Kajian

@Dao
interface DaoAlquran {

    @Query("SELECT * FROM alquran ")
    fun getAll(): List<Alquran>

    @Insert
    fun insert(Alquran: Alquran)

    @Update
    fun update(Alquran: Alquran)

    @Delete
    fun delete(Alquran: Alquran)

}