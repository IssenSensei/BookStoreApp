package com.example.bookstoreapp

import android.content.Intent
import android.widget.CheckBox
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.bookstoreapp.BuildConfig
import kotlinx.android.synthetic.main.activity_main.*
import org.hamcrest.core.AllOf.allOf
import org.junit.*
import org.junit.Assert.assertEquals
import org.junit.runner.RunWith


/**
 * Testy nawigacji po menu głównym
 */
@RunWith(AndroidJUnit4::class)
class MainActivityMenuNavigationInstrumentedTest {

    private var bottomNavigation: BottomNavigationView? = null

    @Rule
    @JvmField
    var mRulePackActivityCard: ActivityTestRule<MainActivity> = ActivityTestRule(
        MainActivity::class.java, true, false
    )

    @Before
    fun setUpBefore() {
        mRulePackActivityCard.launchActivity(Intent())
        bottomNavigation = mRulePackActivityCard.activity.findViewById(R.id.navigation)
    }


    @After
    fun setUpAfter() {
        mRulePackActivityCard.finishActivity()
    }

    @Test
    fun goToMenu() {
        Thread.sleep(3000L)

        onView(
            allOf(
                withText(mRulePackActivityCard.activity.resources.getString(R.string.navigation_menu)),
                isDescendantOfA(withId(R.id.navigation)),
                isDisplayed()
            )
        )
            .perform(click())

        // Verify the correct item is now selected.
        assertChecksFromLeft(
            false, false, false, false, true)
    }

    private fun assertChecksFromLeft(
        books: Boolean,
        userQuotes: Boolean,
        bookQuotes: Boolean,
        news: Boolean,
        menu: Boolean
    ) {
        assertEquals(
            books, bottomNavigation!!.menu.findItem(R.id.navigation_books).isChecked)
        assertEquals(
            userQuotes,
            bottomNavigation!!.menu.findItem(R.id.navigation_books_quotes).isChecked
        )
        assertEquals(
            bookQuotes,
            bottomNavigation!!.menu.findItem(R.id.navigation_user_quotes).isChecked
        )
        assertEquals(
            news, bottomNavigation!!.menu.findItem(R.id.navigation_news).isChecked)
        assertEquals(
            menu, bottomNavigation!!.menu.findItem(R.id.navigation_menu).isChecked)
    }

    @Test
    fun goToUserQuotes() {
        Thread.sleep(3000L)

        onView(
            allOf(
                withText(mRulePackActivityCard.activity.resources.getString(R.string.navigation_user_quotes)),
                isDescendantOfA(withId(R.id.navigation)),
                isDisplayed()
            )
        )
            .perform(click())

        // Verify the correct item is now selected.
        assertChecksFromLeft(false, true, false, false, false)
    }

    @Test
    fun goToBookQuotes() {
        Thread.sleep(3000L)

        onView(
            allOf(
                withText(mRulePackActivityCard.activity.resources.getString(R.string.navigation_book_quotes)),
                isDescendantOfA(withId(R.id.navigation)),
                isDisplayed()
            )
        )
            .perform(click())

        // Verify the correct item is now selected.
        assertChecksFromLeft(false, false, false, true, false)
    }

    @Test
    fun goToNews() {
        Thread.sleep(3000L)

        onView(
            allOf(
                withText(mRulePackActivityCard.activity.resources.getString(R.string.navigation_news)),
                isDescendantOfA(withId(R.id.navigation)),
                isDisplayed()
            )
        )
            .perform(click())

        assertChecksFromLeft(false, false, false, false, true)
    }

    @Test
    fun booksInitiallyChecked() {
        Thread.sleep(3000L)
        assertChecksFromLeft(true, false, false, false, false)

    }

    @Test
    fun goExitOnboardingButton() {
        Thread.sleep(3000L)
        Espresso.pressBackUnconditionally()
        assert(mRulePackActivityCard.activity.isDestroyed)
    }

    @Test
    fun validatePageTitles() {
        Thread.sleep(3000L)
        assertEquals(
            mRulePackActivityCard.activity.resources.getString(R.string.navigation_books),
            mRulePackActivityCard.activity.viewpager.adapter!!.getPageTitle(0)
        )
        assertEquals(
            mRulePackActivityCard.activity.resources.getString(R.string.navigation_user_quotes),
            mRulePackActivityCard.activity.viewpager.adapter!!.getPageTitle(1)
        )
        assertEquals(
            mRulePackActivityCard.activity.resources.getString(R.string.navigation_book_quotes),
            mRulePackActivityCard.activity.viewpager.adapter!!.getPageTitle(2)
        )
        assertEquals(
            mRulePackActivityCard.activity.resources.getString(R.string.navigation_news),
            mRulePackActivityCard.activity.viewpager.adapter!!.getPageTitle(3)
        )
        assertEquals(
            mRulePackActivityCard.activity.resources.getString(R.string.navigation_menu),
            mRulePackActivityCard.activity.viewpager.adapter!!.getPageTitle(4)
        )
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.bookstoreapp${BuildConfig.FLAVOR}", appContext.packageName)
    }


}
