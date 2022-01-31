package com.buyrak.datamanagmentsystem.utils

import android.content.Context
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Functions {

    companion object{
        var currentUser = FirebaseAuth.getInstance().currentUser
        fun sendAuthMail(context:Context){
            currentUser?.sendEmailVerification()?.addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(context,"You have successfully registered! please check your mailbox for confirmation.", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}