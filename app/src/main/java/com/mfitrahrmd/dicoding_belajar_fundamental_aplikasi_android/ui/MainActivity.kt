package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.data.response.CustomerReviewsItem
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.data.response.Restaurant
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.data.response.RestaurantResponse
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.data.retrofit.ApiConfig
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding

    companion object {
        private const val RESTAURANT_ID = "uewq1zg2zlskfw1e867"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        val layout = LinearLayoutManager(this@MainActivity)
        val itemDecoration = DividerItemDecoration(this@MainActivity, layout.orientation)
        _binding.rvReview.layoutManager = layout
        _binding.rvReview.addItemDecoration(itemDecoration)

        findRestaurant()
    }

    private fun findRestaurant() {
        showLoading(true)
        val client = ApiConfig.getApiService().getRestaurant(RESTAURANT_ID)
        client.enqueue(object : retrofit2.Callback<RestaurantResponse> {
            override fun onResponse(
                call: Call<RestaurantResponse>, response: Response<RestaurantResponse>
            ) {
                showLoading(false)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        setRestaurantData(responseBody.restaurant)
                        setReviewData(responseBody.restaurant.customerReviews)
                    }
                } else {
                    Log.e("FAILED", response.message())
                }
            }

            override fun onFailure(call: Call<RestaurantResponse>, t: Throwable) {
                showLoading(false)
                Log.e("FAILED", t.message.toString())
            }
        })
    }

    private fun setRestaurantData(restaurant: Restaurant) {
        _binding.tvTitle.text = restaurant.name
        _binding.tvDescription.text = restaurant.description
        Glide.with(this@MainActivity)
            .load("https://restaurant-api.dicoding.dev/images/large/${restaurant.pictureId}")
            .into(_binding.ivPicture)
    }

    private fun setReviewData(consumerReview: List<CustomerReviewsItem>) {
        val adapter = ReviewAdapter()
        adapter.submitList(consumerReview)
        _binding.rvReview.adapter = adapter
        _binding.edReview.setText("")
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            _binding.progressBar.visibility = View.VISIBLE
        } else {
            _binding.progressBar.visibility = View.GONE
        }
    }
}