package dev.rodosteam.questtime.screen.common


import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class HobbitTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun hobbitTest() {

        val hobbitIndex = 4
        val hobbitName = "Хоббит"

        val contentScreen = LibraryScreen()
            .goToExternal()
            .download(hobbitIndex)
            .goToLibrary()
            .play(0)
        contentScreen.actionBarText.check(matches(allOf(
            withText(hobbitName),
            isDisplayed())))
    }
}
