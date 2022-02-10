package com.buyrak.datamanagmentsystem.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DatabaseOperations {

    companion object{

        fun readUserData(){
            var currentUser = FirebaseAuth.getInstance().currentUser
            var referance = FirebaseDatabase.getInstance().reference

            var queryEqualUser = referance.child("users")
                .orderByKey()
                .equalTo(currentUser!!.uid)

            queryEqualUser.addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot){
                    for(singleSnapShot in snapshot.children){
                        var getUser = singleSnapShot.getValue(User::class.java)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        }
    }
}