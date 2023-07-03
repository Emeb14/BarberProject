package com.example.barber.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.barber.firestore.CitaFirestore
import com.example.barber.firestore.CitaFirestore.getCitas
import com.example.barber.firestore.CitaFirestore.createCita
import com.example.barber.CitaModel
import com.example.barber.room.CitaDao


class CitaRepository constructor(
    private val CitaDao: CitaDao
)
{
    fun ObtenerTodosCitas(): LiveData<List<CitaModel>> = CitaDao.obtenerCita().asLiveData()
    suspend fun ObtenerTodosCitasFirestore(): List<CitaModel> = getCitas()

    suspend fun guardarCita(cita: CitaModel){
        createCita(cita)
    }
    suspend fun updateCita(cita: CitaModel){
        CitaFirestore.updateCita(cita)
    }
    suspend fun deleteCita(cita: CitaModel){
        CitaFirestore.deleteCita(cita)
    }

}