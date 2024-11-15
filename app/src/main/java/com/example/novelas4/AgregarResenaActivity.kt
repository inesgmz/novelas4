package com.example.novelas4

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.ComponentActivity

class AgregarResenaActivity : ComponentActivity() {

    private lateinit var spinnerNovelas: Spinner
    private lateinit var editTextResena: EditText
    private lateinit var btnGuardarResena: Button
    private lateinit var listaNovelas: List<Novela>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_resena)

        // Initialize UI elements
        spinnerNovelas = findViewById(R.id.spinnerNovelas)
        editTextResena = findViewById(R.id.editTextResena)
        btnGuardarResena = findViewById(R.id.btnGuardarResena)

        // Get the list of novels from the intent
        listaNovelas = intent.getParcelableArrayListExtra("listaNovelas") ?: emptyList()

        // Set up the spinner with the list of novels
        val novelaTitles = listaNovelas.map { it.titulo }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, novelaTitles)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerNovelas.adapter = adapter

        // Set up the button click listener
        btnGuardarResena.setOnClickListener {
            val selectedNovela = listaNovelas[spinnerNovelas.selectedItemPosition]
            val resena = editTextResena.text.toString()

            if (resena.isNotEmpty()) {
                // Save the review
                selectedNovela.resenas.add(resena)
                Toast.makeText(this, "Reseña guardada", Toast.LENGTH_SHORT).show()
                setResult(Activity.RESULT_OK) // Set result to OK
                finish() // Close the activity
            } else {
                Toast.makeText(this, "Por favor, escribe una reseña", Toast.LENGTH_SHORT).show()
            }
        }
    }
}