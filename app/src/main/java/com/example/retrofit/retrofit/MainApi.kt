package com.example.retrofit.retrofit

import com.example.retrofit.authentication.AuthRequest
import com.example.retrofit.authentication.User
import com.example.retrofit.product.Products
import retrofit2.http.*

interface MainApi {


    @POST("auth/login")
    suspend fun auth(@Body authRequest: AuthRequest): User

    @GET("auth/product")
    suspend fun getAllProducts(): Products

    @Headers("Content-Type: application/json")
    @GET("auth/product/search")
    suspend fun getProductsByNameAuth(@Header("Authorization") token: String,
                                  @Query("q") name: String): Products

    @Headers("Content-Type: application/json")
    @GET("auth/product/search")
    suspend fun getProductsByName(@Header("Authorization") token: String,
                                      @Query("q") name: String): Products
}