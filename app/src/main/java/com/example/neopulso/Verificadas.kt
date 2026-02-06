package com.example.neopulso

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.neopulso.databinding.ActivityVerificadasBinding

class Verificadas : AppCompatActivity() {
    private lateinit var binding: ActivityVerificadasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerificadasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mostrar todos los items
        binding.itemBuloTecnologia.visibility = View.VISIBLE
        binding.itemBuloSalud.visibility = View.VISIBLE
        binding.itemBuloSociedad.visibility = View.VISIBLE
        binding.itemBuloCultura.visibility = View.VISIBLE
        binding.itemBuloInternacional.visibility = View.VISIBLE
        binding.itemBuloCiencia.visibility = View.VISIBLE
    }
}