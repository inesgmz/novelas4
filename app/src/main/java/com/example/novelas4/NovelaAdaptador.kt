package com.example.novelas4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NovelaAdaptador(
    private val listaNovelas: MutableList<Novela>,
    private val onDeleteClick: (Int) -> Unit,
    private val onFavoritoClick: (Int) -> Unit,
    private val showForm: Boolean,
    private val novelaRepository: NovelaRepository
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_FORM = 0
        private const val VIEW_TYPE_NOVELA = 1
    }

    // ViewHolder for the form
    inner class FormViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val editTextAutor: EditText = itemView.findViewById(R.id.editTextAutor)
        val editTextAño: EditText = itemView.findViewById(R.id.editTextAño)
        val editTextSinopsis: EditText = itemView.findViewById(R.id.editTextSinopsis)
        val btnAgregarNovela: Button = itemView.findViewById(R.id.btnAgregarNovela)

        init {
            // Initially hide additional fields
            editTextAutor.visibility = View.GONE
            editTextAño.visibility = View.GONE
            editTextSinopsis.visibility = View.GONE
            btnAgregarNovela.visibility = View.GONE
        }
    }

    // ViewHolder for novels
    inner class NovelaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTitulo: TextView = itemView.findViewById(R.id.textViewTitulo)
        val textViewAutor: TextView = itemView.findViewById(R.id.textViewAutor)
        val textViewAño: TextView = itemView.findViewById(R.id.textViewAño)
        val textViewSinopsis: TextView = itemView.findViewById(R.id.textViewSinopsis)
        val btnFavorito: Button = itemView.findViewById(R.id.textViewbtnFavorito)
        val textViewReseñas: TextView = itemView.findViewById(R.id.textViewReseñas)

        fun bind(novela: Novela) {
            textViewTitulo.text = novela.titulo
            textViewAutor.text = novela.autor
            textViewAño.text = novela.año.toString()
            textViewSinopsis.text = novela.sinopsis
            btnFavorito.text = if (novela.esFavorita) "★" else "☆"
            textViewReseñas.text = novela.resenas.joinToString(separator = "\n") { "• $it" }

            btnFavorito.setOnClickListener {
                novela.esFavorita = !novela.esFavorita
                btnFavorito.text = if (novela.esFavorita) "★" else "☆"
                onFavoritoClick(adapterPosition - if (showForm) 1 else 0)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_FORM -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_novela_form, parent, false)
                FormViewHolder(view)
            }
            VIEW_TYPE_NOVELA -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_novela, parent, false)
                NovelaViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FormViewHolder -> {
                // No need to do anything here for FormViewHolder
            }
            is NovelaViewHolder -> {
                val novela = listaNovelas[position - if (showForm) 1 else 0]
                holder.bind(novela)
            }
        }
    }

    override fun getItemCount(): Int {
        return listaNovelas.size + if (showForm) 1 else 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (showForm && position == 0) VIEW_TYPE_FORM else VIEW_TYPE_NOVELA
    }

    fun agregarNovela(novela: Novela) {
        listaNovelas.add(novela)
        notifyItemInserted(listaNovelas.size)
    }

    fun updateNovelas(novelas: List<Novela>) {
        listaNovelas.clear()
        listaNovelas.addAll(novelas)
        notifyDataSetChanged()
    }
}