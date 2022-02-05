package com.buyrak.datamanagmentsystem.LoginAndRegister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.buyrak.datamanagmentsystem.MainOperations.MainActivity
import com.buyrak.datamanagmentsystem.R
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onResume() {

        object : CountDownTimer(3000,1000){

            override fun onFinish() {

                if (FirebaseAuth.getInstance().currentUser != null){
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                }else{
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                }
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }

            override fun onTick(millisUntilFinished: Long) {}

        }.start()
        super.onResume()
    }
}