package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var _binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding.btnToCategory.setOnClickListener {
            Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_categoryFragment)
        }

        _binding.btnToProfile.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_profileActivity)
        }
    }
}