package com.buyrak.datamanagmentsystem.TasksOperations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.buyrak.datamanagmentsystem.R
import com.buyrak.datamanagmentsystem.utils.BottomNavigationHelper
import kotlinx.android.synthetic.main.activity_main.*

class TasksActivity : AppCompatActivity() {
    val ACTIVITY_NO = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)
        BottomNavigationHelper.setUpNavigationView(this, bottomNavigationView,  bottomNavigationView.menu, ACTIVITY_NO)
    }
}