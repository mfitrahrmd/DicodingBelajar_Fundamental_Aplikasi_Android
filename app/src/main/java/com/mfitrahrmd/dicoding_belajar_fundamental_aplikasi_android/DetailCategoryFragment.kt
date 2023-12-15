package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.databinding.FragmentDetailCategoryBinding

class DetailCategoryFragment : Fragment() {
    private lateinit var _binding: FragmentDetailCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailCategoryBinding.inflate(inflater, container, false)

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = arguments?.getString(CategoryFragment.EXTRA_NAME)
        val stock = arguments?.getInt(CategoryFragment.EXTRA_STOCK)

        with(_binding) {
            this.tvCategoryName.text = name
            this.tvCategoryDescription.text = "Stock : $stock"
        }
    }
}