package com.example.novelas4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NovelaListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var novelaAdaptador: NovelaAdaptador
    private lateinit var listaNovelas: MutableList<Novela>
    private lateinit var novelaRepository: NovelaRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_novela_list, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewNovelas)
        recyclerView.layoutManager = LinearLayoutManager(context)
        listaNovelas = mutableListOf() // Initialize with your data
        novelaRepository = NovelaRepository() // Initialize the repository
        novelaAdaptador = NovelaAdaptador(
            listaNovelas,
            { position -> onDeleteClick(position) },
            { position -> onFavoritoClick(position) },
            true,
            novelaRepository // Pass the repository
        )
        recyclerView.adapter = novelaAdaptador
        return view
    }

    private fun onNovelaSelected(position: Int) {
        val novela = listaNovelas[position]
        (activity as? MainActivity)?.showNovelaDetails(novela)
    }

    private fun onFavoritoClick(position: Int) {
        val novela = listaNovelas[position]
        novela.esFavorita = !novela.esFavorita
        novelaAdaptador.notifyItemChanged(position)
    }

    private fun onDeleteClick(position: Int) {
        listaNovelas.removeAt(position)
        novelaAdaptador.notifyItemRemoved(position)
    }
}