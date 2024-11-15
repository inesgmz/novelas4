package com.example.novelas4

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity

class DetallesNovelaActivity : ComponentActivity() {

    private lateinit var textViewTitulo: TextView
    private lateinit var textViewAutor: TextView
    private lateinit var textViewAño: TextView
    private lateinit var textViewSinopsis: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_novela)

        textViewTitulo = findViewById(R.id.textViewTitulo)
        textViewAutor = findViewById(R.id.textViewAutor)
        textViewAño = findViewById(R.id.textViewAño)
        textViewSinopsis = findViewById(R.id.textViewSinopsis)

        val novela = intent.getParcelableExtra<Novela>("novela")
        novela?.let {
            textViewTitulo.text = it.titulo
            textViewAutor.text = it.autor
            textViewAño.text = it.año.toString()
            textViewSinopsis.text = it.sinopsis
        }
    }
}