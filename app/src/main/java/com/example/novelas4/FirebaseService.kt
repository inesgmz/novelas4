package com.example.novelas4

import android.content.Context
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.io.ByteArrayOutputStream
import java.util.zip.GZIPOutputStream

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
                // Handle the error
                callback(emptyList())
            }
        })
    }

    fun buscarNovelaPorTitulo(titulo: String, callback: (Novela?) -> Unit) {
        novelasRef.orderByChild("titulo").equalTo(titulo)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val novela = snapshot.children.first().getValue(Novela::class.java)
                        callback(novela)
                    } else {
                        callback(null)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle the error
                    callback(null)
                }
            })
    }

    // Ejemplo de compresión de datos
    fun compressData(data: String): ByteArray {
        val byteArrayOutputStream = ByteArrayOutputStream()
        val gzipOutputStream = GZIPOutputStream(byteArrayOutputStream)
        gzipOutputStream.write(data.toByteArray())
        gzipOutputStream.close()
        return byteArrayOutputStream.toByteArray()
    }

    // Ejemplo de caching con Retrofit
    fun createRetrofitInstance(context: Context, baseUrl: String): Retrofit {
        val cacheSize = (5 * 1024 * 1024).toLong() // 5 MB
        val cache = Cache(context.cacheDir, cacheSize)
        val okHttpClient = OkHttpClient.Builder()
            .cache(cache)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .build()
    }
}