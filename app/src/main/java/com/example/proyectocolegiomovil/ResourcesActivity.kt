package com.example.proyectocolegiomovil

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class ResourcesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resources)
        
        val subjectName = intent.getStringExtra("subject_name") ?: "Materia"
        
        val titleTextView = findViewById<TextView>(R.id.subject_title)
        titleTextView.text = subjectName
    }
}
