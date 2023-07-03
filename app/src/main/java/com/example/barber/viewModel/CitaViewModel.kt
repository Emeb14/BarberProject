package com.example.barber.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.barber.CitaModel
import com.example.barber.repository.CitaRepository
import com.example.barber.room.RoomDabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CitaViewModel(application: Application) : AndroidViewModel(application) {
    val listCitas  : MutableLiveData<List<CitaModel>> =MutableLiveData()
    val repository : CitaRepository

    init {
        val citaDao = RoomDabase.getDatabase(application).CitaDao()
        repository = CitaRepository(citaDao)
        //listGastos = repository.ObtenerTodosGastos()
        GetCitaFirestore()
    }
    fun insertCita(cita: CitaModel) =
        viewModelScope.launch(Dispatchers.IO) { repository.guardarCita(cita) }

    fun updateCita(cita: CitaModel) =
        viewModelScope.launch(Dispatchers.IO) { repository.updateCita(cita) }

    fun deleteCita(cita: CitaModel) =
        viewModelScope.launch(Dispatchers.IO) { repository.deleteCita(cita) }
    fun GetCitaFirestore(){
        viewModelScope.launch(Dispatchers.IO) {
            var lista = repository.ObtenerTodosCitasFirestore()
            listCitas.postValue(lista)
        }
    }
}