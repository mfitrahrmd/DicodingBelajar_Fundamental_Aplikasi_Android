package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.data.response.CustomerReviewsItem
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.data.response.PostReviewResponse
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.data.response.Restaurant
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.data.response.RestaurantResponse
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.data.retrofit.ApiConfig
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private val _vm: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        _binding.btnSend.setOnClickListener {
            _vm.postReview(_binding.edReview.text.toString())
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
        val layout = LinearLayoutManager(this@MainActivity)
        val itemDecoration = DividerItemDecoration(this@MainActivity, layout.orientation)
        _binding.rvReview.layoutManager = layout
        _binding.rvReview.addItemDecoration(itemDecoration)

        _vm.restaurant.observe(this@MainActivity) {
            setRestaurantData(it)
        }

        _vm.listReview.observe(this@MainActivity) {
            setReviewData(it)
        }

        _vm.isLoading.observe(this@MainActivity) {
            showLoading(it)
        }

        _vm.snackbarText.observe(this@MainActivity) {
            it.getContentIfNotHandled()?.let {
                Snackbar.make(window.decorView.rootView, it, Snackbar.LENGTH_SHORT).show()
            }
        }
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