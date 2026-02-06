package com.example.neopulso

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.neopulso.databinding.ActivityDeportesMainBinding
import com.example.neopulso.databinding.ActivityNoticiasMainBinding

class Deportes_main : AppCompatActivity() {
    private lateinit var binding: ActivityDeportesMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDeportesMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val futbol = intent.getBooleanExtra("EXTRA_FUTBOL", false)
        val baloncesto = intent.getBooleanExtra("EXTRA_BALONCESTO", false)
        val tenis = intent.getBooleanExtra("EXTRA_TENIS", false)
        val ciclismo = intent.getBooleanExtra("EXTRA_CICLISMO", false)
        val formula1 = intent.getBooleanExtra("EXTRA_FORMULA1", false)
        val motogp = intent.getBooleanExtra("EXTRA_MOTOGP", false)
        val atletismo = intent.getBooleanExtra("EXTRA_ATLETISMO", false)
        val golf = intent.getBooleanExtra("EXTRA_GOLF", false)
        val boxeo = intent.getBooleanExtra("EXTRA_BOXEO", false)
        val rugby = intent.getBooleanExtra("EXTRA_RUGBY", false) // referencias a los contenedores

        findViewById<View>(R.id.itemFutbol).visibility = if (futbol) View.VISIBLE else View.GONE
        findViewById<View>(R.id.itemBaloncesto).visibility = if (baloncesto) View.VISIBLE else View.GONE
        findViewById<View>(R.id.itemTenis).visibility = if (tenis) View.VISIBLE else View.GONE
        findViewById<View>(R.id.itemCiclismo).visibility = if (ciclismo) View.VISIBLE else View.GONE
        findViewById<View>(R.id.itemFormula1).visibility = if (formula1) View.VISIBLE else View.GONE
        findViewById<View>(R.id.itemMotoGP).visibility = if (motogp) View.VISIBLE else View.GONE
        findViewById<View>(R.id.itemAtletismo).visibility = if (atletismo) View.VISIBLE else View.GONE
        findViewById<View>(R.id.itemGolf).visibility = if (golf) View.VISIBLE else View.GONE
        findViewById<View>(R.id.itemBoxeo).visibility = if (boxeo) View.VISIBLE else View.GONE

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}