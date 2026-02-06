package com.example.neopulso

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.neopulso.databinding.ActivityDeportesBinding
import com.example.neopulso.databinding.ActivityLoginBinding

class Deportes : AppCompatActivity() {
    private lateinit var binding: ActivityDeportesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDeportesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnNext.setOnClickListener {
            val intent = Intent(this, Deportes_main::class.java).apply {
                putExtra("EXTRA_FUTBOL", binding.cbFutbol.isChecked)
                putExtra("EXTRA_BALONCESTO", binding.cbBaloncesto.isChecked)
                putExtra("EXTRA_TENIS", binding.cbTenis.isChecked)
                putExtra("EXTRA_CICLISMO", binding.cbCiclismo.isChecked)
                putExtra("EXTRA_FORMULA1", binding.cbFormula1.isChecked)
                putExtra("EXTRA_MOTOGP", binding.cbMotoGP.isChecked)
                putExtra("EXTRA_ATLETISMO", binding.cbAtletismo.isChecked)
                putExtra("EXTRA_GOLF", binding.cbGolf.isChecked)
                putExtra("EXTRA_BOXEO", binding.cbBoxeo.isChecked)
                putExtra("EXTRA_RUGBY", binding.cbRugby.isChecked)
            }
            startActivity(intent)
        }
    }
}