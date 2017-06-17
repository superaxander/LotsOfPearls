package alexanders.mods.lop

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.file.Files


class ConfigurationManager {
    val properties = propertiesOf(
            "pearl" to "true",
            "bouncy" to "true",
            "rideable" to "true",
            "mining" to "true",
            "spiky" to "true",
            "spawn" to "true",
            "waypoint" to "true")

    val file = File("./rockbottom/config/lop.properties")

    init {
        //println(properties)
        if (file.exists()) {
            load()
        } else {
            val directory = File("./rockbottom/config/")
            if (!directory.exists())
                Files.createDirectory(directory.toPath())
        }
        save()
       //println(properties)
    }

    fun load() {
        val inStream = FileInputStream(file)
        properties.loadFromXML(FileInputStream(file))
        inStream.close()
    }

    fun save() {
        val outStream = FileOutputStream(file)
        properties.storeToXML(outStream, null)
        outStream.close()
    }

    fun isEnabled(key: String): Boolean {
        return properties.getProperty(key).toBoolean()
    }
}
