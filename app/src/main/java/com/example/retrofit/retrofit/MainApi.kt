package com.example.retrofit.retrofit

import com.example.retrofit.authentication.AuthRequest
import com.example.retrofit.authentication.User
import com.example.retrofit.product.Products
import com.example.retrofit.weather.data_model.WeatherDataModel
import retrofit2.http.*

interface MainApi {


    @POST("auth/login")
    suspend fun auth(@Body authRequest: AuthRequest): User

    @GET("product")
    suspend fun getAllProducts(): Products

    @Headers("Content-Type: application/json")
    @GET("product/search")
    suspend fun getProductsByName(@Query("q") name: String): Products

    @GET("forecast.json")
    suspend fun getWeatherData(
        @Query("key") key: String,
        @Query("q") q: String,
        @Query("days") days: String,
        @Query("aqi") aqi:String,
        @Query("alerts") alerts:String
    ) : WeatherDataModel
}