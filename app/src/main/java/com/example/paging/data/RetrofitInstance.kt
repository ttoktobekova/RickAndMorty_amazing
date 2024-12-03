package com.example.paging.data

import com.example.paging.utils.KEY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(KEY.BASE_URL)
            .build()

    val api = retrofit.create(ApiService::class.java)
}