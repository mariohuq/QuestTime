package dev.rodosteam.questtime.quest.repo.meta

import android.content.Context
import android.content.res.Resources
import dev.rodosteam.questtime.R
import dev.rodosteam.questtime.quest.model.QuestMeta
import dev.rodosteam.questtime.utils.InternalStorage
import dev.rodosteam.questtime.utils.asIterableOfJSONObjects
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener

class QuestMetaRepoJson(intStorage: InternalStorage) : QuestMetaRepoBase() {
    companion object {
        // Json primitives
        private const val QUESTS_META_ARRAY = "questsMeta"
        private const val ID = "id"
        private const val TITLE = "title"
        private const val DESCRIPTION = "description"
        private const val AUTHOR = "author"
        private const val DOWNLOADS = "downloads"
        private const val FAVORITES = "favorites"
        private const val CREATED = "created"
        private const val FILENAME = "filename"
        private const val ICON_FILENAME = "iconFilename"

        private val IMAGES_RESOURCES = mapOf(
            R.drawable.test_icon to "test_icon.png",
            R.drawable.hobbit_lego_icon to "hobbit_lego_icon.png"
        )
        private val JSON_RESOURCES = mapOf(
            R.raw.quest_information to "quest_information.json",
            R.raw.test_quest to "test_quest.json", R.raw.hobbit to "hobbit.json"
        )

        private const val PATH_IN_INTERNAL_ST = "quests_library"
        private const val META_INFO_FILENAME = "$PATH_IN_INTERNAL_ST/quest_information.json"
        private const val IMAGES_LOCATION = "$PATH_IN_INTERNAL_ST/images"

        /**
         * This function init meta and content repo in internal storage from resources.
         * If it was init already, nothing will happens
         */
        fun initRes(resources: Resources, applicationContext: Context) {
            // Copy quest resources to internal storage
            val intStorage = InternalStorage(applicationContext.filesDir)
            if (!intStorage.exists(PATH_IN_INTERNAL_ST)) {
                intStorage.copyFromRawResources(
                    resources,
                    JSON_RESOURCES,
                    PATH_IN_INTERNAL_ST
                )
                intStorage.copyFromImageResources(resources, IMAGES_RESOURCES, IMAGES_LOCATION)
            }
        }

        private fun readMetas(
            jsonMetas: JSONArray
        ): Iterable<QuestMeta> =
            jsonMetas.asIterableOfJSONObjects().map { currentJson ->
                val id = currentJson.getInt(ID)
                QuestMeta(
                    id,
                    currentJson.getString(TITLE),
                    currentJson.getString(DESCRIPTION),
                    currentJson.getString(AUTHOR),
                    currentJson.getInt(DOWNLOADS),
                    currentJson.getInt(FAVORITES),
                    currentJson.getLong(CREATED),
                    currentJson.getString(ICON_FILENAME),
                    currentJson.getString(FILENAME)
                )
            }
    }

    init {
        val jsonObject = JSONTokener(intStorage.read(META_INFO_FILENAME)).nextValue() as JSONObject
        val jsonMetas = jsonObject.getJSONArray(QUESTS_META_ARRAY)
        addAll(readMetas(jsonMetas))
    }
}
