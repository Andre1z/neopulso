package com.example.neopulso

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.neopulso.databinding.ActivityNoticiasMainBinding

class Noticias_main : AppCompatActivity() {
    private lateinit var binding: ActivityNoticiasMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNoticiasMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val politica = intent.getBooleanExtra("EXTRA_POLITICA", false)
        val economia = intent.getBooleanExtra("EXTRA_ECONOMIA", false)
        val ss = intent.getBooleanExtra("EXTRA_SS", false)
        val cultural = intent.getBooleanExtra("EXTRA_CULTURAL", false)
        val internacional = intent.getBooleanExtra("EXTRA_INTERNACIONAL", false)
        val ct = intent.getBooleanExtra("EXTRA_CT", false)

        if (politica) binding.itemNoticiaPolitica.visibility = View.VISIBLE
        if (economia) binding.itemNoticiaEconomia.visibility = View.VISIBLE
        if (ss) binding.itemNoticiaSS.visibility = View.VISIBLE
        if (cultural) binding.itemNoticiaCultural.visibility = View.VISIBLE
        if (internacional) binding.itemNoticiaInternacional.visibility = View.VISIBLE
        if (ct) binding.itemNoticiaCT.visibility = View.VISIBLE

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}