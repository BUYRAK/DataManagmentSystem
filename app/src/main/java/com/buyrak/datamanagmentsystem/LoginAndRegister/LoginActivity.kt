package com.buyrak.datamanagmentsystem.LoginAndRegister


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.buyrak.datamanagmentsystem.MainOperations.MainActivity
import com.buyrak.datamanagmentsystem.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    val getInstance = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
            if (!etMail.text.isNullOrEmpty() && !etPassword.text.isNullOrEmpty()) {
                imgEtMailLoginError.visibility = View.INVISIBLE
                imgEtPasswordLoginError.visibility = View.INVISIBLE
                FirebaseAuth.getInstance().signInWithEmailAndPassword(etMail.text.toString(), etPassword.text.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            if (getInstance.currentUser!!.isEmailVerified) {
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
                            } else {
                                Toast.makeText(
                                    this@LoginActivity,
                                    "Please verify email address.",
                                    Toast.LENGTH_LONG
                                ).show()
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