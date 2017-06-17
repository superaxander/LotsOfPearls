package alexanders.mods.lop.render

import alexanders.mods.lop.LOP
import de.ellpeck.rockbottom.api.IGameInstance
import de.ellpeck.rockbottom.api.RockBottomAPI
import de.ellpeck.rockbottom.api.gui.Gui
import de.ellpeck.rockbottom.api.gui.component.ComponentButton


class ConfigGUI(parent: Gui) : Gui(374, 132, parent) {

    var currentID = 0
    val configuration = LOP.instance.configManager
    val buttonList = ArrayList<String>(configuration.properties.size)

    override fun initGui(game: IGameInstance) {
        super.initGui(game)
        val assetManager = game.assetManager
        var row = 0
        var even = true // yes I know 0 isn't even!

        for ((key, value) in configuration.properties) {//TODO: do height bounds checking and add a scrollbar
            buttonList.add(currentID, key as String)
            val buttonText = assetManager.localize(RockBottomAPI.createRes(LOP.instance, "button.$key")) + ": $value"
            if (even)
                components.add(ComponentButton(this, currentID, guiLeft, guiTop + 30 * row, 190, 16, buttonText))
            else {
                components.add(ComponentButton(this, currentID, guiLeft + 194, guiTop + 30 * row, 190, 16, buttonText))
                row++
            }
            currentID++
            even = !even
        }

        components.add(ComponentButton(this, -1, guiLeft + 92, guiTop + 116, 190, 16, assetManager.localize(RockBottomAPI.createRes(RockBottomAPI.getModLoader().getMod("rockbottom"), "button.back"))))
    }

    override fun onButtonActivated(game: IGameInstance, button: Int): Boolean {
        val guiManager = game.guiManager
        val assetManager = game.assetManager
        //println(button)
        when (button) {
            -1 -> {
                guiManager.openGui(parent)
                return true
            }
            else -> {
                if (button < buttonList.size && button >= 0) {
                    val buttonKey = buttonList[button]
                    //println(buttonKey)
                    val property = configuration.properties.getProperty(buttonKey)
                    //println(property)
                    if (property is String) {
                        var bool = property.toBoolean()
                        bool = !bool
                        //println(bool)
                        configuration.properties.setProperty(buttonKey, bool.toString())
                        val button_component = components[button]
                        components[button] = ComponentButton(this, button, button_component.x, button_component.y, button_component.sizeX, button_component.sizeY, assetManager.localize(RockBottomAPI.createRes(LOP.instance, "button.$buttonKey")) + ": " + bool)
                        return true
                    }
                }
            }
        }
        return false
    }

    override fun onClosed(game: IGameInstance?) {
        configuration.save()
    }
}
