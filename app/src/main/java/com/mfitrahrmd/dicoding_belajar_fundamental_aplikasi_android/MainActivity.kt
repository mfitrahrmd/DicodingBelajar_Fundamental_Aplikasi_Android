package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.annotations.SerializedName
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

data class User(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("email") val email: String,
    @field:SerializedName("first_name") val firstName: String,
    @field:SerializedName("last_name") val lastName: String,
    @field:SerializedName("avatar") val avatar: String
)

data class ResponseUser(
    @field:SerializedName("page") val page: Int,
    @field:SerializedName("per_page") val perPage: Int,
    @field:SerializedName("total") val total: Int,
    @field:SerializedName("total_pages") val totalPages: Int,
    @field:SerializedName("data") val data: List<User>
)

interface ApiService {
    @GET("users")
    fun getListUsers(@Query("page") page: Int): Call<ResponseUser>
}

//class TodosAdapter(val todos: List<Todo>) : RecyclerView.Adapter<TodosAdapter.VH>() {
//    class VH(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
//        val binding =
//            ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//
//        return VH(binding)
//    }
//
//    override fun getItemCount(): Int = todos.size
//
//    override fun onBindViewHolder(holder: VH, position: Int) {
//        holder.binding.tvTitle.text = todos[position].title
//    }
//}

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        val chucker =
            OkHttpClient.Builder().addInterceptor(ChuckerInterceptor(this@MainActivity)).build()

        val api = Retrofit.Builder().baseUrl("https://reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create()).client(chucker).build()
            .create(ApiService::class.java)

        _binding.btnStart.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val client = api.getListUsers(1)

                val response = client.execute()

                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        _binding.tvText.text = response.body().toString()
                    }
                }
            }
        }
        val layout = LinearLayoutManager(this@MainActivity)
        _binding.listTodos.layoutManager = layout
        _binding.listTodos.addItemDecoration(
            DividerItemDecoration(
                this@MainActivity, layout.orientation
            )
        )
    }
}