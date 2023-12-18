package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.data.response.CustomerReviewsItem
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.databinding.ItemReviewBinding

class ReviewAdapter : ListAdapter<CustomerReviewsItem, ReviewAdapter.VH>(DIFF_CALLBACK) {
    class VH(private val _binding: ItemReviewBinding) : RecyclerView.ViewHolder(_binding.root) {
        fun bind(review: CustomerReviewsItem) {
            _binding.tvItem.text = "${review.review}\n- ${review.name}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CustomerReviewsItem>() {
            override fun areItemsTheSame(
                oldItem: CustomerReviewsItem,
                newItem: CustomerReviewsItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: CustomerReviewsItem,
                newItem: CustomerReviewsItem
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}