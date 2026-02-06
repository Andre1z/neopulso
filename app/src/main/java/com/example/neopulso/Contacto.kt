package com.example.neopulso

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.neopulso.databinding.ActivityContactoBinding

class Contacto : AppCompatActivity() {
    private lateinit var binding: ActivityContactoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityContactoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.llPhone.setOnClickListener{
            val intent = Intent(Intent.ACTION_DIAL).apply{
                data = "tel:+34345678901".toUri()
            }
            startActivity(intent)
        }
        binding.llEmail.setOnClickListener{
            val intent = Intent(Intent.ACTION_SENDTO).apply{
                data = "mailto:info@neopulso.com".toUri()
            }
            startActivity(intent)
        }
        binding.llWebsite.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW).apply{
                data = "https://www.neopulso.com".toUri()
            }
            startActivity(intent)
        }
        binding.llUbicacion.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply{
                data = "geo:41.6199,0.6273".toUri()
            }
            startActivity(intent)
        }
    }
}