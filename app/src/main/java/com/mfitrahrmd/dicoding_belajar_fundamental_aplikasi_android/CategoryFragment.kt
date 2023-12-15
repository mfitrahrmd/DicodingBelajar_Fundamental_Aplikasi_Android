package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.databinding.FragmentCategoryBinding

class CategoryFragment : Fragment() {
    private lateinit var _binding: FragmentCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding.btnToCategoryLifestyle.setOnClickListener {
            it.findNavController().navigate(
                CategoryFragmentDirections.actionCategoryFragmentToDetailCategoryFragment(
                    "Lifestyle", 7
                )
            )
        }
    }
}