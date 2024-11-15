package com.example.novelas4

import android.content.Context
import android.os.AsyncTask
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class SyncTask(private val context: Context) : AsyncTask<Void, Void, Boolean>() {

    override fun doInBackground(vararg params: Void?): Boolean {
        return try {
            val db = FirebaseFirestore.getInstance()
            val novelasCollection = db.collection("novelas")
            val novelas = mutableListOf<Novela>()

            novelasCollection.get().addOnSuccessListener { result ->
                for (document in result) {
                    val novela = document.toObject(Novela::class.java)
                    novelas.add(novela)
                }
                // Update the local list of novels
                (context as AjustesActivity).updateNovelas(novelas)
            }.addOnFailureListener {
                return@addOnFailureListener
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun onPostExecute(result: Boolean) {
        if (result) {
            Toast.makeText(context, "Novelas sincronizadas correctamente", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Error al sincronizar novelas", Toast.LENGTH_SHORT).show()
        }
    }
}