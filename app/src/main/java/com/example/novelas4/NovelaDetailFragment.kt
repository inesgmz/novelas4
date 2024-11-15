package com.example.novelas4

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class NovelaDetailFragment : Fragment() {

    private lateinit var textViewTitulo: TextView
    private lateinit var textViewAutor: TextView
    private lateinit var textViewAño: TextView
    private lateinit var textViewSinopsis: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_novela_detail, container, false)
        textViewTitulo = view.findViewById(R.id.textViewTitulo)
        textViewAutor = view.findViewById(R.id.textViewAutor)
        textViewAño = view.findViewById(R.id.textViewAño)
        textViewSinopsis = view.findViewById(R.id.textViewSinopsis)
        return view
    }

    fun showNovelaDetails(novela: Novela) {
        textViewTitulo.text = novela.titulo
        textViewAutor.text = novela.autor
        textViewAño.text = novela.año.toString()
        textViewSinopsis.text = novela.sinopsis
    }
}