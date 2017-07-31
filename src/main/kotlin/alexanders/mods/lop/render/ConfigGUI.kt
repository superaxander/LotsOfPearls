package alexanders.mods.lop.render

import alexanders.mods.lop.LOP
import alexanders.mods.lop.init.Resources
import de.ellpeck.rockbottom.api.IGameInstance
import de.ellpeck.rockbottom.api.RockBottomAPI
import de.ellpeck.rockbottom.api.assets.IAssetManager
import de.ellpeck.rockbottom.api.gui.Gui
import de.ellpeck.rockbottom.api.gui.component.ComponentButton
import de.ellpeck.rockbottom.api.util.reg.IResourceName
import org.newdawn.slick.Graphics
import java.util.*


class ConfigGUI(parent: Gui) : Gui(384, 136, parent) {

    var currentID = 0
    val configuration = LOP.instance.configManager
    val buttonList = ArrayList<String>(configuration.properties.size)
    val rows = ArrayList<Array<ComponentButton>>()
    var row = 0
    lateinit var upButton: ComponentButton
    lateinit var downButton: ComponentButton
    lateinit var backButton: ComponentButton

    override fun initGui(game: IGameInstance) {
        super.initGui(game)
        rows.clear()
        buttonList.clear()
        currentID = 0
        val assetManager = game.assetManager

        var currentRow = ArrayList<ComponentButton>(2)

        for ((key, value) in configuration.properties) {
            if (currentRow.size >= 2) {
                rows.add(currentRow.toTypedArray())
                currentRow = ArrayList<ComponentButton>(2)
            }
            buttonList.add(key as String)
            val buttonText = assetManager.localize(RockBottomAPI.createRes(LOP.instance, "button.$key")) + ": $value"
            //println(currentRow.size)
            if (currentRow.size == 1) // Even?
                currentRow.add(ComponentButton(this, currentID++, guiLeft, guiTop + 30 * rows.size, 180, 16, buttonText))
            else
                currentRow.add(ComponentButton(this, currentID++, guiLeft + 184, guiTop + 30 * rows.size, 180, 16, buttonText))
        }
        rows.add(currentRow.toTypedArray())
        upButton = ComponentButton(this, -2, guiLeft + 374, guiTop + 3, 10, 10, "")
        downButton = ComponentButton(this, -3, guiLeft + 374, guiTop + 93, 10, 10, "")
        backButton = ComponentButton(this, -1, guiLeft + 92, guiTop + 120, 190, 16, assetManager.localize(RockBottomAPI.createRes(RockBottomAPI.getModLoader().getMod("rockbottom"), "button.back")))
        updateComponentList()
    }

    fun updateComponentList() {
        components.clear()
        outer_loop@ for (i in row..row + 3) {
            for (j in 0..1) {
                try {
                    val c = rows[i][j]
                    c.y = guiTop + 30 * (i - row)
                    //System.out.println("y:"+c.y);
                    components.add(c)
                } catch (ignored: IndexOutOfBoundsException) {
                    break@outer_loop
                    //ignored.printStackTrace();
                }

            }
        }
        components.add(upButton)
        components.add(downButton)
        components.add(backButton)

    }

    override fun renderOverlay(game: IGameInstance, manager: IAssetManager, g: Graphics) {
        super.renderOverlay(game, manager, g)
        manager.getTexture(Resources.UP_ARROW).draw(upButton.x.toFloat(), upButton.y.toFloat(), upButton.sizeX.toFloat(), upButton.sizeY.toFloat())
        manager.getTexture(Resources.DOWN_ARROW).draw(downButton.x.toFloat(), downButton.y.toFloat(), downButton.sizeX.toFloat(), downButton.sizeY.toFloat())
    }

    private fun scrollDown() {
        println(rows.size)
        if (row + 1 < rows.size - 3) {
            row++
            updateComponentList()
        }
    }

    private fun scrollUp() {
        if (row - 1 >= 0) {
            row--
            updateComponentList()
        }
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
            -2 -> {
                game.scheduleAction {
                    scrollUp()
                    return@scheduleAction true
                }
            }
            -3 -> {
                game.scheduleAction {
                    scrollDown()
                    return@scheduleAction true
                }
            }
            else -> {
                if (button < buttonList.size && button >= 0) {
                    val buttonKey = buttonList[button]
                    val property = configuration.properties.getProperty(buttonKey)
                    if (property is String) {
                        var bool = property.toBoolean()
                        bool = !bool
                        configuration.properties.setProperty(buttonKey, bool.toString())
                        rows.forEach {
                            it.forEach {
                                if (it.id == button) {
                                    it.setText(assetManager.localize(RockBottomAPI.createRes(LOP.instance, "button.$buttonKey")) + ": " + bool)
                                    return true
                                }
                            }
                        }
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

    override fun getName(): IResourceName = RockBottomAPI.createRes(LOP.instance, "mod_config")
}
