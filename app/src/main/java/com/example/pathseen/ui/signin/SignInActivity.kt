package com.example.pathseen.ui.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.pathseen.R
import com.example.pathseen.databinding.ActivitySignInBinding
import com.example.pathseen.ui.Books.BooksFragment
import com.example.pathseen.ui.signup.SignUpActivity

class SignInActivity : AppCompatActivity() {

    private lateinit var signInViewModel: SignInViewModel
    private lateinit var signInBinding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signInBinding= ActivitySignInBinding.inflate(layoutInflater)
        signInViewModel=ViewModelProvider(this)[SignInViewModel::class.java]
        val view= signInBinding.root
        setContentView(view)

        signInBinding.registerTextview.setOnClickListener{
            startActivity(Intent(this,SignUpActivity::class.java))
        }

        signInBinding.loginButton.setOnClickListener{
            val email= signInBinding.emailEditText.text.toString()
            val password= signInBinding.passwordEditText.text.toString()
            signInViewModel.validateFields(email,password)
            //startActivity(Intent(this,BooksFragment::class.java))
        }
    }
}