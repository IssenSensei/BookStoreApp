package com.example.bookstoreapp

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.bookstoreapp.matcher.RecyclerViewWithIdAction.withRecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.hamcrest.core.AllOf.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Testy okna z nowinkami (lista artykułów i linków)
 */
@RunWith(AndroidJUnit4::class)
class NewsFeedInstrumentedTest {

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

        onView(
            allOf(
                withText(mRulePackActivityCard.activity.resources.getString(R.string.navigation_books)),
                isDescendantOfA(withId(R.id.navigation)),
                isDisplayed()
            )
        )
            .perform(click())

    }

    @Test
    fun givenClickOnExpandShouldExpand() {
        Thread.sleep(3000L)

        onView(
            withRecyclerView(R.id.books_recycler_view)
                .atPositionOnView(0, R.id.lyt_expand)
        ).perform(click())

        onView(withId(R.id.bookDescription))
            .check(matches(isDisplayed()))
    }


    @After
    fun setUpAfter() {
        mRulePackActivityCard.finishActivity()
    }
}

