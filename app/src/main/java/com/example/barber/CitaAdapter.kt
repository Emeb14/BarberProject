package com.example.barber

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.barber.databinding.ItemLowBinding
import com.example.barber.CitaModel
import com.example.barber.viewModel.CitaViewModel

class CitaAdapter(var datalist: List<CitaModel>, private val viewModel: CitaViewModel) : RecyclerView.Adapter<CitaAdapter.CitaHolder>() {

    inner class CitaHolder(val binding: ItemLowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitaHolder {
        val binding = ItemLowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CitaHolder(binding)
    }
    override fun getItemCount()=datalist.size
    override fun onBindViewHolder(holder: CitaHolder, position: Int) {
        var item = datalist[position]
        holder.binding.tvId.text = "Codigo de la cita: ${item.idFirestore.toString()}"
        holder.binding.tvFecha.text = "Nombre: ${item.nombre}"
        holder.binding.tvNombre.text = "Fecha: ${item.fecha.toString()}"
        holder.binding.tvHorario.text = "Horario: ${item.horario.toString()}"
        holder.binding.btDelete.setOnClickListener {
            viewModel.deleteCita(item);
            notifyDataSetChanged()
        }
    }


}