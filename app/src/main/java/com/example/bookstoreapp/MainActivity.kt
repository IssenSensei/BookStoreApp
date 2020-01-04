package com.example.bookstoreapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.bookstoreapp.bookQuotes.BookQuotesFragment
import com.example.bookstoreapp.books.BooksFragment
import com.example.bookstoreapp.menu.MenuFragment
import com.example.bookstoreapp.news.NewsFragment
import com.example.bookstoreapp.userQuotes.UserQuotesFragment
import com.example.bookstoreapp.utils.SharedPreference
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var currentTheme: String
    private lateinit var sharedPreference: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        sharedPreference = SharedPreference(this)
        currentTheme = sharedPreference.getValueString("current_theme").toString()
        setAppTheme(currentTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setViewPager()
    }

    override fun onResume() {
        super.onResume()
        val theme = sharedPreference.getValueString("current_theme")
        if(currentTheme != theme)
            recreate()
    }

    private fun setAppTheme(currentTheme: String) {
        when (currentTheme) {
            "THEME_DARKISH" -> setTheme(R.style.Theme_App_Darkish)
            "THEME_PURPLISH" -> setTheme(R.style.Theme_App_Purplish)
            "THEME_GREENISH" -> setTheme(R.style.Theme_App_Greenish)
            "THEME_FULLWHITE" -> setTheme(R.style.Theme_App_FullWhite)
            else -> setTheme(R.style.Theme_App_Whitish)
        }
    }

    private fun setViewPager(){
        viewpager.adapter = getFragmentPagerAdapter()
        navigation.setOnNavigationItemSelectedListener(
            getBottomNavigationItemSelectedListener())
        viewpager.addOnPageChangeListener(getOnPageChangeListener())
        viewpager.offscreenPageLimit = 5
    }

    private fun getFragmentPagerAdapter() =
        object: FragmentPagerAdapter(supportFragmentManager){
            override fun getCount() = 5
            override fun getItem(position: Int) = when(position) {
                USER_QUOTES -> UserQuotesFragment()
                USER_BOOKS -> BooksFragment()
                BOOK_QUOTES -> BookQuotesFragment()
                NEWS -> NewsFragment()
                MENU -> MenuFragment()

                else -> {
                    Fragment()
                }
            }
        }

    private fun getBottomNavigationItemSelectedListener() =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_books -> {
                    viewpager.currentItem = 0
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_user_quotes -> {
                    viewpager.currentItem = 1
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_books_quotes -> {
                    viewpager.currentItem = 2
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_news -> {
                    viewpager.currentItem = 3
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_menu -> {
                    viewpager.currentItem = 4
                    return@OnNavigationItemSelectedListener true
                }
                else -> false

            }
        }

    private fun getOnPageChangeListener() =
        object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int,
                                        positionOffset: Float,
                                        positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                navigation.menu.getItem(position).isChecked = true
            }
        }

    companion object {
        const val USER_BOOKS = 0
        const val USER_QUOTES = 1
        const val BOOK_QUOTES = 2
        const val NEWS = 3
        const val MENU = 4

    }

}
