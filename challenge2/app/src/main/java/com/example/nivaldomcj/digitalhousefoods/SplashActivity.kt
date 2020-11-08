package com.example.nivaldomcj.digitalhousefoods

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {

    // see: https://android.jlelse.eu/revisited-a-guide-on-splash-screen-in-android-in-2020-bbcd4bb1ce42
    private val activityScope: CoroutineScope = CoroutineScope(Dispatchers.Main)

    private fun startScope() {
        activityScope.launch {
            // 3 seconds is enough?
            delay(3000)
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finish()
        }
    }

    private fun cleanScope() {
        activityScope.cancel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startScope()
    }

    override fun onPause() {
        cleanScope()
        super.onPause()
    }

}