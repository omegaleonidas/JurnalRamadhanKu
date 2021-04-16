package com.example.jurnalramadhanku.Local.model.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alquran")
data class Alquran(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name = "tanggal")
    var tanggal: String? = null,

    @ColumnInfo(name = "tilawah")
    var tilawah: String? = null,

    @ColumnInfo(name = "mengahafal")
    var menghafal: String? = null,

    @ColumnInfo(name = "morajaah")
    var murajaan: String? = null


)

