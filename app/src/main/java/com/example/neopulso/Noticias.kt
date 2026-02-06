package com.example.neopulso

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.neopulso.databinding.ActivityNoticiasBinding

class Noticias : AppCompatActivity() {
    private lateinit var binding: ActivityNoticiasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNoticiasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnContinuar.setOnClickListener {
            val intent = Intent(this, Noticias_main::class.java).apply {
                putExtra("EXTRA_POLITICA", binding.cbPolitica.isChecked)
                putExtra("EXTRA_ECONOMIA", binding.cbEconomia.isChecked)
                putExtra("EXTRA_SS", binding.cbSS.isChecked)
                putExtra("EXTRA_CULTURAL", binding.cbCultural.isChecked)
                putExtra("EXTRA_INTERNACIONAL", binding.cbInternacional.isChecked)
                putExtra("EXTRA_CT", binding.cbCT.isChecked)
            }
            startActivity(intent)
        }
    }
}