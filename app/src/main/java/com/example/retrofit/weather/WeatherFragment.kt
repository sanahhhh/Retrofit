package com.example.retrofit.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.retrofit.databinding.FragmentWeatherBinding
import com.example.retrofit.retrofit.MainApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class WeatherFragment: Fragment() {

    private var _binding: FragmentWeatherBinding? = null
    private val binding: FragmentWeatherBinding
        get() = _binding ?: throw RuntimeException("FragmentProductsBinding == null")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        binding.getForecast.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val api = retrofit.create(MainApi::class.java)
                val model = api.getWeatherData(
                    "743512008b3742d586e90447231107",
                    "London",
                    "3",
                    "no",
                    "no"
                )

                binding.city.text = model.location.name
                binding.date.text = model.location.localtime
                binding.temperature.text = model.current.temp_c.toString()
            }
        }
    }
}