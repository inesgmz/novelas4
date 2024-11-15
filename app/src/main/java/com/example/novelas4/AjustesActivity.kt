package com.example.novelas4

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatDelegate
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class AjustesActivity : ComponentActivity() {

    private lateinit var layout: LinearLayout
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var listaNovelas: MutableList<Novela>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajustes)

        layout = findViewById(R.id.layoutAjustes)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        listaNovelas = mutableListOf()

        findViewById<Button>(R.id.btnBlanco).setOnClickListener {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            layout.setBackgroundColor(Color.WHITE)
            saveThemePreference(AppCompatDelegate.MODE_NIGHT_NO)
        }

        findViewById<Button>(R.id.btnNegro).setOnClickListener {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            layout.setBackgroundColor(Color.BLACK)
            saveThemePreference(AppCompatDelegate.MODE_NIGHT_YES)
        }

        findViewById<Button>(R.id.btnVolverInicio).setOnClickListener {
            finish()
        }

        val btnSync = findViewById<Button>(R.id.btnSync)
        btnSync.setOnClickListener {
            SyncTask(this).execute()
        }

        // Load saved theme preference
        loadThemePreference()
    }

    private fun saveThemePreference(mode: Int) {
        with(sharedPreferences.edit()) {
            putInt("theme_mode", mode)
            apply()
        }
    }

    private fun loadThemePreference() {
        val mode = sharedPreferences.getInt("theme_mode", AppCompatDelegate.MODE_NIGHT_NO)
        AppCompatDelegate.setDefaultNightMode(mode)
    }

    fun updateNovelas(novelas: List<Novela>) {
        listaNovelas.clear()
        listaNovelas.addAll(novelas)
        // Notify the adapter or update the UI as needed
    }
}