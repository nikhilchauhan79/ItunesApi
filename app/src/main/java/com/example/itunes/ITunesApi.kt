package com.example.itunes

import android.telecom.Call
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.io.File

interface ITunesApi {

    @GET("search?")
    fun getJsonFile(@Query("term")searchTerm: String):retrofit2.Call<ResponseBody>

    @POST("search?")
    fun getJsonFrQuery(@Query("term")searchTerm: String):retrofit2.Call<ResponseBody>


}

object MusicService{
    private val client = OkHttpClient.Builder().build()


    private val retrofit = Retrofit.Builder()
            .baseUrl("https://itunes.apple.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}
