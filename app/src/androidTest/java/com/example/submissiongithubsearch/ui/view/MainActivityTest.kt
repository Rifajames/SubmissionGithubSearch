package com.example.submissiongithubsearch.ui.view

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.submissiongithubsearch.R

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest{
    @Before
    fun setup(){
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun assertUITest(){
        val editText = Espresso.onView(ViewMatchers.withId(R.id.svSearchUser))
        editText.perform(ViewActions.typeText("RifaJames"))
        onView(withId(R.id.svSearchUser)).perform(ViewActions.pressImeActionButton())
    }
}