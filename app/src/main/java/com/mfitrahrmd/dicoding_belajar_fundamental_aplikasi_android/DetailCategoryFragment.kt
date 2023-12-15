package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.databinding.FragmentDetailCategoryBinding

class DetailCategoryFragment : Fragment() {
    private lateinit var _binding: FragmentDetailCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailCategoryBinding.inflate(inflater, container, false)

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = DetailCategoryFragmentArgs.fromBundle(arguments as Bundle).name
        val stock = DetailCategoryFragmentArgs.fromBundle(arguments as Bundle).stock

        with(_binding) {
            this.tvCategoryName.text = name
            this.tvCategoryDescription.text = "Stock : $stock"
            this.btnToHome.setOnClickListener {
                Navigation.createNavigateOnClickListener(R.id.action_detailCategoryFragment_to_homeFragment)
            }
        }
    }
}