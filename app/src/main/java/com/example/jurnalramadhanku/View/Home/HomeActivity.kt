package com.example.jurnalramadhanku.View.Home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.jurnalramadhanku.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


//        buttomNavigation.setOnNavigationItemSelectedListener(object :
//            BottomNavigationView.OnNavigationItemSelectedListener {
//            fun onNavigationItemSelected(@NonNull item: MenuItem): Boolean {
//                var fragment: Fragment? = null
//                when (item.getItemId()) {
//                    R.id.home -> fragment = Home()
//                    R.id.person -> fragment = Profile()
//                    R.id.notidications -> fragment = Notification()
//                }
//                return getFragmentPage(fragment)
//            }
//        })
//
//    }
    }
}