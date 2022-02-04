package com.buyrak.datamanagmentsystem.LoginAndRegister

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.buyrak.datamanagmentsystem.MainOperations.MainActivity
import com.buyrak.datamanagmentsystem.R
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.dialog_login_phone.*
import okio.Timeout
import java.util.concurrent.TimeUnit

class LoginActivity : AppCompatActivity() {
    val getInstance = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
            if (!etMail.text.isNullOrEmpty() && !etPassword.text.isNullOrEmpty()) {
                imgEtMailLoginError.visibility = View.INVISIBLE
                imgEtPasswordLoginError.visibility = View.INVISIBLE
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(etMail.text.toString(), etPassword.text.toString())
                    .addOnCompleteListener {
                        if(getInstance.currentUser!!.isEmailVerified) {
                            startActivity(
                                Intent(
                                    this@LoginActivity,
                                    MainActivity::class.java
                                ).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                            )
                            Toast.makeText(
                                this@LoginActivity,
                                "You have successfully logged in.",
                                Toast.LENGTH_LONG
                            ).show()
                        }else {
                            imgEtMailLoginError.visibility = View.VISIBLE
                            imgEtPasswordLoginError.visibility = View.VISIBLE
                            Toast.makeText(
                                this@LoginActivity,
                                "Your email address or password is incorrect, please try again.",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            }else{
                imgEtMailLoginError.visibility = View.VISIBLE
                imgEtPasswordLoginError.visibility = View.VISIBLE
                Toast.makeText(
                    this@LoginActivity,
                    "Your email address or password is incorrect, please try again.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        btnLoginFacebook.setOnClickListener{
        }




        txtRegisterPage.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            overridePendingTransition(R.anim.right_slide, R.anim.slide_to_left)
        }
        txtRememberPassword.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RememberActivity::class.java))
            overridePendingTransition(R.anim.right_slide, R.anim.slide_to_left)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, RegisterActivity::class.java))
        overridePendingTransition(R.anim.right_slide, R.anim.slide_to_left)
    }
}