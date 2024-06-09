package com.minaMikhail.home.presentation.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.minaMikhail.base.network.Resource
import com.minaMikhail.home.databinding.ActivityHomeBinding
import com.minaMikhail.home.presentation.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private var _binding: ActivityHomeBinding? = null
    private val binding get() = checkNotNull(_binding)

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()

        setUpViews()

        setUpListeners()

        setUpObservers()
    }

    private fun initBinding() {
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setUpViews() {
        binding.apply {

        }
    }

    private fun setUpListeners() {
        binding.apply {

        }
    }

    @SuppressLint("SetTextI18n")
    private fun setUpObservers() {
        lifecycleScope.launch {
            viewModel.getNews()
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { result ->
                    when (result) {
                        is Resource.Default -> {
                            binding.tvState.text = "Default"
                        }

                        is Resource.Loading -> {
                            binding.tvState.text = "Loading..."
                        }

                        is Resource.Success -> {
                            binding.tvState.text = "Success ${result.value?.size}"

                        }

                        is Resource.Failure -> {
                            binding.tvState.text = "Failure ${result.exception.message}"
                        }

                        else -> {
                            binding.tvState.text = "Other"
                        }
                    }
                }
        }
    }

    override fun onDestroy() {
        _binding = null

        super.onDestroy()
    }
}