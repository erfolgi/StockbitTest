package com.stockbit.hiring

import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.stockbit.hiring.ui.login.LoginActivity
import com.stockbit.hiring.util.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginActivityTest {
    @Rule
    @JvmField var activityRule = ActivityTestRule(LoginActivity::class.java)

    @Before
    fun setup(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoIdlingResource)
    }

    @Test
    fun testAppBehaviour() {
        Espresso.onView(ViewMatchers.withId(R.id.et_username)).perform(clearText(), typeText("erfolgi"))
        Espresso.pressBack()
        Espresso.onView(ViewMatchers.withId(R.id.et_password)).perform(clearText(), typeText("passwords"))
        Espresso.pressBack()
        Espresso.onView(ViewMatchers.withId(R.id.bt_login)).perform(click())

        Espresso.onView(ViewMatchers.withId(R.id.rv_stock)).perform(ViewActions.swipeUp())
        Espresso.onView(ViewMatchers.withId(R.id.rv_stock)).perform(ViewActions.swipeUp())
        Espresso.onView(ViewMatchers.withId(R.id.rv_stock)).perform(ViewActions.swipeUp())

        Espresso.onView(ViewMatchers.withId(R.id.rv_stock)).perform(ViewActions.swipeDown())
        Espresso.onView(ViewMatchers.withId(R.id.rv_stock)).perform(ViewActions.swipeDown())
        Espresso.onView(ViewMatchers.withId(R.id.rv_stock)).perform(ViewActions.swipeDown())

        Espresso.onView(ViewMatchers.withId(R.id.rv_stock)).perform(ViewActions.swipeUp())
        Espresso.onView(ViewMatchers.withId(R.id.rv_stock)).perform(ViewActions.swipeUp())

    }
}