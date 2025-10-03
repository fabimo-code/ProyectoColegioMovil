package com.example.proyectocolegiomovil

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        setupBottomNavigation()
        setupSubjectCards()
    }
    
    private fun setupBottomNavigation() {
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_asignaturas -> {
                    // Ya estamos en la pantalla de asignaturas
                    true
                }
                R.id.nav_mensajes -> {
                    val intent = Intent(this, MessagesActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_observador -> {
                    val intent = Intent(this, ObserverActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_tareas -> {
                    val intent = Intent(this, TasksActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }
    
    private fun setupSubjectCards() {
        findViewById<androidx.cardview.widget.CardView>(R.id.matematicas_card).setOnClickListener {
            val intent = Intent(this, SelectorActivity::class.java)
            intent.putExtra("subject_name", getString(R.string.matematicas))
            startActivity(intent)
        }
        
        findViewById<androidx.cardview.widget.CardView>(R.id.historia_card).setOnClickListener {
            val intent = Intent(this, SelectorActivity::class.java)
            intent.putExtra("subject_name", getString(R.string.historia))
            startActivity(intent)
        }
        
        findViewById<androidx.cardview.widget.CardView>(R.id.ciencias_card).setOnClickListener {
            val intent = Intent(this, SelectorActivity::class.java)
            intent.putExtra("subject_name", getString(R.string.ciencias_naturales))
            startActivity(intent)
        }
        
        findViewById<androidx.cardview.widget.CardView>(R.id.literatura_card).setOnClickListener {
            val intent = Intent(this, SelectorActivity::class.java)
            intent.putExtra("subject_name", getString(R.string.literatura))
            startActivity(intent)
        }
        
        findViewById<androidx.cardview.widget.CardView>(R.id.economia_card).setOnClickListener {
            val intent = Intent(this, SelectorActivity::class.java)
            intent.putExtra("subject_name", getString(R.string.economia))
            startActivity(intent)
        }
    }
}