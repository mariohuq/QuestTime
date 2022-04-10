package dev.rodosteam.questtime.quest.repo.meta

import dev.rodosteam.questtime.R
import dev.rodosteam.questtime.quest.model.QuestMeta

interface QuestMetaRepo {
    companion object {
        val DEFAULT_RESOURCES =
            mapOf(
                R.raw.quest_information to "quest_information.json",
                R.raw.test_quest to "test_quest.json", R.raw.hobbit to "hobbit.json"
            )

        val PATH_IN_INTERNAL_ST = "quests_library1"
    }

    fun findAll(): List<QuestMeta>
    fun findAllByName(name: String): List<QuestMeta>
    fun findById(id: Int): QuestMeta?
    fun findByName(name: String): QuestMeta?
    fun add(item: QuestMeta): Boolean
    fun remove(id: Int): QuestMeta?
    fun addAll(elements: Iterable<QuestMeta>)
}
