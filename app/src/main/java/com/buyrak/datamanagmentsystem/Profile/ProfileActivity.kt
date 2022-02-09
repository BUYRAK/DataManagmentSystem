package com.buyrak.datamanagmentsystem.Profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.buyrak.datamanagmentsystem.MainOperations.MainActivity
import com.buyrak.datamanagmentsystem.R
import com.buyrak.datamanagmentsystem.utils.BottomNavigationHelper
import com.buyrak.datamanagmentsystem.utils.Functions
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.bottomNavigationView
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {
    val ACTIVITY_NO = 2
    override fun onCreate(savedInstanceState: Bundle?) {
        val user = Functions.currentUser
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        BottomNavigationHelper.setUpNavigationView(this, bottomNavigationView,  bottomNavigationView.menu, ACTIVITY_NO)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        this.startActivity(intent)
    }
}