package com.issen.ebooker

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView

@Suppress("DEPRECATION")
class MainActivity : BaseActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController: NavController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(
            navController.graph, drawerLayout
        )

        setupNavigationMenu(navController)
        setupActionBar(navController, appBarConfiguration)
//        setViewPager()
    }


    private fun setupNavigationMenu(navController: NavController) {
        val sideNavView = findViewById<NavigationView>(R.id.nav_view)
        sideNavView?.setupWithNavController(navController)

        sideNavView.menu.findItem(R.id.nav_logout)
            .setOnMenuItemClickListener {
                logout()
                true
            }
    }

    private fun setupActionBar(
        navController: NavController,
        appBarConfig: AppBarConfiguration
    ) {
        setupActionBarWithNavController(navController, appBarConfig)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun logout() {
        //todo logout
        Toast.makeText(this, "Logout clicked", Toast.LENGTH_SHORT).show()
    }
//    private fun setViewPager(){
//        viewpager.adapter = getFragmentPagerAdapter()
//        navigation.setOnNavigationItemSelectedListener(
//            getBottomNavigationItemSelectedListener())
//        viewpager.addOnPageChangeListener(getOnPageChangeListener())
//        viewpager.offscreenPageLimit = 5
//    }
//
//    private fun getFragmentPagerAdapter() =
//        object: FragmentPagerAdapter(supportFragmentManager){
//            override fun getCount() = 5
//            override fun getItem(position: Int) = when(position) {
//                USER_QUOTES -> UserQuotesFragment()
//                USER_BOOKS -> BooksFragment()
//                BOOK_QUOTES -> BookQuotesFragment()
//                NEWS -> NewsFragment()
//                MENU -> MenuFragment()
//
//                else -> {
//                    Fragment()
//                }
//            }
//        }
//
//    private fun getBottomNavigationItemSelectedListener() =
//        BottomNavigationView.OnNavigationItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.navigation_books -> {
//                    viewpager.currentItem = 0
//                    return@OnNavigationItemSelectedListener true
//                }
//                R.id.navigation_user_quotes -> {
//                    viewpager.currentItem = 1
//                    return@OnNavigationItemSelectedListener true
//                }
//                R.id.navigation_books_quotes -> {
//                    viewpager.currentItem = 2
//                    return@OnNavigationItemSelectedListener true
//                }
//                R.id.navigation_news -> {
//                    viewpager.currentItem = 3
//                    return@OnNavigationItemSelectedListener true
//                }
//                R.id.navigation_menu -> {
//                    viewpager.currentItem = 4
//                    return@OnNavigationItemSelectedListener true
//                }
//                else -> false
//
//            }
//        }
//
//    private fun getOnPageChangeListener() =
//        object : ViewPager.OnPageChangeListener {
//            override fun onPageScrollStateChanged(state: Int) {}
//            override fun onPageScrolled(position: Int,
//                                        positionOffset: Float,
//                                        positionOffsetPixels: Int) {}
//            override fun onPageSelected(position: Int) {
//                navigation.menu.getItem(position).isChecked = true
//            }
//        }
//
//    companion object {
//        const val USER_BOOKS = 0
//        const val USER_QUOTES = 1
//        const val BOOK_QUOTES = 2
//        const val NEWS = 3
//        const val MENU = 4
//
//    }

}
