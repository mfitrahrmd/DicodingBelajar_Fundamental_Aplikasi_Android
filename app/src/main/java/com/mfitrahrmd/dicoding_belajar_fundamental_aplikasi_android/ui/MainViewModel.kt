package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.data.response.CustomerReviewsItem
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.data.response.PostReviewResponse
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.data.response.Restaurant
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.data.response.RestaurantResponse
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val _restaurant: MutableLiveData<Restaurant> = MutableLiveData()
    val restaurant: LiveData<Restaurant> = _restaurant

    private val _listReview: MutableLiveData<List<CustomerReviewsItem>> = MutableLiveData()
    val listReview: LiveData<List<CustomerReviewsItem>> = _listReview

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "MainViewModel"
        private const val RESTAURANT_ID = "uewq1zg2zlskfw1e867"
    }

    init {
        findRestaurant()
    }

    fun findRestaurant() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getRestaurant(RESTAURANT_ID)
        client.enqueue(object : retrofit2.Callback<RestaurantResponse> {
            override fun onResponse(
                call: Call<RestaurantResponse>, response: Response<RestaurantResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _restaurant.value = responseBody.restaurant
                        _listReview.value = responseBody.restaurant.customerReviews
                    }
                } else {
                    Log.e("FAILED", response.message())
                }
            }

            override fun onFailure(call: Call<RestaurantResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("FAILED", t.message.toString())
            }
        })
    }

    fun postReview(review: String) {
        _isLoading.value = true
        val client =
            ApiConfig.getApiService().postReview(RESTAURANT_ID, "Dicoding", review)
        client.enqueue(object : retrofit2.Callback<PostReviewResponse> {
            override fun onResponse(
                call: Call<PostReviewResponse>,
                response: Response<PostReviewResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _listReview.value = responseBody.customerReviews
                    }
                } else {
                    Log.e("FAILED", response.message())
                }
            }

            override fun onFailure(call: Call<PostReviewResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("FAILED", t.message.toString())
            }
        })
    }
}