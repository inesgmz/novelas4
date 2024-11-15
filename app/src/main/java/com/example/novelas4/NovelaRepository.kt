package com.example.novelas4

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects

class NovelaRepository {

    private val db = FirebaseFirestore.getInstance()

    fun buscarNovelaPorTitulo(titulo: String, callback: (Novela?) -> Unit) {
        db.collection("novela").whereEqualTo("titulo:", titulo).get()
            .addOnSuccessListener { snapshot ->
                val novelas = snapshot.toObjects<Novela>()
                if (novelas.isNotEmpty()) {
                    callback(novelas[0])
                } else {
                    callback(null)
                }
            }
            .addOnFailureListener {
                callback(null)
            }
    }

    fun agregarNovela(novela: Novela) {
        db.collection("novela").add(novela)
    }
}