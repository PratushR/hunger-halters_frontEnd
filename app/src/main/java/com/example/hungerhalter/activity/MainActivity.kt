package com.example.hungerhalter.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.hungerhalter.R
import com.example.hungerhalter.fragment.*
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var toolbar: Toolbar
    lateinit var frames: FrameLayout
    lateinit var navigationView: NavigationView
    var previousMenuItem: MenuItem? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)
        coordinatorLayout = findViewById(R.id.coordinateLayout)
        toolbar = findViewById(R.id.toolbar)
        frames = findViewById(R.id.frames)
        navigationView = findViewById(R.id.navigationView)
        setupToolbar()
        homepage()

        val actionBarDrawerToggle = ActionBarDrawerToggle(this@MainActivity,drawerLayout,R.string.open_drawer,R.string.close_drawer)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.drawerArrowDrawable.setColor(resources.getColor(R.color.colorAccent))
        actionBarDrawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> {
                    homepage()
                    drawerLayout.closeDrawers()
                }

                R.id.profile -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frames,ProfileFragment()).commit()
                    supportActionBar?.title = "My Profile"
                    drawerLayout.closeDrawers()
                }

                R.id.favorites -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frames,FavoritesFragment()).commit()
                    supportActionBar?.title = "All Favorites Restaurant"
                    drawerLayout.closeDrawers()
                }

                R.id.order_history -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frames,OrderFragment()).commit()
                    supportActionBar?.title = "My Order Details"
                    drawerLayout.closeDrawers()
                }

                R.id.faq -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frames, HelpFragment()).commit()
                    supportActionBar?.title = "Frequently Asked Questions (FAQs)"
                    drawerLayout.closeDrawers()
                }

                R.id.settings -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frames,
                        SettingsFragment()
                    ).commit()
                    supportActionBar?.title = "App Settings"
                    drawerLayout.closeDrawers()
                }

                R.id.logout -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frames,HomeFragment()).commit()
                    drawerLayout.closeDrawers()
                }
            }

            if(previousMenuItem != null){
                previousMenuItem?.isChecked = false
            }

            it.isChecked = true
            it.isCheckable = true
            previousMenuItem = it

            return@setNavigationItemSelectedListener true
        }
    }

    fun setupToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Hunger Halter"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if(id == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START)
        }

        return super.onOptionsItemSelected(item)
    }

    fun homepage(){
        val fragment = HomeFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frames,fragment)
        transaction.commit()
        supportActionBar?.title = "All Restaurant Details"

        navigationView.setCheckedItem(R.id.home)
    }

    override fun onBackPressed() {
        val frag = supportFragmentManager.findFragmentById(R.id.frames)

        when(frag){
            !is HomeFragment -> homepage()
            else -> super.onBackPressed()
        }
    }
}