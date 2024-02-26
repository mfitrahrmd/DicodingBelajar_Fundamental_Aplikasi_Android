package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.ui.insert

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.R
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.database.Note
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.databinding.ActivityNoteAddUpdateBinding
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.helper.DateHelper

class NoteAddUpdateActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityNoteAddUpdateBinding
    private lateinit var _viewModel: NoteAddUpdateViewModel
    private var _isEdit = false
    private var _note: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNoteAddUpdateBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        _note = intent.getParcelableExtra(EXTRA_NOTE)
        if (_note != null) {
            _isEdit = true
        } else {
            _note = Note()
        }

        val actionBarTitle: String
        val btnTitle: String

        if (_isEdit) {
            actionBarTitle = "Change"
            btnTitle = "Update"
            if (_note != null) {
                _note.let { note ->
                    _binding.etTitle.setText(note?.title)
                    _binding.etDescription.setText(note?.description)
                }
            }
        } else {
            actionBarTitle = "Add"
            btnTitle = "Save"
        }

        supportActionBar?.title = actionBarTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        _binding.btnSubmit.text = btnTitle

        _binding.btnSubmit.setOnClickListener {
            val title = _binding.etTitle.text.toString().trim()
            val description = _binding.etDescription.text.toString().trim()

            when {
                title.isEmpty() -> {
                    _binding.etTitle.error = getString(R.string.empty)
                }

                description.isEmpty() -> {
                    _binding.etDescription.error = getString(R.string.empty)
                }

                else -> {
                    _note.let { note ->
                        note?.title = title
                        note?.description = description
                    }
                    if (_isEdit) {
                        _viewModel.update(_note!!)
                        showToast(getString(R.string.changed))
                    } else {
                        _note.let { note ->
                            note?.date = DateHelper.getCurrentDate()
                        }
                        _viewModel.insert(_note!!)
                        showToast(getString(R.string.added))
                    }
                    finish()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (_isEdit) {
            menuInflater.inflate(R.menu.menu_form, menu)
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.actionDelete -> showAlertDialog(ALERT_DIALOG_DELETE)
            android.R.id.home -> showAlertDialog(ALERT_DIALOG_CLOSE)
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        showAlertDialog(ALERT_DIALOG_CLOSE)
    }

    private fun showAlertDialog(type: Int) {
        val isDialogClose = type == ALERT_DIALOG_CLOSE
        val dialogTitle: String
        val dialogMessage: String
        if (isDialogClose) {
            dialogTitle = "Cancel"
            dialogMessage = "Cancel?"
        } else {
            dialogTitle = "Delete"
            dialogMessage = "Delete?"
        }
        val alertDialogBuilder = AlertDialog.Builder(this@NoteAddUpdateActivity)
        with(alertDialogBuilder) {
            setTitle(dialogTitle)
            setMessage(dialogMessage)
            setCancelable(false)
            setPositiveButton("Yes") { _, _ ->
                if (!isDialogClose) {
                    _viewModel.delete(_note!!)
                    showToast("Deleted")
                }

                finish()
            }
            setNegativeButton("No") { dialog, _ ->
                dialog.cancel()
            }
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun showToast(message: String) {
        Toast.makeText(this@NoteAddUpdateActivity, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val EXTRA_NOTE = "extra_note"
        const val ALERT_DIALOG_CLOSE = 10
        const val ALERT_DIALOG_DELETE = 20
    }
}