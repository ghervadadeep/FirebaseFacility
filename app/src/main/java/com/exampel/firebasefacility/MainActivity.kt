package com.exampel.firebasefacility

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var fragment_view: LinearLayout
    lateinit var navigation_view: NavigationView
    lateinit var drawer_layout: DrawerLayout

    lateinit var toggle: ActionBarDrawerToggle

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        fragment_view = findViewById(R.id.fragment_view)
        navigation_view = findViewById(R.id.navigation_view)
        drawer_layout = findViewById(R.id.drawer_layout)

        auth = Firebase.auth


        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        toggle = ActionBarDrawerToggle(
            this@MainActivity,
            drawer_layout,
            toolbar,
            R.string.open,
            R.string.close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        navigation_view.setNavigationItemSelectedListener(object :
            NavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {

                if (item.itemId == R.id.f1) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_view, phonenumber())
                        .commit()
                    Toast.makeText(
                        this@MainActivity,
                        "phone number authentication",
                        Toast.LENGTH_SHORT
                    ).show()
                    drawer_layout.closeDrawers()
                }

                if (item.itemId == R.id.f2) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_view, ContinuewithGoogle())
                        .commit()
                    Toast.makeText(
                        this@MainActivity,
                        "continue with google",
                        Toast.LENGTH_SHORT
                    ).show()
                    drawer_layout.closeDrawers()
                }

                if (item.itemId == R.id.f3) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_view, RealTimeDatabase()).commit()
                    Toast.makeText(this@MainActivity, "Real Time Database", Toast.LENGTH_SHORT)
                        .show()
                    drawer_layout.closeDrawers()
                }

                if (item.itemId == R.id.f4) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_view, Notification()).commit()
                    Toast.makeText(this@MainActivity, "notification", Toast.LENGTH_SHORT).show()
                    drawer_layout.closeDrawers()
                }

                if (item.itemId == R.id.f5) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_view, InAppMessaging()).commit()
                    Toast.makeText(this@MainActivity, "in-app-messaging", Toast.LENGTH_SHORT).show()
                    drawer_layout.closeDrawers()
                }

                if (item.itemId == R.id.f6) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_view, Gallery()).commit()
                    Toast.makeText(this@MainActivity, "Firebase Storage", Toast.LENGTH_SHORT).show()
                    drawer_layout.closeDrawers()
                }

                return true
            }

        })


//        supportFragmentManager.beginTransaction().replace(R.id.fragment_view, phonenumber())
//            .commit()

        var naviviewwww = navigation_view.getHeaderView(0)
        var title: TextView = naviviewwww.findViewById(R.id.titleee)
        title.setText("FIREBASE FACILITY")


    }


}