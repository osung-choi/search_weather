package com.osung.idustest.domain.entity

data class WeatherEntity(
    val id: Int,
    val title: String,
    val todayWeather: WeatherInfo,
    val tomorrowWeather: WeatherInfo
)

data class WeatherInfo(
    val weatherStateName: String,
    val weatherStateAbbr: String,
    val theTemp: Double,
    val humidity: Int,
)