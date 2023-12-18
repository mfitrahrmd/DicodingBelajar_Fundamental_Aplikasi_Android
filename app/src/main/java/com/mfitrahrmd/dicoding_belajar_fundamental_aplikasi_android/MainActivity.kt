package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android

import android.opengl.Visibility
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.databinding.ActivityMainBinding
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.databinding.ItemTodoBinding
import cz.msebera.android.httpclient.Header
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.lang.Exception
import java.util.ArrayList
import java.util.concurrent.Callable
import java.util.concurrent.Executors

data class Todo(val title: String)

class TodosAdapter(val todos: List<Todo>) : RecyclerView.Adapter<TodosAdapter.VH>() {
    class VH(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding =
            ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return VH(binding)
    }

    override fun getItemCount(): Int = todos.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.tvTitle.text = todos[position].title
    }
}

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        _binding.btnStart.setOnClickListener {
            getTodos()
        }
        val layout = LinearLayoutManager(this@MainActivity)
        _binding.listTodos.layoutManager = layout
        _binding.listTodos.addItemDecoration(
            DividerItemDecoration(
                this@MainActivity,
                layout.orientation
            )
        )
    }

    fun getTodos() {
        _binding.progressBar.visibility = View.VISIBLE
        val client = AsyncHttpClient()

        client.get(
            "https://jsonplaceholder.typicode.com/todos",
            object : AsyncHttpResponseHandler() {
                override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>,
                    responseBody: ByteArray
                ) {
                    val listTodos = ArrayList<Todo>()
                    try {
                        val responseObject = JSONArray(String(responseBody))

                        for (i in 0 until responseObject.length()) {
                            with(responseObject.getJSONObject(i)) {
                                listTodos.add(Todo(this.getString("title")))
                            }
                        }

                        _binding.listTodos.adapter = TodosAdapter(listTodos)

                        _binding.progressBar.visibility = View.INVISIBLE
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseBody: ByteArray?,
                    error: Throwable?
                ) {
                    Toast.makeText(
                        this@MainActivity,
                        statusCode.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                    _binding.progressBar.visibility = View.INVISIBLE
                }
            })
    }
}