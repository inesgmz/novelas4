package com.example.novelas4

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FirebaseService {

    private val database = FirebaseDatabase.getInstance()
    private val novelasRef = database.getReference("novela")

    fun obtenerNovelas(callback: (List<Novela>) -> Unit) {
        novelasRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val novelas = mutableListOf<Novela>()
                for (novelaSnapshot in snapshot.children) {
                    val novela = novelaSnapshot.getValue(Novela::class.java)
                    if (novela != null) {
                        novelas.add(novela)
                    }
                }
                callback(novelas)
            }

            override fun onCancelled(error: DatabaseError) {
                // Manejar el error
                callback(emptyList())
            }
        })
    }
}