package com.example.neopulso

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
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

        // Registrar toolbar si existe en el layout (se recomienda tener MaterialToolbar con id @+id/toolbar)
        try {
            setSupportActionBar(binding.toolbar)
        } catch (e: Exception) {
            Log.w("Noticias_main", "binding.toolbar no encontrado, intentando findViewById", e)
            val tb = findViewById<Toolbar?>(R.id.toolbar)
            tb?.let { setSupportActionBar(it) }
        }

        // Recuperar filtros pasados por Intent
        val politica = intent.getBooleanExtra("EXTRA_POLITICA", false)
        val economia = intent.getBooleanExtra("EXTRA_ECONOMIA", false)
        val ss = intent.getBooleanExtra("EXTRA_SS", false)
        val cultural = intent.getBooleanExtra("EXTRA_CULTURAL", false)
        val internacional = intent.getBooleanExtra("EXTRA_INTERNACIONAL", false)
        val ct = intent.getBooleanExtra("EXTRA_CT", false)

        // Mostrar/ocultar contenedores según extras (manteniendo los ids de tu layout)
        binding.itemNoticiaPolitica.visibility = if (politica) View.VISIBLE else View.GONE
        binding.itemNoticiaEconomia.visibility = if (economia) View.VISIBLE else View.GONE
        binding.itemNoticiaSS.visibility = if (ss) View.VISIBLE else View.GONE
        binding.itemNoticiaCultural.visibility = if (cultural) View.VISIBLE else View.GONE
        binding.itemNoticiaInternacional.visibility = if (internacional) View.VISIBLE else View.GONE
        binding.itemNoticiaCT.visibility = if (ct) View.VISIBLE else View.GONE

        // Edge-to-edge insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Forzar color del título y del overflow icon si ya existe (usa @color/toolbar_text)
        val textColor = ContextCompat.getColor(this, R.color.toolbar_text)
        try {
            binding.toolbar.setTitleTextColor(textColor)
        } catch (e: Exception) {
            Log.w("Noticias_main", "No se pudo setTitleTextColor en toolbar", e)
        }
        binding.toolbar.overflowIcon?.let { DrawableCompat.setTint(it, textColor) }
    }

    // Inflar el menú y aplicar tint a iconos
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
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

    // Manejar selección de items del menú
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
        // Si usas Firebase: FirebaseAuth.getInstance().signOut()

        val intent = Intent(this, login::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}