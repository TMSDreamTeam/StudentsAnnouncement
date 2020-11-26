package com.tms.studentsannouncement

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        navView.setOnNavigationItemSelectedListener {item->
            when(item.itemId) {
                R.id.navigation_home -> {

                    true
                }
                R.id.navigation_add->{
                    true
                }
                R.id.navigation_my_announcement->{
                    true
                }

                else -> false
            }
        }
    }
}