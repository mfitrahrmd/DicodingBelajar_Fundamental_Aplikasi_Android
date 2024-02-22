package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var _binding: ActivityMainBinding
    private lateinit var _viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        _viewModel = MainViewModel(CuboidModel())
        setContentView(_binding.root)
        with(_binding) {
            btnSave.setOnClickListener(this@MainActivity)
            btnCalculateVolume.setOnClickListener(this@MainActivity)
            btnCalculateCircumference.setOnClickListener(this@MainActivity)
            btnCalculateSurfaceArea.setOnClickListener(this@MainActivity)
        }
    }

    override fun onClick(v: View) {
        with(_binding) {
            val width = etWidth.text.toString().trim()
            val length = etLength.text.toString().trim()
            val height = etHeight.text.toString().trim()
            when {
                TextUtils.isEmpty(width) -> {
                    etWidth.error = "Field cannot be empty"
                }

                TextUtils.isEmpty(length) -> {
                    etLength.error = "Field cannot be empty"
                }

                TextUtils.isEmpty(height) -> {
                    etHeight.error = "Field cannot be empty"
                }

                else -> {
                    val valWidth = width.toDouble()
                    val valLength = length.toDouble()
                    val valHeight = height.toDouble()
                    when (v.id) {
                        R.id.btnSave -> {
                            _viewModel.save(valWidth, valLength, valHeight)
                            visible()
                        }

                        R.id.btnCalculateVolume -> {
                            tvResult.text = _viewModel.getVolume().toString()
                            gone()
                        }

                        R.id.btnCalculateCircumference -> {
                            tvResult.text = _viewModel.getCircumference().toString()
                            gone()
                        }

                        R.id.btnCalculateSurfaceArea -> {
                            tvResult.text = _viewModel.getSurfaceArea().toString()
                            gone()
                        }
                    }
                }
            }
        }
    }

    private fun visible() {
        with(_binding) {
            btnCalculateVolume.visibility = View.VISIBLE
            btnCalculateCircumference.visibility = View.VISIBLE
            btnCalculateSurfaceArea.visibility = View.VISIBLE
            btnSave.visibility = View.GONE
        }
    }

    private fun gone() {
        with(_binding) {
            btnCalculateVolume.visibility = View.GONE
            btnCalculateCircumference.visibility = View.GONE
            btnCalculateSurfaceArea.visibility = View.GONE
            btnSave.visibility = View.VISIBLE
        }
    }
}