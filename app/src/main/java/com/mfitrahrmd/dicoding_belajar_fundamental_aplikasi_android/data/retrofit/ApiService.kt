package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.data.retrofit

import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.data.response.PostReviewResponse
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.data.response.RestaurantResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("detail/{id}")
    fun getRestaurant(@Path("id") id: String): Call<RestaurantResponse>

    @FormUrlEncoded
    @Headers("Authorization: token 12345")
    @POST("review")
    fun postReview(
        @Field("id")
        id: String,
        @Field("name")
        name: String,
        @Field("review")
        review: String
    ): Call<PostReviewResponse>
}