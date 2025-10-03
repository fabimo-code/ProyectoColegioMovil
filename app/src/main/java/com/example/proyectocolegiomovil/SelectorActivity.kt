package com.example.proyectocolegiomovil

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class SelectorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selector)
        
        val subjectName = intent.getStringExtra("subject_name") ?: "Materia"
        
        val titleTextView = findViewById<TextView>(R.id.subject_title)
        titleTextView.text = subjectName
        
        setupClickListeners(subjectName)
    }
    
    private fun setupClickListeners(subjectName: String) {
        findViewById<androidx.cardview.widget.CardView>(R.id.recursos_card).setOnClickListener {
            val intent = Intent(this, ResourcesActivity::class.java)
            intent.putExtra("subject_name", subjectName)
            startActivity(intent)
        }
        
        findViewById<androidx.cardview.widget.CardView>(R.id.calificaciones_card).setOnClickListener {
            val intent = Intent(this, GradesActivity::class.java)
            intent.putExtra("subject_name", subjectName)
            startActivity(intent)
        }
    }
}
