package com.example.pathseen.ui.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.pathseen.R
import com.example.pathseen.databinding.ActivitySignInBinding
import com.example.pathseen.ui.Books.BooksFragment
import com.example.pathseen.ui.main.MainActivity
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

        signInViewModel.isSuccessSignIn.observe(this){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        signInViewModel.errorMsg.observe(this){errorMsg->
            Toast.makeText(applicationContext, errorMsg, Toast.LENGTH_LONG).show()
        }

        signInBinding.registerTextview.setOnClickListener{
            startActivity(Intent(this,SignUpActivity::class.java))
        }

        signInBinding.loginButton.setOnClickListener{
            val email= signInBinding.emailEditText.text.toString()
            val password= signInBinding.passwordEditText.text.toString()
            signInViewModel.validateFields(email,password)
        }
    }
}