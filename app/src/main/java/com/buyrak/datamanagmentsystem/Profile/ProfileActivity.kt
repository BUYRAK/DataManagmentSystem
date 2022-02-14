package com.buyrak.datamanagmentsystem.Profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.buyrak.datamanagmentsystem.MainOperations.MainActivity
import com.buyrak.datamanagmentsystem.R
import com.buyrak.datamanagmentsystem.utils.BottomNavigationHelper
import com.buyrak.datamanagmentsystem.utils.Functions
import com.buyrak.datamanagmentsystem.utils.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.bottomNavigationView
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {
    val ACTIVITY_NO = 2
    var currentUser = FirebaseAuth.getInstance().currentUser
    var referanceUser = FirebaseDatabase.getInstance().reference.child("users")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        BottomNavigationHelper.setUpNavigationView(this, bottomNavigationView,  bottomNavigationView.menu, ACTIVITY_NO)
        //readUserDataProfile()

       // txtProfileDisplayName.text =
        // referanceUser.child(currentUser!!.uid).orderByChild("email").limitToFirst(1).toString()

    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        this.startActivity(intent)
    }

    fun readUserDataProfile(){
        var currentUser = FirebaseAuth.getInstance().currentUser
        var referance = FirebaseDatabase.getInstance().reference

        var queryEqualUser = referance.child("users")
            .orderByKey()
            .equalTo(currentUser!!.uid)

        queryEqualUser.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot){
                for(singleSnapShot in snapshot.children){
                    val getUser = singleSnapShot.getValue(User::class.java)

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}