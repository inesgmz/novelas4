package com.example.novelas4

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.osmdroid.api.IMapController
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class MapsActivity : AppCompatActivity() {

    private lateinit var mapView: MapView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        // Initialize MapView
        mapView = findViewById<MapView>(R.id.mapView)
        mapView.setTileSource(org.osmdroid.tileprovider.tilesource.TileSourceFactory.MAPNIK)
        mapView.setMultiTouchControls(true)
        val mapController: IMapController = mapView.controller
        mapController.setZoom(2.0) // Set zoom level to show the world map

        // List of novels with their titles and coordinates
        val novels = listOf(
            Triple("Orgullo y prejuicio", 51.217777, -1.612222),
            Triple("Cien años de soledad", 10.451267, -73.253221),
            Triple("Don Quijote de la Mancha", 39.8675, -4.027222),
            Triple("1984", 51.509865, -0.118092),
            Triple("Crimen y Castigo", 59.93428, 30.335099),
            Triple("El Gran Gatsby", 40.712776, -74.005974),
            Triple("Matar a un ruiseñor", 32.366805, -86.299969),
            Triple("El Hobbit", -37.787001, 175.279253),
            Triple("Farenheit 451", 34.052235, -118.243683),
            Triple("La Odisea", 38.246639, 21.734573),
            Triple("El Principito", 43.710173, 7.261953),
            Triple("Drácula", 46.770439, 23.591423),
            Triple("Los Miserables", 48.856613, 2.352222),
            Triple("La sombra del viento", 41.385063, 2.173404)
        )

        // Add markers for each novel
        for (novel in novels) {
            val marker = Marker(mapView)
            marker.position = GeoPoint(novel.second, novel.third)  // Coordinates of the marker
            marker.title = novel.first  // Title of the marker
            mapView.overlays.add(marker)
        }
        mapView.invalidate()  // Refresh the map to show the markers
    }

    override fun onResume() {
        super.onResume()
        org.osmdroid.config.Configuration.getInstance().load(this, android.preference.PreferenceManager.getDefaultSharedPreferences(this))
    }

    override fun onPause() {
        super.onPause()
        org.osmdroid.config.Configuration.getInstance().save(this, android.preference.PreferenceManager.getDefaultSharedPreferences(this))
    }
}