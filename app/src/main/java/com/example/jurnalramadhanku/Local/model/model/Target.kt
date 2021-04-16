package com.example.jurnalramadhanku.Local.model.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "target")

data class Target(

    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name = "target")
    var target: String? = null

)


