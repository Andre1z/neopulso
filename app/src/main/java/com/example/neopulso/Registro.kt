package com.example.neopulso

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.Toast
import com.example.neopulso.databinding.ActivityRegistroBinding

class Registro : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding   // ← DENTRO de la clase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnCrearCuenta.setOnClickListener {

            val nombre = binding.etNombre.text.toString()
            val apellido = binding.etApellido.text.toString()
            val usuario = binding.etUsuario.text.toString()
            val correo = binding.eteCorreo.text.toString()
            val cp = binding.etpCP.text.toString()
            val pass1 = binding.etpContrasena.text.toString()
            val pass2 = binding.etpContrasena2.text.toString()
            val aviso = binding.swAvisoLegal.isChecked

            if (nombre.isEmpty() || usuario.isEmpty() || pass1.isEmpty()) {
                Toast.makeText(this, "Rellena los campos obligatorios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (pass1 != pass2) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!aviso) {
                Toast.makeText(this, "Debes aceptar el aviso legal", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val prefs = getSharedPreferences("usuarios", MODE_PRIVATE)
            val editor = prefs.edit()

            editor.putString("nombre", nombre)
            editor.putString("apellido", apellido)
            editor.putString("usuario", usuario)
            editor.putString("correo", correo)
            editor.putString("cp", cp)
            editor.putString("password", pass1)
            editor.apply()

            Toast.makeText(this, "Cuenta creada correctamente", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, login::class.java)
            intent.putExtra("cuenta_creada", true)
            startActivity(intent)
            finish()
        }
    }
}