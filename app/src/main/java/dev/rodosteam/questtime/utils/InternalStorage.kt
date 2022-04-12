package dev.rodosteam.questtime.utils

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Bitmap
import androidx.core.graphics.drawable.toBitmap
import java.io.File

class InternalStorage(private val filesDir: File) {
    companion object {
        private const val COMPRESS_QUALITY = 95
    }

    fun save(dirPath: String, filename: String, content: String) {
        val file = fileWithDirectoryAssurance(filesDir.resolve(dirPath).toString(), filename)
        file.bufferedWriter()
            .use { w -> w.write(content) }
    }

    fun read(path: String): String =
        filesDir.resolve(path).bufferedReader().use { r ->
            r.readText()
        }

    fun copyFromRawResources(
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

    @SuppressLint("UseCompatLoadingForDrawables")
    fun copyFromImageResources(
        resources: Resources,
        resourcesToCopy: Map<Int, String>,
        pathTo: String
    ) {
        val dirStr = filesDir.resolve(pathTo).toString()
        val dir = fileWithDirectoryAssurance(dirStr, dirStr)
        for (entry in resourcesToCopy) {
            dir.resolve(entry.value).outputStream().use { stream ->
                resources.getDrawable(entry.key, null).toBitmap()
                    .compress(Bitmap.CompressFormat.PNG, COMPRESS_QUALITY, stream)
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
        return dir.resolve(filename)
    }
}