package com.minaMikhail.networkCaching.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.minaMikhail.home.presentation.view.HomeActivity
import com.minaMikhail.networkCaching.databinding.ActivitySplashBinding
import com.minaMikhail.preferences.cachingManager.CachingManager
import com.minaMikhail.preferences.cachingManager.utils.CachingPreferenceKey.REMOTE_CONFIG_CACHING_KEY
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private var _binding: ActivitySplashBinding? = null
    private val binding get() = checkNotNull(_binding)

    @Inject
    lateinit var cachingManager: CachingManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()

        // To enable api caching for the app
        lifecycleScope.launch {
            cachingManager.set(REMOTE_CONFIG_CACHING_KEY, true)
        }

        decideNavigationLogic()
    }

    private fun initBinding() {
        _binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun decideNavigationLogic() {
        Handler(Looper.getMainLooper()).postDelayed({
            openHomeActivity()
        }, 2000)
    }

    private fun openHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        _binding = null

        super.onDestroy()
    }
}