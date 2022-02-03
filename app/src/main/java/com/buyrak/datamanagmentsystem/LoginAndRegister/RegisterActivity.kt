package com.buyrak.datamanagmentsystem.LoginAndRegister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.buyrak.datamanagmentsystem.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*
import com.buyrak.datamanagmentsystem.utils.*
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {
    val getInstance = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
            btnRegister.setOnClickListener {
                if(!etMailRegister.text.isNullOrEmpty()){
                    if (!etPasswordRegister.text.isNullOrEmpty() && !etPasswordAgainRegister.text.isNullOrEmpty() && etPasswordRegister.text.toString() == etPasswordAgainRegister.text.toString()){
                        imgEtMailRegisterError.visibility = View.INVISIBLE
                        imgEtPasswordRegisterError.visibility = View.INVISIBLE
                        imgEtPasswordTwoRegisterError.visibility = View.INVISIBLE
                        getInstance.createUserWithEmailAndPassword(etMailRegister.text.toString(), etPasswordRegister.text.toString())
                            .addOnCompleteListener{
                                if (it.isSuccessful){
                                    val user = User(
                                        userId = FirebaseAuth.getInstance().currentUser!!.uid,
                                        email = etMailRegister.text.toString(),
                                        fullName = "No Information",
                                        phone = "No Information",
                                        profilePhoto = "default_user.jpg",
                                        role = "1"
                                    )
                                    FirebaseDatabase.getInstance().reference
                                        .child("users")
                                        .child(FirebaseAuth.getInstance().currentUser!!.uid)
                                        .setValue(user)
                                    Functions.sendAuthMail(this@RegisterActivity)
                                    startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                                    overridePendingTransition(R.anim.left_slide, R.anim.slide_to_right)

                                }else{
                                    Toast.makeText(this@RegisterActivity,"An error occurred while registering. Please try again later.", Toast.LENGTH_LONG).show()
                                }
                            }
                    }else{
                        imgEtPasswordRegisterError.visibility = View.VISIBLE
                        imgEtPasswordTwoRegisterError.visibility = View.VISIBLE
                        Toast.makeText(
                            this@RegisterActivity,
                            "Passwords do not match or incorrect. Your password must be at least 8 characters.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }else{
                    imgEtMailRegisterError.visibility = View.VISIBLE
                    Toast.makeText(
                        this@RegisterActivity,
                        "Email is wrong or incorrect.",
                        Toast.LENGTH_LONG
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