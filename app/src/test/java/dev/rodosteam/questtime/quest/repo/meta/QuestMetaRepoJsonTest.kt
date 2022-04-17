package dev.rodosteam.questtime.quest.repo.meta

import dev.rodosteam.questtime.utils.InternalStorage
import org.junit.Assert
import org.junit.Test
import java.io.File

class QuestMetaRepoJsonTest {
    companion object {
        private const val TEST_FILES_DIR = "./test_files"
        private const val TEST_QUEST_ID = -1
        private const val TEST_QUEST_TITLE = "Test quest"
        private const val TEST_QUEST_DESCRIPTION = "Test quest description"
        private const val TEST_QUEST_AUTHOR = "Lev Saskov"
        private const val TEST_QUEST_DOWNLOADS = 0
        private const val TEST_QUEST_FAVORITES = 0
        private const val TEST_QUEST_CREATED = 0L
        private const val TEST_QUEST_FILENAME = "test_quest.json"
    }

    @Test
    fun reading_isCorrect() {
        // TODO: Почему-то на компе JSONTokener внутри MetaRepo не работает и возвращает null, хотя json файл читается корректно
        // Поэтому этот тест крашитсяЫ
        val questMetaRepo = QuestMetaRepoJson(InternalStorage(File(TEST_FILES_DIR)))
        val meta = questMetaRepo.findById(TEST_QUEST_ID)
        Assert.assertNotNull(meta)
        Assert.assertEquals(TEST_QUEST_TITLE, meta?.title)
        Assert.assertEquals(TEST_QUEST_DESCRIPTION, meta?.description)
        Assert.assertEquals(TEST_QUEST_AUTHOR, meta?.author)
        Assert.assertEquals(TEST_QUEST_DOWNLOADS, meta?.downloads)
        Assert.assertEquals(TEST_QUEST_FAVORITES, meta?.favorites)
        Assert.assertEquals(TEST_QUEST_CREATED, meta?.created)
        Assert.assertEquals(TEST_QUEST_FILENAME, meta?.filename)
    }
}