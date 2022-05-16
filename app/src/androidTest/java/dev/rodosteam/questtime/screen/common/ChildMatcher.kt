package dev.rodosteam.questtime.screen.common

import android.view.View
import android.view.ViewGroup
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

fun Matcher<View>.childAtPosition(
    position: Int
): Matcher<View> {
    return object : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description) {
            description.appendText("Child at position $position in parent ")
            this@childAtPosition.describeTo(description)
        }

        public override fun matchesSafely(view: View): Boolean {
            val parent = view.parent
            return parent is ViewGroup && this@childAtPosition.matches(parent)
                    && view == parent.getChildAt(position)
        }
    }
}