package com.example.miniweather_romeroj

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.example.miniweather_romeroj.utilites.WeatherService
import java.time.LocalTime

class MainActivity : AppCompatActivity() {

    private lateinit var greeting: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = false

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val citySelected = intent.getStringExtra("city") ?: ""

        greeting = findViewById(R.id.greeting)

        val service = WeatherService(this)
        val weather = service.getWeather(citySelected)

        val tvCity: TextView = findViewById(R.id.tvCity)
        val tvTemperature: TextView = findViewById(R.id.ivTemperature)
        val tvWeather: TextView = findViewById(R.id.tvWeather)
        val ivWeather: ImageView = findViewById(R.id.ivWeather)

        tvCity.text = citySelected
        tvTemperature.text = "${weather.temperature}°"
        tvWeather.text = weather.weather

        ivWeather.setImageResource(
            when (weather.weather) {
                getString(R.string.snowy) -> R.drawable.ic_snowy
                getString(R.string.windy) -> R.drawable.ic_windy
                getString(R.string.stormy) -> R.drawable.ic_stormy
                getString(R.string.rainy) -> R.drawable.ic_rainy
                getString(R.string.cloudy) -> R.drawable.ic_cloudy
                getString(R.string.sunny) -> R.drawable.ic_sunny
                else -> R.drawable.logo_negativo
            }
        )
    }

    override fun onResume() {
        super.onResume()
        updateGreeting()
    }

    private fun updateGreeting() {
        val time = LocalTime.now().hour
        greeting.text = when (time) {
            in 5..11 -> getString(R.string.good_morning)
            in 12..19 -> getString(R.string.good_afternoon)
            else -> getString(R.string.good_evening)
        }
    }


}