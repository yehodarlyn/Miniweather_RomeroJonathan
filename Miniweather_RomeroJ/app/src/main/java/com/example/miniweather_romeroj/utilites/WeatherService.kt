package com.example.miniweather_romeroj.utilites

import android.content.Context
import com.example.miniweather_romeroj.R
import com.example.miniweather_romeroj.domain.Weather



class WeatherService(val context: Context) {

    val weatherStates = arrayOf(
        context.getString(R.string.snowy),
        context.getString(R.string.windy),
        context.getString(R.string.stormy),
        context.getString(R.string.rainy),
        context.getString(R.string.cloudy),
        context.getString(R.string.sunny))

    fun getCities():Array<String>{
        return arrayOf(
            "CDMX",
            "Londres",
            "Paris",
            "GDL",
            "Obregon")
    }

    private fun generateWeather(): Weather{
        val temp = (-15..50).random()

        var weatherIndex = -1
        when(temp){
            in -15..0 -> weatherIndex = 0
            in 1..18 -> weatherIndex = (1..4).random()
            in 19..25 -> weatherIndex = (4..5).random()
            else -> weatherIndex = 5
        }

        return Weather(temp, weatherStates[weatherIndex])
    }

    fun getWeather(city: String): Weather {
        return generateWeather()
    }
}