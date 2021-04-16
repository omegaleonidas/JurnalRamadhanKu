package com.example.jurnalramadhanku.Local.model.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "kajian")
class Kajian(


    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name = "time")
    var time: String? = null,

    @ColumnInfo(name = "judul")
    var judul: String? = null,
    @ColumnInfo(name = "pemateri")
    var pemateri: String? = null,
    @ColumnInfo(name = "lokasi")
    var lokasi: String? = null,
    @ColumnInfo(name = "quote")
    var quote: String? = null


)
