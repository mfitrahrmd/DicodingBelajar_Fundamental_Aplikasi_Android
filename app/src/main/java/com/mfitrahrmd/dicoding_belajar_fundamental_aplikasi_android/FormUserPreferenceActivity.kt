package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.databinding.ActivityFormUserPreferenceBinding

class FormUserPreferenceActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val EXTRA_TYPE_FORM = "extra_type_form"
        const val EXTRA_RESULT = "extra_result"
        const val RESULT_CODE = 101
        const val TYPE_ADD = 1
        const val TYPE_EDIT = 2
        private const val FIELD_REQUIRED = "Field required"
        private const val FIELD_DIGIT_ONLY = "Field must be type of numeric"
        private const val FIELD_IS_NOT_VALID = "Field must be valid email"
    }

    private lateinit var _userModel: UserModel
    private lateinit var _binding: ActivityFormUserPreferenceBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFormUserPreferenceBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        with(_binding) {
            btnSave.setOnClickListener(this@FormUserPreferenceActivity)
        }
        _userModel = intent.getParcelableExtra<UserModel>("USER", UserModel::class.java)!!
        val formType = intent.getIntExtra(EXTRA_TYPE_FORM, 0)
        var actionBarTitle = ""
        var btnTitle = ""
        when (formType) {
            TYPE_ADD -> {
                actionBarTitle = "Add New"
                btnTitle = "Save"
            }

            TYPE_EDIT -> {
                actionBarTitle = "Edit"
                btnTitle = "Update"
                showPreferenceInForm()
            }
        }
        supportActionBar?.title = actionBarTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        _binding.btnSave.text = btnTitle
    }

    private fun showPreferenceInForm() {
        with(_binding) {
            edtName.setText(_userModel.name)
            edtEmail.setText(_userModel.email)
            edtAge.setText(_userModel.age.toString())
            edtPhone.setText(_userModel.phoneNumber)
            if (_userModel.isLoveMU) {
                rbYes.isChecked = true
            } else {
                rbNo.isChecked = true
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_save) {
            with(_binding) {
                val name = edtName.text.toString().trim()
                val email = edtEmail.text.toString().trim()
                val age = edtAge.text.toString().trim()
                val phoneNo = edtPhone.text.toString().trim()
                val isLoveMU = rgLoveMu.checkedRadioButtonId == R.id.rb_yes
                if (name.isEmpty()) {
                    edtName.error = FIELD_REQUIRED
                    return
                }
                if (email.isEmpty()) {
                    edtEmail.error = FIELD_REQUIRED
                    return
                }
                if (!isValidEmail(email)) {
                    edtEmail.error = FIELD_IS_NOT_VALID
                    return
                }
                if (age.isEmpty()) {
                    edtAge.error = FIELD_REQUIRED
                    return
                }
                if (phoneNo.isEmpty()) {
                    edtPhone.error = FIELD_REQUIRED
                    return
                }
                if (!TextUtils.isDigitsOnly(phoneNo)) {
                    edtPhone.error = FIELD_DIGIT_ONLY
                    return
                }
                saveUser(name, email, age, phoneNo, isLoveMU)
                setResult(RESULT_CODE, Intent().apply {
                    putExtra(EXTRA_RESULT, _userModel)
                })
                finish()
            }
        }
    }

    private fun saveUser(
        name: String,
        email: String,
        age: String,
        phoneNo: String,
        isLoveMU: Boolean
    ) {
        val userPreference = UserPreference(this)
        _userModel.name = name
        _userModel.email = email
        _userModel.age = Integer.parseInt(age)
        _userModel.phoneNumber = phoneNo
        _userModel.isLoveMU = isLoveMU
        userPreference.setUser(_userModel)
        Toast.makeText(this, "Data tersimpan", Toast.LENGTH_SHORT).show()
    }

    private fun isValidEmail(email: CharSequence): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}