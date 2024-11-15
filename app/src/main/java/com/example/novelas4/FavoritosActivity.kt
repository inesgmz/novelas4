package com.example.novelas4

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FavoritosActivity : ComponentActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var novelaAdaptador: NovelaAdaptador
    private lateinit var listaFavoritos: MutableList<Novela>
    private lateinit var novelaRepository: NovelaRepository

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritos)

        // Initialize the repository
        novelaRepository = NovelaRepository()

        // Retrieve the list of novels from the intent
        val listaNovelas = intent.getParcelableArrayListExtra<Novela>("listaNovelas") ?: mutableListOf()

        // Initialize the list of favorite novels
        listaFavoritos = listaNovelas.filter { it.esFavorita }.toMutableList()

        // Configure the RecyclerView and adapter
        recyclerView = findViewById(R.id.recyclerViewFavoritos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        novelaAdaptador = NovelaAdaptador(
            listaFavoritos,
            { position -> eliminarNovela(position) }, // Callback to delete
            { position -> mostrarNotificacionFavorito(position) }, // Callback for favorites
            false, // Do not show form
            novelaRepository // Pass the repository
        )
        recyclerView.adapter = novelaAdaptador

        // Set up the button to return to the main screen
        findViewById<Button>(R.id.btnVolverInicio).setOnClickListener {
            finish()
        }
    }

    private fun eliminarNovela(position: Int) {
        listaFavoritos.removeAt(position) // Remove the novel from the list
        novelaAdaptador.notifyItemRemoved(position) // Notify the adapter
    }

    private fun mostrarNotificacionFavorito(position: Int) {
        val novela = listaFavoritos[position]
        if (novela.esFavorita) {
            // Handle favorite notification
        } else {
            // Handle unfavorite notification
        }
    }
}