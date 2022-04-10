package dev.rodosteam.questtime.utils

import android.content.res.Resources
import java.io.File

class InternalStorage(private val filesDir: File) {
    fun save(dirPath: String, filename: String, content: String) {
        val file = fileWithDirectoryAssurance(filesDir.resolve(dirPath).toString(), filename)
        file.outputStream().bufferedWriter()
            .use { w -> w.write(content) }
    }

    fun read(path: String): String =
        filesDir.resolve(path).inputStream().bufferedReader().use { r ->
            r.readText()
        }

    fun copyFromResources(
        resources: Resources,
        resourcesToCopy: Map<Int, String>,
        pathTo: String
    ) {
        for (entry in resourcesToCopy) {
            resources.openRawResource(entry.key).bufferedReader().use { reader ->
                save(pathTo, entry.value, reader.readText())
            }
        }
    }

    fun exists(path: String): Boolean {
        return filesDir.resolve(path).exists()
    }

    /** Creates parent directories if necessary. Then returns file  */
    private fun fileWithDirectoryAssurance(directory: String, filename: String): File {
        val dir = File(directory)
        if (!dir.exists()) dir.mkdirs()
        return File("$directory/$filename")
    }
}