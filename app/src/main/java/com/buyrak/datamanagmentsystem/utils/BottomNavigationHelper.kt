package com.buyrak.datamanagmentsystem.utils

import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import com.buyrak.datamanagmentsystem.MainOperations.MainActivity
import com.buyrak.datamanagmentsystem.ProfileOperations.ProfileActivity
import com.buyrak.datamanagmentsystem.R
import com.buyrak.datamanagmentsystem.TasksOperations.TasksActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_profile.*
import org.w3c.dom.Text

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

                        R.id.navigation_profile -> {
                            val intent = Intent(context, ProfileActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
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

