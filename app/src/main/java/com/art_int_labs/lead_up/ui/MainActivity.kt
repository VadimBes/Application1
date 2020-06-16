package com.art_int_labs.lead_up.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.art_int_labs.lead_up.R
import com.art_int_labs.lead_up.ui.Achivments.AchivmentsFragment
import com.art_int_labs.lead_up.ui.Course.CourseFragment
import com.art_int_labs.lead_up.ui.Knowledge.KnowledgeFragment
import com.art_int_labs.lead_up.ui.Main.MainFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    private lateinit var navController: NavController
    private lateinit var drawView:DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val toogle = ActionBarDrawerToggle(this,main_l,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        main_l.addDrawerListener(toogle)
        toogle.syncState()

        navController = Navigation.findNavController(this,R.id.nav_host_fragment)
        bottom_nav.setupWithNavController(navController)
        //NavigationUI.setupActionBarWithNavController(this,navController)
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (main_l.isDrawerOpen(GravityCompat.START)){
            main_l.closeDrawer(GravityCompat.START)
        }else super.onBackPressed()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_menu ->{
                supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment,MainFragment()).commit()
                bottom_nav.selectedItemId = R.id.mainFragment
            }
            R.id.nav_course ->{
                supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment,CourseFragment()).commit()
                bottom_nav.selectedItemId = R.id.courseFragment
            }
            R.id.nav_knowledge ->{
                supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment,KnowledgeFragment()).commit()
                bottom_nav.selectedItemId = R.id.knowledgeFragment
            }
            R.id.nav_ach ->{
                supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment,AchivmentsFragment()).commit()
                bottom_nav.selectedItemId = R.id.achivmentsFragment
            }
        }
        main_l.closeDrawer(GravityCompat.START)
        return true
    }

//    override fun onSupportNavigateUp(): Boolean {
//        return NavigationUI.navigateUp(navController,null)
//    }
}
