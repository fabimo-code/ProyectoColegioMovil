package com.example.proyectocolegiomovil  // <-- CHANGE this to your package!

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class ProfileActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_EDIT_MODE = "EXTRA_EDIT_MODE"
        private const val PREFS = "profile_prefs"
    }

    // View (solo lectura)
    private lateinit var tvNombre: TextView
    private lateinit var tvCorreo: TextView
    private lateinit var tvRol: TextView
    private lateinit var tvTelefono: TextView

    // Edit (registro/edición)
    private lateinit var tilNombre: TextInputLayout
    private lateinit var tilCorreo: TextInputLayout
    private lateinit var tilRol: TextInputLayout
    private lateinit var tilTelefono: TextInputLayout

    private lateinit var etNombre: TextInputEditText
    private lateinit var etCorreo: TextInputEditText
    private lateinit var etRol: TextInputEditText
    private lateinit var etTelefono: TextInputEditText

    private lateinit var btnGuardar: MaterialButton
    private lateinit var btnCerrarSesion: MaterialButton

    private var editMode: Boolean = false
    private val prefs: SharedPreferences by lazy {
        getSharedPreferences(PREFS, MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile) // layout name must be activity_profile.xml

        bindViews()
        editMode = intent?.getBooleanExtra(EXTRA_EDIT_MODE, false) == true
        loadDataIntoViews()
        applyMode(editMode)

        btnGuardar.setOnClickListener {
            if (editMode) {
                if (!validate()) return@setOnClickListener
                saveDataFromInputs()
                applyMode(false)
                Toast.makeText(this, "Perfil actualizado", Toast.LENGTH_SHORT).show()
            } else {
                // En modo perfil, el botón actúa como "Editar"
                applyMode(true)
            }
        }

        btnCerrarSesion.setOnClickListener {
            prefs.edit().clear().apply()
            val i = Intent(this, LoginActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(i)
            finish()
        }
    }

    private fun bindViews() {
        tvNombre = findViewById(R.id.tvNombre)
        tvCorreo = findViewById(R.id.tvCorreo)
        tvRol = findViewById(R.id.tvRol)
        tvTelefono = findViewById(R.id.tvTelefono)

        tilNombre = findViewById(R.id.tilNombre)
        tilCorreo = findViewById(R.id.tilCorreo)
        tilRol = findViewById(R.id.tilRol)
        tilTelefono = findViewById(R.id.tilTelefono)

        etNombre = findViewById(R.id.etNombre)
        etCorreo = findViewById(R.id.etCorreo)
        etRol = findViewById(R.id.etRol)
        etTelefono = findViewById(R.id.etTelefono)

        btnGuardar = findViewById(R.id.btnGuardar)
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion)
    }

    private fun applyMode(editing: Boolean) {
        editMode = editing
        // Solo lectura visible cuando NO estamos editando
        val viewVis = if (editing) View.GONE else View.VISIBLE
        // Inputs visibles cuando SÍ estamos editando
        val editVis = if (editing) View.VISIBLE else View.GONE

        tvNombre.visibility = viewVis
        tvCorreo.visibility = viewVis
        tvRol.visibility = viewVis
        tvTelefono.visibility = viewVis

        tilNombre.visibility = editVis
        tilCorreo.visibility = editVis
        tilRol.visibility = editVis
        tilTelefono.visibility = editVis

        btnGuardar.text = if (editing) "Guardar cambios" else "Editar perfil"
    }

    private fun loadDataIntoViews() {
        val nombre = prefs.getString("nombre", "") ?: ""
        val correo = prefs.getString("correo", "") ?: ""
        val rol = prefs.getString("rol", "") ?: ""
        val telefono = prefs.getString("telefono", "") ?: ""

        // Vistas de solo lectura
        tvNombre.text = nombre.ifEmpty { "—" }
        tvCorreo.text = correo.ifEmpty { "—" }
        tvRol.text = rol.ifEmpty { "—" }
        tvTelefono.text = telefono.ifEmpty { "—" }

        // Inputs
        etNombre.setText(nombre)
        etCorreo.setText(correo)
        etRol.setText(rol)
        etTelefono.setText(telefono)
    }

    private fun validate(): Boolean {
        var ok = true

        if (etNombre.text.isNullOrBlank()) {
            tilNombre.error = "Ingresa tu nombre"
            ok = false
        } else tilNombre.error = null

        if (etCorreo.text.isNullOrBlank()) {
            tilCorreo.error = "Ingresa tu correo"
            ok = false
        } else tilCorreo.error = null

        if (etRol.text.isNullOrBlank()) {
            tilRol.error = "Ingresa tu rol"
            ok = false
        } else tilRol.error = null

        if (etTelefono.text.isNullOrBlank()) {
            tilTelefono.error = "Ingresa tu teléfono"
            ok = false
        } else tilTelefono.error = null

        return ok
    }

    private fun saveDataFromInputs() {
        val nombre = etNombre.text?.toString()?.trim().orEmpty()
        val correo = etCorreo.text?.toString()?.trim().orEmpty()
        val rol = etRol.text?.toString()?.trim().orEmpty()
        val telefono = etTelefono.text?.toString()?.trim().orEmpty()

        prefs.edit()
            .putString("nombre", nombre)
            .putString("correo", correo)
            .putString("rol", rol)
            .putString("telefono", telefono)
            .apply()

        // Refrescar vistas de lectura
        tvNombre.text = nombre.ifEmpty { "—" }
        tvCorreo.text = correo.ifEmpty { "—" }
        tvRol.text = rol.ifEmpty { "—" }
        tvTelefono.text = telefono.ifEmpty { "—" }
    }
}
