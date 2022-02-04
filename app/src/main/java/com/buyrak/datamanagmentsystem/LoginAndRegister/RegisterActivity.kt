package com.buyrak.datamanagmentsystem.LoginAndRegister

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.buyrak.datamanagmentsystem.R
import kotlinx.android.synthetic.main.activity_register.*
import com.buyrak.datamanagmentsystem.utils.*
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.dialog_login_phone.*
import java.util.concurrent.TimeUnit

class RegisterActivity : AppCompatActivity() {
    val getInstance = FirebaseAuth.getInstance()
    val firebaseAuth = Firebase.auth
    val firebaseAuthSettings = firebaseAuth.firebaseAuthSettings
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
            btnRegister.setOnClickListener {
                if(!etMailPhoneRegister.text.isNullOrEmpty()){
                    if (!etPasswordRegister.text.isNullOrEmpty() && !etPasswordAgainRegister.text.isNullOrEmpty() && etPasswordRegister.text.toString() == etPasswordAgainRegister.text.toString()){
                        imgEtMailRegisterError.visibility = View.INVISIBLE
                        imgEtPasswordRegisterError.visibility = View.INVISIBLE
                        imgEtPasswordTwoRegisterError.visibility = View.INVISIBLE
                        if (etMailPhoneRegister.text.toString().contains("@", true )){
                            getInstance.createUserWithEmailAndPassword(etMailPhoneRegister.text.toString(), etPasswordRegister.text.toString())
                                .addOnCompleteListener{
                                    if (it.isSuccessful){
                                        val user = User(
                                            userId = FirebaseAuth.getInstance().currentUser!!.uid,
                                            email = etMailPhoneRegister.text.toString(),
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
                        }else if (etMailPhoneRegister.text.toString().contains("90")) {

                            val loginPhoneDialog = View.inflate(this, R.layout.dialog_login_phone, null)
                            val builder = AlertDialog.Builder(this)
                            builder.setView(loginPhoneDialog)

                            val dialog = builder.create()
                            dialog.show()
                            dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)

                            val phoneNumber = "+" + etMailPhoneRegister.text.toString()

                            val options = PhoneAuthOptions.newBuilder(getInstance)
                                .setPhoneNumber(phoneNumber)
                                .setActivity(this)
                                .setTimeout(30L, TimeUnit.SECONDS)
                                .setCallbacks(mCallBack)
                                .build()
                            PhoneAuthProvider.verifyPhoneNumber(options)

                        }else{
                            imgEtMailRegisterError.visibility = View.VISIBLE
                            Toast.makeText(
                                this@RegisterActivity,
                                "Email/Phone Number is wrong or incorrect.",
                                Toast.LENGTH_LONG
                            ).show()
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
                        "Email/Phone Number is wrong or incorrect.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        btnVerificationSms.setOnClickListener {

        }
        txtBackLoginScreen.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            overridePendingTransition(R.anim.left_slide, R.anim.slide_to_right)
        }
    }

    val mCallBack: PhoneAuthProvider.OnVerificationStateChangedCallbacks =
        object: PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(p0, p1)
                Toast.makeText(
                    this@RegisterActivity,
                    "Success!!!",
                    Toast.LENGTH_LONG
                ).show()
            }
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                Toast.makeText(
                    this@RegisterActivity,
                    "Verification Operations",
                    Toast.LENGTH_LONG
                ).show()

            }

            override fun onVerificationFailed(p0: FirebaseException) {
                Toast.makeText(
                    this@RegisterActivity,
                    "${p0.message}",
                    Toast.LENGTH_LONG
                ).show()
            }

        }



    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, LoginActivity::class.java))
        overridePendingTransition(R.anim.left_slide, R.anim.slide_to_right)
    }
}