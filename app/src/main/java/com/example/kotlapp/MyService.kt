package com.example.kotlapp

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

import java.util.*

interface MyService {

    @GET("card/{id}")
    fun getCard(@Path("id")name: String): Call<Card>

    @GET("card")
    fun getCards(): Call<List<Card>>

    companion object Factory {
        fun create(): MyService {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://10.115.20.16:8081/")
                .addConverterFactory(GsonConverterFactory.create())

                .build()

            return retrofit.create(MyService::class.java)

        }
    }
}