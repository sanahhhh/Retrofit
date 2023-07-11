package com.example.retrofit.weather.data_model


data class WeatherDataModel(
    val location: LocationModel,
    val current: CurrentModel,
    val forecast: ForecastModel
)

data class LocationModel(
    val name: String,
    val localtime: String
)

data class CurrentModel(
    val last_updated: String,
    val temp_c: Float,
    val condition: ConditionModel
)

data class ConditionModel(
    val text: String,
    val icon: String
)

