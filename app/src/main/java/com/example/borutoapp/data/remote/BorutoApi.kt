package com.example.borutoapp.data.remote

import com.example.borutoapp.domain.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BorutoApi {

    @GET(value = "/boruto/heroes")
    suspend fun getAllHeroes(
        @Query(value = "page") page: Int = 1): ApiResponse

    @GET(value = "/boruto/heroes/search")
    suspend fun searchheroes(
        @Query(value = "name") name: String): ApiResponse

}