package com.example.barber.firestore

import android.util.Log
import com.example.barber.CitaModel
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.tasks.await

object
CitaFirestore {
    suspend fun getCitas(): List<CitaModel> = coroutineScope {
        val db = Firebase.firestore
        val listaCita = mutableListOf<CitaModel>()

        try {
            val querySnapshot = db.collection("Barberia").get().await()
            listaCita.addAll(obtenerListaCitas(querySnapshot))
        } catch (e: Exception) {
            Log.e("Error", "Error fetching documents", e)
        }
        listaCita
    }

    suspend fun createCita(citaModel: CitaModel) = coroutineScope {
        val db = Firebase.firestore
        db.collection("Barberia").add(citaModel).await()
    }

    suspend fun updateCita(citaModel: CitaModel) = coroutineScope {
        val db = Firebase.firestore
        val newData = hashMapOf<String, Any>(
            "nombre" to citaModel.nombre,
            "fecha" to citaModel.fecha,
            "horario" to citaModel.horario,
        )
        db.collection("Barberia").document(citaModel.idFirestore!!).update(newData).await()
    }

    suspend fun deleteCita(citaModel: CitaModel) = coroutineScope {
        val db = Firebase.firestore
        db.collection("Barberia").document(citaModel.idFirestore!!).delete()

    }

    fun obtenerListaCitas(querySnapshot: QuerySnapshot): List<CitaModel> {
        val listaCitas = mutableListOf<CitaModel>()

        for (document in querySnapshot.documents) {
            val citaData = document.data
            val citaModel = citaData?.let { convertirACitaModel(it, document.id) }

            listaCitas.add(citaModel!!)
        }

        return listaCitas
    }

    fun convertirACitaModel(citaData: Map<String, Any>, id: String): CitaModel {

        val nombre = citaData["nombre"] as String
        val fecha = citaData["fecha"] as String
        val horario = citaData["horario"] as String
        //val ids = citaData["id"] as Int
        return CitaModel(0, nombre, fecha, horario, id)
    }
}