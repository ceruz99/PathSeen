package com.example.pathseen.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.example.pathseen.R
import com.example.pathseen.ui.main.MainActivity
import com.example.pathseen.ui.signin.SignInActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashActivity : AppCompatActivity() {
    private var auth: FirebaseAuth = Firebase.auth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        startTimer();
    }

    private fun startTimer() {
        object: CountDownTimer(3000, 1000){
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                if(auth.currentUser == null){
                    val intent = Intent(applicationContext, SignInActivity::class.java).apply{}
                    startActivity(intent);
                    finish()
                }
                else{
                    val intent = Intent(applicationContext, MainActivity::class.java).apply{}
                    startActivity(intent);
                    finish()
                }
            }

        }.start()
    }
}