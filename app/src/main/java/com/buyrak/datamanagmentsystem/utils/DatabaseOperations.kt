package com.buyrak.datamanagmentsystem.utils

import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DatabaseOperations {

    companion object{
         fun readProfileUserData(displayName:TextView, mailAddress:TextView, phoneNumber:TextView){
            val referance = FirebaseDatabase.getInstance().reference
            val user = FirebaseAuth.getInstance().currentUser

            val query = referance.child("users")
                .orderByKey()
                .equalTo(user!!.uid)

            query.addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (singleSnapshot in snapshot.children){
                        var readUser = singleSnapshot.getValue(User::class.java)
                        displayName.text = readUser!!.fullName
                        mailAddress.text = readUser.email
                        phoneNumber.text = readUser.phone
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })

        }
    }
}