package com.example.jurnalramadhanku.Local.model

import androidx.room.*
import com.example.jurnalramadhanku.Local.model.model.Kajian
import com.example.jurnalramadhanku.Local.model.model.Target

@Dao
interface DaoKajian {

    @Query("SELECT * FROM kajian ")
    fun getAll(): List<Kajian>

    @Insert
    fun insert(kajian: Kajian)

    @Update
    fun update(kajian: Kajian)

    @Delete
    fun delete(kajian: Kajian)

}