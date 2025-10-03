package com.example.proyectocolegiomovil

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etEmail = findViewById<TextInputEditText>(R.id.etEmail)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)
        val btnLogin = findViewById<MaterialButton>(R.id.btnLogin)
        val tvRegister = findViewById<TextView>(R.id.tvRegister)
        val tvForgot = findViewById<TextView>(R.id.tvForgot)

        btnLogin.setOnClickListener {
            val email = etEmail.text?.toString()?.trim().orEmpty()
            val pass = etPassword.text?.toString()?.trim().orEmpty()

            if (email.isEmpty()) {
                etEmail.error = "Ingresa tu correo o ID"
                return@setOnClickListener
            }
            if (pass.isEmpty()) {
                etPassword.error = "Ingresa tu contraseña"
                return@setOnClickListener
            }

            // TODO: Replace with real auth (API/Firebase/etc.)
            // For now, just go to the teacher home if fields are filled.
            startActivity(Intent(this, TeacherHomeActivity::class.java))
            finish()
        }

        tvRegister.setOnClickListener({ v ->
            val i: Intent = Intent(this, ProfileActivity::class.java)
            i.putExtra(ProfileActivity.EXTRA_EDIT_MODE, true)
            startActivity(i)
        })

        tvForgot.setOnClickListener {
            startActivity(Intent(this, ResetPasswordActivity::class.java))
        }
    }
}
