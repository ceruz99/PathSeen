package com.example.pathseen.ui.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pathseen.R
import com.example.pathseen.databinding.ActivitySignUpBinding
import com.example.pathseen.ui.signin.SignInActivity

class SignUpActivity : AppCompatActivity() {

    private lateinit var signUpBinding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signUpBinding=ActivitySignUpBinding.inflate(layoutInflater)
        val view=signUpBinding.root
        setContentView(view)

        signUpBinding.registerButton.setOnClickListener{
            startActivity(Intent(this, SignInActivity::class.java))
        }

    }
}