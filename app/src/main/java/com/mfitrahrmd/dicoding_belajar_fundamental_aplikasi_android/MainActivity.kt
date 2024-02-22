package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var _binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        with(_binding) {
            buttonNew.setOnClickListener(this@MainActivity)
            buttonOpen.setOnClickListener(this@MainActivity)
            buttonSave.setOnClickListener(this@MainActivity)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_new -> {
                newFile()
            }

            R.id.button_open -> {
                showList()
            }

            R.id.button_save -> {
                saveFile()
            }
        }
    }

    private fun saveFile() {
        with(_binding) {
            when {
                editTitle.text.toString().isEmpty() -> {
                    Toast.makeText(this@MainActivity, "Title is required", Toast.LENGTH_SHORT)
                        .show()
                }

                editFile.text.toString().isEmpty() -> {
                    Toast.makeText(this@MainActivity, "Content is required", Toast.LENGTH_SHORT)
                        .show()
                }

                else -> {
                    val title = editTitle.text.toString()
                    val content = editFile.text.toString()
                    val fileModel = FileModel(title, content)
                    FileHelper.writeToFile(fileModel, this@MainActivity)
                    Toast.makeText(
                        this@MainActivity,
                        "Saving ${fileModel.fileName} data",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }

    private fun showList() {
        val items = fileList()
        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle("Choose file")
        builder.setItems(items) { dialog, item ->
            loadData(items[item].toString())
        }
        val alert = builder.create()
        alert.show()
    }

    private fun loadData(title: String) {
        val fileModel: FileModel = FileHelper.readFromFile(title, this@MainActivity)
        with(_binding) {
            editTitle.setText(fileModel.fileName)
            editFile.setText(fileModel.data)
        }
        Toast.makeText(this@MainActivity, "Loading ${fileModel.fileName} data", Toast.LENGTH_SHORT)
            .show()
    }

    private fun newFile() {
        with(_binding) {
            editTitle.setText("")
            editFile.setText("")
            Toast.makeText(this@MainActivity, "Clearing File", Toast.LENGTH_SHORT).show()
        }
    }
}