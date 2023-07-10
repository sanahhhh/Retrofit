package com.example.retrofit.retrofit

import com.example.retrofit.authentication.AuthRequest
import com.example.retrofit.authentication.User
import com.example.retrofit.product.Product
import com.example.retrofit.product.Products
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MainApi {


    @POST("auth/login")
    suspend fun auth(@Body authRequest: AuthRequest) : User

    @GET("product")
    suspend fun getAllProducts() : Products
}