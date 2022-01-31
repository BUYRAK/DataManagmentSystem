package com.buyrak.datamanagmentsystem.LoginAndRegister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.buyrak.datamanagmentsystem.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*
import com.buyrak.datamanagmentsystem.utils.*

class RegisterActivity : AppCompatActivity() {
    val getInstance = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
            btnRegister.setOnClickListener {
                if (etPasswordRegister.text.toString().equals(etPasswordAgainRegister.text.toString())){
                getInstance.createUserWithEmailAndPassword(etMailRegister.text.toString(), etPasswordRegister.text.toString())
                    .addOnCompleteListener{
                        if (it.isSuccessful){

                            Functions.sendAuthMail(this@RegisterActivity)
                        }else{
                            Toast.makeText(this@RegisterActivity,"An error occurred while registering. Please try again later.", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }

        txtBackLoginScreen.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            overridePendingTransition(R.anim.left_slide, R.anim.slide_to_right)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, LoginActivity::class.java))
        overridePendingTransition(R.anim.left_slide, R.anim.slide_to_right)
    }
}