package com.example.neopulso

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
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

        // Conectar toolbar (asegúrate de tener <MaterialToolbar android:id="@+id/toolbar"> en el layout)
        try {
            setSupportActionBar(binding.toolbar)
        } catch (e: Exception) {
            Log.w("Noticias", "binding.toolbar no encontrado, intentando findViewById", e)
            val tb = findViewById<Toolbar?>(R.id.toolbar)
            tb?.let { setSupportActionBar(it) }
        }

        // Ajuste de insets para edge-to-edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Botón continuar
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

        // Forzar color del título y del overflow icon si ya existe
        val textColor = ContextCompat.getColor(this, R.color.toolbar_text)
        binding.toolbar.setTitleTextColor(textColor)
        binding.toolbar.overflowIcon?.let { DrawableCompat.setTint(it, textColor) }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.d("Noticias", "onCreateOptionsMenu called")
        menuInflater.inflate(R.menu.main_menu, menu)

        // Aplicar tint a iconos del menú y al overflow icon después de inflar
        val textColor = ContextCompat.getColor(this, R.color.toolbar_text)
        binding.toolbar.overflowIcon?.let { DrawableCompat.setTint(it, textColor) }

        binding.toolbar.menu?.let { m ->
            for (i in 0 until m.size()) {
                val mi = m.getItem(i)
                mi.icon?.let { icon ->
                    val wrapped = DrawableCompat.wrap(icon)
                    DrawableCompat.setTint(wrapped, textColor)
                    mi.icon = wrapped
                }
            }
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_about -> {
                startActivity(Intent(this, AcercaDe::class.java))
                true
            }
            R.id.menu_contacto -> {
                startActivity(Intent(this, Contacto::class.java))
                true
            }
            R.id.menu_cerrar_sesion -> {
                cerrarSesion()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun cerrarSesion() {
        val prefs = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        prefs.edit().clear().apply()
        // FirebaseAuth.getInstance().signOut() // descomenta si usas Firebase
        val intent = Intent(this, login::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}