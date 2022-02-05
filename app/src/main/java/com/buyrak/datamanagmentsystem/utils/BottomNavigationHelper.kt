package com.buyrak.datamanagmentsystem.utils

import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import com.buyrak.datamanagmentsystem.MainOperations.MainActivity
import com.buyrak.datamanagmentsystem.R
import com.buyrak.datamanagmentsystem.SettingsOperations.SettingsActivity
import com.buyrak.datamanagmentsystem.TasksOperations.TasksActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationHelper {

    companion object{
        fun setUpNavigation(context: Context, bottomNavigationView: BottomNavigationView){
            bottomNavigationView.setOnItemSelectedListener(object : BottomNavigationView.OnNavigationItemSelectedListener{
                override fun onNavigationItemSelected(item: MenuItem): Boolean {
                    when(item.itemId){

                        R.id.navigation_home -> {
                            val intent = Intent(context, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                            context.startActivity(intent)
                            return true
                        }

                        R.id.navigation_tasks -> {
                            val intent = Intent(context, TasksActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                            context.startActivity(intent)
                            return true
                        }

                        R.id.navigation_settings -> {
                            val intent = Intent(context, SettingsActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                            context.startActivity(intent)
                            return true
                        }

                    }
                    return false
                }

            })

        }

        fun setUpNavigationView(context: Context, bottomNavigationView: BottomNavigationView, menu : Menu, activity_no:Int){
            setUpNavigation(context,bottomNavigationView)
            val menuItem = menu.getItem(activity_no)
            menuItem.setChecked(true)
        }
    }
}

