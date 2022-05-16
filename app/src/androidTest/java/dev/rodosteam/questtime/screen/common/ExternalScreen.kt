package dev.rodosteam.questtime.screen.common

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers
import dev.rodosteam.questtime.R
import org.hamcrest.Matchers

class ExternalScreen : BaseScreen() {
    fun downloadButton(index: Int) = Matchers.allOf(
        ViewMatchers.withId(R.id.fragment_external_item__downloadButton),
        Matchers.allOf(
            ViewMatchers.withId(R.id.fragment_library_item__main),
            ViewMatchers.withId(R.id.external__recycler_view).childAtPosition(index)
        ).childAtPosition(2),
        ViewMatchers.isDisplayed()
    )

    fun download(index: Int): ExternalScreen {
        onView(downloadButton(index)).perform(click())
        return this
    }


}