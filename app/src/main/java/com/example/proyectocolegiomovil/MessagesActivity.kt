package com.example.proyectocolegiomovil

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout

class MessagesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messages)
        
        setupTabs()
    }
    
    private fun setupTabs() {
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.anuncios)))
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.mensajes)))
        
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                // Cambiar contenido según la pestaña seleccionada
            }
            
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }
}
