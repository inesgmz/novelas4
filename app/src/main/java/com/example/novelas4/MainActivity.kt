package com.example.novelas4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var recyclerView: RecyclerView
    private lateinit var novelaAdaptador: NovelaAdaptador
    private lateinit var listaNovelas: MutableList<Novela>
    private val firebaseService = FirebaseService() // Initialize FirebaseService
    private lateinit var editTextTitulo: EditText
    private lateinit var btnSiguiente: Button
    private lateinit var novelaRepository: NovelaRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        try {
            FirebaseApp.initializeApp(this)
        } catch (e: Exception) {
            Toast.makeText(this, "Error initializing Firebase", Toast.LENGTH_SHORT).show()
            return
        }

        // Initialize the repository
        novelaRepository = NovelaRepository()

        // Initialize the list of novels
        listaNovelas = mutableListOf()

        // Configure the RecyclerView and adapter
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        novelaAdaptador = NovelaAdaptador(
            listaNovelas,
            { position -> eliminarNovela(position) }, // Callback to delete
            { position -> mostrarNotificacionFavorito(position) }, // Callback for favorites
            true, // Show form
            novelaRepository // Pass the repository
        )
        recyclerView.adapter = novelaAdaptador

        // Initialize UI elements for novel search
        editTextTitulo = findViewById(R.id.editTextTitulo)
        btnSiguiente = findViewById(R.id.btnSiguiente)

        btnSiguiente.setOnClickListener {
            val titulo = editTextTitulo.text.toString()
            if (titulo.isNotEmpty()) {
                lifecycleScope.launch {
                    // Search for the novel
                    novelaRepository.buscarNovelaPorTitulo(titulo) { novela ->
                        if (novela != null) {
                            val intent = Intent(this@MainActivity, DetallesNovelaActivity::class.java).apply {
                                putExtra("novela", novela)
                            }
                            startActivity(intent)
                        } else {
                            Toast.makeText(this@MainActivity, "Novela no encontrada", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Por favor, introduce un título", Toast.LENGTH_SHORT).show()
            }
        }

        // Set up button listeners
        findViewById<Button>(R.id.btnFavoritos).setOnClickListener {
            val intent = Intent(this, FavoritosActivity::class.java)
            intent.putParcelableArrayListExtra("listaNovelas", ArrayList(listaNovelas))
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnAjustes).setOnClickListener {
            val intent = Intent(this, AjustesActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnAgregarResena).setOnClickListener {
            val intent = Intent(this, AgregarResenaActivity::class.java)
            intent.putParcelableArrayListExtra("listaNovelas", ArrayList(listaNovelas))
            startActivityForResult(intent, REQUEST_CODE_AGREGAR_RESENA)
        }

        // Fetch novels from Firebase
        fetchNovelasFromFirebase()
    }

    private fun fetchNovelasFromFirebase() {
        firebaseService.obtenerNovelas { novelas ->
            listaNovelas.clear()
            listaNovelas.addAll(novelas)
            novelaAdaptador.notifyDataSetChanged()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_AGREGAR_RESENA && resultCode == RESULT_OK) {
            // Update the RecyclerView
            novelaAdaptador.notifyDataSetChanged()
        }
    }

    // Function to delete a novel
    private fun eliminarNovela(position: Int) {
        listaNovelas.removeAt(position) // Remove the novel from the list
        novelaAdaptador.notifyItemRemoved(position) // Notify the adapter
        Toast.makeText(this, "Novela eliminada", Toast.LENGTH_SHORT).show()
    }

    private fun mostrarNotificacionFavorito(position: Int) {
        val novela = listaNovelas[position]
        if (novela.esFavorita) {
            Toast.makeText(this, "${novela.titulo} añadida a favoritos", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "${novela.titulo} eliminada de favoritos", Toast.LENGTH_SHORT).show()
        }
    }

    fun showNovelaDetails(novela: Novela) {
        val intent = Intent(this, DetallesNovelaActivity::class.java).apply {
            putExtra("novela", novela)
        }
        startActivity(intent)
    }

    companion object {
        const val REQUEST_CODE_AGREGAR_RESENA = 1
    }
}