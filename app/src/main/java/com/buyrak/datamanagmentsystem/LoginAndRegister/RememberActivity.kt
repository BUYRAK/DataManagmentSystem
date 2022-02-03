package com.buyrak.datamanagmentsystem.LoginAndRegister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.buyrak.datamanagmentsystem.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_remember.*

class RememberActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remember)

        btnRemember.setOnClickListener {
            if (!etMailRemember.text.isNullOrEmpty()){
                imgEtMailRememberError.visibility = View.INVISIBLE
                FirebaseAuth.getInstance().sendPasswordResetEmail(etMailRemember.text.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful){
                            Toast.makeText(
                                this@RememberActivity,
                                "Your password reset link has been sent, please check your mailbox",
                                Toast.LENGTH_SHORT
                            ).show()
                            startActivity(Intent(this, LoginActivity::class.java))
                            overridePendingTransition(R.anim.left_slide, R.anim.slide_to_right)
                        }else{
                            Toast.makeText(
                                this@RememberActivity,
                                "There is no such e-mail address registered in the system.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

            }else{
                imgEtMailRememberError.visibility = View.VISIBLE
                Toast.makeText(
                    this@RememberActivity,
                    "Please enter a valid email",
                    Toast.LENGTH_SHORT
                ).show()
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