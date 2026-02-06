package com.example.neopulso

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.neopulso.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        enableEdgeToEdge()

        lanzarNoticias()
        lanzarDeportes()
        lanzarnoticias_verificadas()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun lanzarNoticias(){ // Al pulsar el boton noticias se lanza la pantalla de noticias
        binding.btnNoticias.setOnClickListener {
            startActivity(Intent(this, Noticias::class.java))
        }
    }

    fun lanzarDeportes() { // Al pulsar el boton deportes se lanza la pantalla de deportes
        binding.btnDeportes.setOnClickListener {
            startActivity(Intent(this, Deportes::class.java))
        }
    }
    fun lanzarnoticias_verificadas() {
        binding.btnVerficadas.setOnClickListener {
            startActivity(Intent(this, Verificadas::class.java))
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

}