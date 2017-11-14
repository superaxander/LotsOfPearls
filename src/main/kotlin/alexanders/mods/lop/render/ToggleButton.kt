package alexanders.mods.lop.render

import alexanders.mods.lop.LOP
import de.ellpeck.rockbottom.api.RockBottomAPI
import de.ellpeck.rockbottom.api.gui.component.ComponentButton
import java.util.function.Supplier


class ToggleButton(val id: Int, val cfg_gui: ConfigGUI, x: Int, y: Int, sizeX: Int, sizeY: Int, text: String) : ComponentButton(cfg_gui, x, y, sizeX, sizeY, Supplier {
    val buttonKey = cfg_gui.buttonList[id]
    val property = cfg_gui.configuration.properties.getProperty(buttonKey)
    if (property is String) {
        var bool = property.toBoolean()
        bool = !bool
        cfg_gui.configuration.properties.setProperty(buttonKey, bool.toString())
        cfg_gui.rows.forEach {
            it.forEach {
                if (it.id == id) {
                    it.setText(RockBottomAPI.getGame().assetManager.localize(RockBottomAPI.createRes(LOP.instance, "button.$buttonKey")) + ": " + bool)
                    return@Supplier true
                }
            }
        }
        return@Supplier true
    }
    return@Supplier false
}, text) {
    constructor(other: ToggleButton, y: Int) : this(other.id, other.cfg_gui, other.x, y, other.width, other.height, other.text)
}
