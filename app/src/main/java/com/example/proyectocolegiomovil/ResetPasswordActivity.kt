package com.example.proyectocolegiomovil // <-- cámbialo a TU package real

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class ResetPasswordActivity : AppCompatActivity() {

    private lateinit var tilEmail: TextInputLayout
    private lateinit var etEmail: TextInputEditText
    private lateinit var btnSendCode: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        tilEmail = findViewById(R.id.tilEmail)
        etEmail = findViewById(R.id.etEmail)
        btnSendCode = findViewById(R.id.btnSendCode)

        btnSendCode.setOnClickListener {
            val email = etEmail.text?.toString()?.trim().orEmpty()
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                tilEmail.error = "Ingresa un correo válido"
                return@setOnClickListener
            }
            tilEmail.error = null
            Toast.makeText(this, "Si el correo existe, se enviará un código", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}
