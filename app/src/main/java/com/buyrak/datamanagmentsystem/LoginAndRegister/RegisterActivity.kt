package com.buyrak.datamanagmentsystem.LoginAndRegister

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.buyrak.datamanagmentsystem.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    val getInstance = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
            btnRegister.setOnClickListener {
                if (etPasswordRegister.text.toString().equals(etPasswordAgainRegister.text.toString())){
                getInstance.createUserWithEmailAndPassword(etMailRegister.text.toString(), etPasswordRegister.text.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful){
                            Toast.makeText(this@RegisterActivity,"test", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

    }
}