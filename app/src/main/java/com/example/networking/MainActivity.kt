package com.example.networking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.networking.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var view:ConstraintLayout
    private lateinit var toDoAdapter: ToDoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityMainBinding.inflate(layoutInflater)
        view=binding.root
        super.onCreate(savedInstanceState)
        setContentView(view)
        toDoAdapter= ToDoAdapter()
        binding.todo.adapter=toDoAdapter
        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible=true
            val response=try {
                RetrofitInstance.api.getTodos()
            }
            catch (e:IOException){
                Log.i("Exception Occurred","IOException check internet connectivity")
                binding.progressBar.isVisible=false
                return@launchWhenCreated
            }
            catch (e:HttpException){
                Log.i("Exception Occured","HttpException")
                binding.progressBar.isVisible=false
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body()!=null){
                toDoAdapter.todos=response.body()!!
            }
            else{
                Log.i("Failed","Response not successfull")
            }
            binding.progressBar.isVisible=false
        }

    }
}