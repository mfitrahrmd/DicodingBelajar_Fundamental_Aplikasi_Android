package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.databinding.FragmentCategoryBinding

class CategoryFragment : Fragment() {
    private lateinit var _binding: FragmentCategoryBinding

    companion object {
        val EXTRA_NAME = "extra_name"
        val EXTRA_STOCK = "extra_stock"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding.btnToCategoryLifestyle.setOnClickListener {
            with(Bundle()) {
                this.putString(EXTRA_NAME, "Lifestyle")
                this.putInt(EXTRA_STOCK, 7)
                it.findNavController().navigate(R.id.action_categoryFragment_to_detailCategoryFragment, this)
            }
        }
    }
}