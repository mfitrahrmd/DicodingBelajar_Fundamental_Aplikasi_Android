package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.data.retrofit

import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.data.response.RestaurantResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("detail/{id}")
    fun getRestaurant(@Path("id") id: String): Call<RestaurantResponse>
}