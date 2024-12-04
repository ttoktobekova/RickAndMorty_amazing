package com.example.paging.data

import com.example.paging.data.models.BaseResponse
import com.example.paging.data.models.Character
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int,
    ): BaseResponse

    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") id :Int
    ):Character
}