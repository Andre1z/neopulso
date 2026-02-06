package com.example.neopulso

import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_about -> startActivity(Intent(this, AcercaDe::class.java))
            R.id.menu_contacto -> startActivity(Intent(this, Contacto::class.java))
            R.id.menu_cerrar_sesion -> {
                val prefs = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
                prefs.edit().clear().apply()
                val intent = Intent(this, login::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }
}
