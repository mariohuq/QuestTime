package dev.rodosteam.questtime.screen.common

import androidx.appcompat.R
import androidx.test.espresso.Espresso
import androidx.test.espresso.matcher.ViewMatchers

class QuestContentScreen {
    val actionBarText =
        Espresso.onView(ViewMatchers.withParent(ViewMatchers.withId(R.id.action_bar)))
}