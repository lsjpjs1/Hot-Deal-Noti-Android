package com.hotdealnoti

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.hotdealnoti.data.local.App
import com.hotdealnoti.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {
    private val binding: MainActivityBinding by lazy {
        MainActivityBinding.inflate(layoutInflater)
    }


    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {

        App.prefs.removeValue("AUTH_TOKEN") //삭제예정

        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupJetpackNavigation()
    }


    private fun setupJetpackNavigation() {
        val host =
            supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment

        navController = host.navController
        binding.bottomNavigationView.setupWithNavController(navController)
    }
}