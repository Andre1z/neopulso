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
import com.example.neopulso.databinding.ActivityDeportesMainBinding

class Deportes_main : AppCompatActivity() {
    private lateinit var binding: ActivityDeportesMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDeportesMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Registrar toolbar si existe en el layout (se recomienda tener MaterialToolbar con id @+id/toolbar)
        try {
            setSupportActionBar(binding.toolbar)
        } catch (e: Exception) {
            Log.w("Deportes_main", "binding.toolbar no encontrado, intentando findViewById", e)
            val tb = findViewById<Toolbar?>(R.id.toolbar)
            tb?.let { setSupportActionBar(it) }
        }

        // Recuperar filtros pasados por Intent
        val futbol = intent.getBooleanExtra("EXTRA_FUTBOL", false)
        val baloncesto = intent.getBooleanExtra("EXTRA_BALONCESTO", false)
        val tenis = intent.getBooleanExtra("EXTRA_TENIS", false)
        val ciclismo = intent.getBooleanExtra("EXTRA_CICLISMO", false)
        val formula1 = intent.getBooleanExtra("EXTRA_FORMULA1", false)
        val motogp = intent.getBooleanExtra("EXTRA_MOTOGP", false)
        val atletismo = intent.getBooleanExtra("EXTRA_ATLETISMO", false)
        val golf = intent.getBooleanExtra("EXTRA_GOLF", false)
        val boxeo = intent.getBooleanExtra("EXTRA_BOXEO", false)
        val rugby = intent.getBooleanExtra("EXTRA_RUGBY", false)

        // Mostrar/ocultar contenedores según extras (manteniendo los ids de tu layout)
        findViewById<View>(R.id.itemFutbol).visibility = if (futbol) View.VISIBLE else View.GONE
        findViewById<View>(R.id.itemBaloncesto).visibility = if (baloncesto) View.VISIBLE else View.GONE
        findViewById<View>(R.id.itemTenis).visibility = if (tenis) View.VISIBLE else View.GONE
        findViewById<View>(R.id.itemCiclismo).visibility = if (ciclismo) View.VISIBLE else View.GONE
        findViewById<View>(R.id.itemFormula1).visibility = if (formula1) View.VISIBLE else View.GONE
        findViewById<View>(R.id.itemMotoGP).visibility = if (motogp) View.VISIBLE else View.GONE
        findViewById<View>(R.id.itemAtletismo).visibility = if (atletismo) View.VISIBLE else View.GONE
        findViewById<View>(R.id.itemGolf).visibility = if (golf) View.VISIBLE else View.GONE
        findViewById<View>(R.id.itemBoxeo).visibility = if (boxeo) View.VISIBLE else View.GONE
        // Si quieres mostrar rugby en algún contenedor, añade el id correspondiente en el layout y aquí.

        // Edge-to-edge insets
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Forzar color del título y del overflow icon si ya existe (usa @color/toolbar_text)
        val textColor = ContextCompat.getColor(this, R.color.toolbar_text)
        try {
            binding.toolbar.setTitleTextColor(textColor)
        } catch (e: Exception) {
            Log.w("Deportes_main", "No se pudo setTitleTextColor en toolbar", e)
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
                val intent = Intent(this, login::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}