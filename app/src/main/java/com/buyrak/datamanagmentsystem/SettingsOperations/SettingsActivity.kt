package com.buyrak.datamanagmentsystem.SettingsOperations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.buyrak.datamanagmentsystem.R
import com.buyrak.datamanagmentsystem.utils.BottomNavigationHelper
import kotlinx.android.synthetic.main.activity_main.*

class SettingsActivity : AppCompatActivity() {
    val ACTIVITY_NO = 2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        BottomNavigationHelper.setUpNavigationView(this, bottomNavigationView,  bottomNavigationView.menu, ACTIVITY_NO)
    }
}