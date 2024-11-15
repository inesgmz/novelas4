package com.example.novelas4

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import android.widget.Switch

class AjustesActivity : AppCompatActivity() {

    private lateinit var switchLightMode: Switch
    private lateinit var switchDarkMode: Switch
    private lateinit var recyclerView: RecyclerView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajustes)

        switchLightMode = findViewById(R.id.switchLightMode)
        switchDarkMode = findViewById(R.id.switchDarkMode)
        recyclerView = findViewById(R.id.recyclerView)

        // Set the initial switch states based on the current theme
        val currentNightMode = AppCompatDelegate.getDefaultNightMode()
        val isDarkMode = currentNightMode == AppCompatDelegate.MODE_NIGHT_YES
        switchLightMode.isChecked = !isDarkMode
        switchDarkMode.isChecked = isDarkMode

        // Listener for light mode switch
        switchLightMode.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                switchDarkMode.isChecked = false
                recreate() // Recreate the activity to apply the new theme
            }
        }

        // Listener for dark mode switch
        switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                switchLightMode.isChecked = false
                recreate() // Recreate the activity to apply the new theme
            }
        }

        // Button to return to the main screen
        findViewById<Button>(R.id.btnVolverInicio).setOnClickListener {
            finish()
        }
    }

    // Update the list of novelas in the RecyclerView
    fun updateNovelas(novelas: List<Novela>) {
        (recyclerView.adapter as NovelaAdaptador).apply {
            this.updateNovelas(novelas)
        }
    }
}