package com.route.todp3.ui.splash
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

import com.route.todp3.ui.home.MainActivity
import com.route.todp3.R
import com.route.todp3.dataBase.MyDataBase


class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        MyDataBase.getInstance(applicationContext)
        startActivity()
    }

    private fun startActivity() {
        Handler(Looper.getMainLooper()).postDelayed( object:Runnable {
            override fun run() {
               val  intent=Intent(this@Splash, MainActivity::class.java)
                startActivity(intent)
            }
                                                                     } ,2000)
    }
}