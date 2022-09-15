package com.hotdealnoti

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.hotdealnoti.data.local.App
import com.hotdealnoti.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {
    private val binding: MainActivityBinding by lazy {
        MainActivityBinding.inflate(layoutInflater)
    }


    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(ContentValues.TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            App.prefs.setValue("NOTIFICATION_TOKEN",token)
        })
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