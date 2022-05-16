package dev.rodosteam.questtime.screen.common

import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import dev.rodosteam.questtime.R
import org.hamcrest.Matchers

open class BaseScreen {
    private enum class Navigation(private val id: Int) {
        LIBRARY(R.id.navigation_library),
        EXTERNAL(R.id.navigation_external),
        EDITOR(R.id.navigation_editor),
        SETTINGS(R.id.navigation_settings);
        private fun item(): ViewInteraction = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.navigation_external),
                ViewMatchers.isDisplayed()
            )
        )

        fun click() {
            item().perform(ViewActions.click())
        }
    }

    fun goToLibrary(): LibraryScreen {
        Navigation.LIBRARY.click()
        return LibraryScreen()
    }

    fun goToExternal(): ExternalScreen {
        Navigation.EXTERNAL.click()
        return ExternalScreen()
    }
}