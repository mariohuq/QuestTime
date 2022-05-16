package dev.rodosteam.questtime.screen.common

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import dev.rodosteam.questtime.R
import org.hamcrest.Matchers

class LibraryScreen : BaseScreen() {
    fun play(index: Int): QuestContentScreen {
        val floatingActionButton2 = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.fragment_library_item__playButton),
                Matchers.allOf(
                    ViewMatchers.withId(R.id.fragment_library_item__main),
                    ViewMatchers.withId(R.id.library_recycler_view).childAtPosition(index)
                ).childAtPosition(2),
                ViewMatchers.isDisplayed()
            )
        )
        floatingActionButton2.perform(ViewActions.click())
        return QuestContentScreen()
    }
}