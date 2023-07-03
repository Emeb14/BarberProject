package com.example.barber

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
@Entity(tableName = "citas")
data class CitaModel(
    @PrimaryKey(autoGenerate = true)  @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "nombre") var nombre: String,
    @ColumnInfo(name = "fecha")  val fecha: String,
    @ColumnInfo(name = "horario") var horario: String,
    val idFirestore: String ?
)