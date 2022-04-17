package dev.rodosteam.questtime.quest.repo.content

import dev.rodosteam.questtime.quest.model.QuestContent
import dev.rodosteam.questtime.quest.repo.meta.QuestMetaRepoJson
import dev.rodosteam.questtime.utils.InternalStorage
import org.junit.Assert
import org.junit.Test
import java.io.File

class QuestContentRepoJsonTest {
    companion object {
        private const val TEST_ID = -1
        private const val TEST_FILES_DIR = "../test_files"
        private val FIRST = QuestContent.Page.Id(1)
    }

    @Test
    fun reading_isCorrect() {
        // TODO: Почему-то на компе JSONTokener внутри MetaRepo не работает и возвращает null, хотя json файл читается корректно
        // Поэтому этот тест крашится
        val intStorage = InternalStorage(File(TEST_FILES_DIR))
        val questMetaRepo = QuestMetaRepoJson(intStorage)
        val questContentRepo = QuestContentRepoJson(questMetaRepo, intStorage)
        val quest = questContentRepo.findById(TEST_ID)
        Assert.assertNotNull(quest)
        Assert.assertNotNull(quest?.pages)
        Assert.assertEquals(quest?.pages?.size, 3)

        Assert.assertNotNull(quest?.pages?.get(FIRST))
        Assert.assertNotNull(quest?.pages?.get(FIRST)?.choices)
        Assert.assertEquals(quest?.pages?.get(FIRST)?.choices?.size, 2)
    }
}