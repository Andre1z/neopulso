package com.example.neopulso

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.neopulso.databinding.ActivityLoginBinding

class login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent.getBooleanExtra("cuenta_creada", false)) {
            Toast.makeText(this, "Cuenta creada, por favor inicie sesión", Toast.LENGTH_LONG).show()
        }
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnLogin.setOnClickListener {

            val inputUser = binding.etEmail.text.toString()
            val inputPass = binding.etPassword.text.toString()

            val prefs = getSharedPreferences("usuarios", MODE_PRIVATE)

            val userGuardado = prefs.getString("usuario", null)
            val emailGuardado = prefs.getString("correo", null)
            val passGuardado = prefs.getString("password", null)

            val loginCorrecto =
                (inputUser == userGuardado || inputUser == emailGuardado) &&
                        inputPass == passGuardado

            if (loginCorrecto) {
                Toast.makeText(this, "Sesión iniciada correctamente", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Usuario/Email o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnRegistrate.setOnClickListener { startActivity(Intent(this, Registro::class.java)) }
        //binding.btnAcerca.setOnClickListener { startActivity(Intent(this, AcercaDe::class.java)) }
        //binding.btnContacto.setOnClickListener { startActivity(Intent(this, Contacto::class.java)) }
    }
}