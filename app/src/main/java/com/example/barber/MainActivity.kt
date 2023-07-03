package com.example.barber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.barber.databinding.ActivityMainBinding
import com.example.barber.CitaModel
import com.example.barber.room.RoomDabase
import com.example.barber.viewModel.CitaViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CitaViewModel
    private lateinit var adapterCitas: CitaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.rvRecycler.layoutManager = LinearLayoutManager(this)
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(CitaViewModel::class.java)
        observeEvents()
    }
    private fun observeEvents() {
        viewModel.listCitas.observe(this, Observer { list ->
            list?.let {
                adapterCitas = CitaAdapter(it,viewModel)
                binding.rvRecycler.adapter = adapterCitas
                adapterCitas.notifyDataSetChanged()
            }
        })

    }
    companion object {
        //var isDelete = false
        //lateinit var citeSelect : CitaModel
        //lateinit var viewModel: CitaViewModel

    }
}