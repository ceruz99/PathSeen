package com.example.pathseen.ui.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.pathseen.R
import com.example.pathseen.databinding.ActivitySignUpBinding
import com.example.pathseen.ui.signin.SignInActivity


class SignUpActivity : AppCompatActivity() {
    private lateinit var signUpBinding: ActivitySignUpBinding
    private lateinit var signUpViewModel: SignUpViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signUpBinding=ActivitySignUpBinding.inflate(layoutInflater)
        signUpViewModel= ViewModelProvider(this)[SignUpViewModel::class.java]
        val view=signUpBinding.root
        setContentView(view)

        signUpViewModel.isSuccessSignUp.observe(this){
            //val intent = Intent(this,SignInActivity::class.java)
            //startActivity(intent)
            onBackPressedDispatcher.onBackPressed()
        }

        signUpViewModel.errorMsg.observe(this){errorMsg->
            Toast.makeText(applicationContext, errorMsg, Toast.LENGTH_LONG).show()
        }

        signUpBinding.registerButton.setOnClickListener{
            val name= signUpBinding.nameEditText.text.toString()
            val email=signUpBinding.emailEditText.text.toString()
            val password=signUpBinding.passwordEditText.text.toString()
            val repassword=signUpBinding.reppasswordEditText.text.toString()
            var genres=""
            if(signUpBinding.crimeCheckbox.isChecked) genres += " Crime"
            if(signUpBinding.dramaCheckbox.isChecked) genres += " Drama"
            if(signUpBinding.thrillerCheckbox.isChecked) genres += " Thriller"
            if(signUpBinding.otherCheckbox.isChecked) genres += " Action"
            if(signUpBinding.adventureCheckbox.isChecked) genres += " Adventure"
            if(signUpBinding.horrorCheckbox.isChecked) genres += " Horror"
            if(signUpBinding.scienceFictionCheckbox.isChecked) genres += " Science Fiction"
            if(signUpBinding.shooterCheckbox.isChecked) genres += " Shooter games"
            if(signUpBinding.roleCheckbox.isChecked) genres += " Role games"
            if(signUpBinding.platformCheckbox.isChecked) genres += " Platform games"
            if(signUpBinding.simulationCheckbox.isChecked) genres += " Simulation games"
            if(signUpBinding.sportsCheckbox.isChecked) genres += " Sports"
            if(signUpBinding.mysteryCheckbox.isChecked) genres += " Mystery"
            if(signUpBinding.historicalCheckbox.isChecked) genres += " Historical"
            if(signUpBinding.RomanceCheckbox.isChecked) genres += " Romance"
            if(signUpBinding.westernCheckbox.isChecked) genres += " Western"
            if(signUpBinding.fictionCheckbox.isChecked) genres += " Fiction"
            if(signUpBinding.costumbrismoCheckbox.isChecked) genres += " Costumbrismo"
            if(signUpBinding.magicRealismCheckbox.isChecked) genres += " Magic Realism"

            signUpViewModel.fields(name,email,password,repassword,genres)

        }
    }
}