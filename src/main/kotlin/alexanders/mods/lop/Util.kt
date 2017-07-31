package alexanders.mods.lop

import de.ellpeck.rockbottom.api.construction.resource.ResUseInfo
import java.util.*

fun propertiesOf(vararg pairs: Pair<String, String>): Properties {
    val p = Properties()
    for ((first, second) in pairs)
        p.setProperty(first, second)
    return p
}

infix fun Int.of(name: String) = ResUseInfo(name, this)
