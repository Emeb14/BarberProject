package com.example.barber.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.barber.CitaModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CitaDao {
    @Query("SELECT * from citas")
    fun obtenerCita(): Flow<List<CitaModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun guardarCita(citaModel: CitaModel)

    @Update
    fun actualizarCita(citaModel: CitaModel)

    @Query("DELETE FROM citas")
    suspend fun eliminarTodosCita()

    @Delete
    suspend fun eliminarCitas(citaModel: CitaModel)
}