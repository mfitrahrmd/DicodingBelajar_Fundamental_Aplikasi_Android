package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var _binding: ActivityMainBinding
    private lateinit var _userPreference: UserPreference
    private var _isPreferenceEmpty = false
    private lateinit var _userModel: UserModel

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.data != null && it.resultCode == FormUserPreferenceActivity.RESULT_CODE) {
            _userModel =
                it.data?.getParcelableExtra<UserModel>(
                    FormUserPreferenceActivity.EXTRA_RESULT,
                    UserModel::class.java
                )!!
            populateView(_userModel)
            checkForm(_userModel)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        supportActionBar?.title = "My User Preference"
        _userPreference = UserPreference(this@MainActivity)
        showExistingPreference()
        with(_binding) {
            btnSave.setOnClickListener(this@MainActivity)
        }
    }

    private fun showExistingPreference() {
        _userModel = _userPreference.getUser()
        populateView(_userModel)
        checkForm(_userModel)
    }

    private fun checkForm(userModel: UserModel) {
        when {
            userModel.name.toString().isNotEmpty() -> {
                _binding.btnSave.text = getString(R.string.change)
                _isPreferenceEmpty = false
            }

            else -> {
                _binding.btnSave.text = getString(R.string.save)
                _isPreferenceEmpty = true
            }
        }
    }

    private fun populateView(userModel: UserModel) {
        with(_binding) {
            tvName.text = if (userModel.name.toString().isEmpty()) "Tidak Ada" else userModel.name
            tvAge.text =
                if (userModel.age.toString().isEmpty()) "Tidak Ada" else userModel.age.toString()
            tvIsLoveMu.text = if (userModel.isLoveMU) "Ya" else "Tidak"
            tvEmail.text =
                if (userModel.email.toString().isEmpty()) "Tidak Ada" else userModel.email
            tvPhone.text = if (userModel.phoneNumber.toString()
                    .isEmpty()
            ) "Tidak Ada" else userModel.phoneNumber
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_save) {
            val intent = Intent(this@MainActivity, FormUserPreferenceActivity::class.java)
            when {
                _isPreferenceEmpty -> {
                    intent.putExtra(
                        FormUserPreferenceActivity.EXTRA_TYPE_FORM,
                        FormUserPreferenceActivity.TYPE_ADD
                    )
                    intent.putExtra("USER", _userModel)
                }

                else -> {
                    intent.putExtra(
                        FormUserPreferenceActivity.EXTRA_TYPE_FORM,
                        FormUserPreferenceActivity.TYPE_EDIT
                    )
                    intent.putExtra("USER", _userModel)
                }
            }
            resultLauncher.launch(intent)
        }
    }
}