package com.example.retrofit.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.retrofit.R
import com.example.retrofit.databinding.FragmentAuthenticationBinding
import com.example.retrofit.product.ProductsFragment
import com.example.retrofit.retrofit.MainApi
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthenticationFragment: Fragment() {

    private var _binding: FragmentAuthenticationBinding? = null
    private val binding: FragmentAuthenticationBinding
        get() = _binding ?: throw RuntimeException("FragmentProductsBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthenticationBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val mainApi = retrofit.create(MainApi::class.java)

        binding.login.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val user = mainApi.auth(
                    AuthRequest(
                        binding.etName.text.toString(),
                        binding.etPassword.text.toString()
                    )
                )
                binding.apply {
                    Picasso.get().load(user.image).into(ivAvatar)
                    firstName.text = user.firstName
                    lastName.text = user.lastName
                }
            }
        }

        binding.next.setOnClickListener {
            next()
        }

    }

    private fun next() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, ProductsFragment())
            .commit()
    }
}