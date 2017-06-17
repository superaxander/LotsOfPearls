package alexanders.mods.lop

import java.util.*

fun propertiesOf(vararg pairs: Pair<String, String>): Properties {
    val p = Properties()
    for ((first, second) in pairs)
        p.setProperty(first, second)
    return p
}
