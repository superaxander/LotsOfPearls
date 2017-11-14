package alexanders.mods.lop.render

import alexanders.mods.lop.LOP
import alexanders.mods.lop.init.Resources
import de.ellpeck.rockbottom.api.IGameInstance
import de.ellpeck.rockbottom.api.IGraphics
import de.ellpeck.rockbottom.api.RockBottomAPI
import de.ellpeck.rockbottom.api.assets.IAssetManager
import de.ellpeck.rockbottom.api.gui.Gui
import de.ellpeck.rockbottom.api.gui.component.ComponentButton
import de.ellpeck.rockbottom.api.util.reg.IResourceName
import java.util.*
import java.util.function.Supplier


class ConfigGUI(parent: Gui) : Gui(384, 136, parent) {
    var currentID = 0
    val configuration = LOP.instance.configManager
    val buttonList = ArrayList<String>(configuration.properties.size)
    val rows = ArrayList<Array<ToggleButton>>()
    var row = 0
    lateinit var upButton: ComponentButton
    lateinit var downButton: ComponentButton
    lateinit var backButton: ComponentButton

    override fun init(game: IGameInstance) {
        super.init(game)
        rows.clear()
        buttonList.clear()
        currentID = 0
        val assetManager = game.assetManager

        var currentRow = ArrayList<ToggleButton>(2)

        for ((key, value) in configuration.properties) {
            if (currentRow.size >= 2) {
                rows.add(currentRow.toTypedArray())
                currentRow = ArrayList(2)
            }
            buttonList.add(key as String)
            val buttonText = assetManager.localize(RockBottomAPI.createRes(LOP.instance, "button.$key")) + ": $value"
            //println(currentRow.size)
            if (currentRow.size == 1) // Even?
                currentRow.add(ToggleButton(currentID++, this, 0, 30 * rows.size, 180, 16, buttonText))
            else
                currentRow.add(ToggleButton(currentID++, this, 184, 30 * rows.size, 180, 16, buttonText))
        }
        rows.add(currentRow.toTypedArray())
        upButton = ComponentButton(this, 374, 3, 10, 10, Supplier {
            game.enqueueAction({ _, _ ->
                scrollUp()
            }, null)
            return@Supplier true
        }, "")
        downButton = ComponentButton(this, 374, 93, 10, 10, Supplier {
            game.enqueueAction({ _, _ ->
                scrollDown()
            }, null)
            return@Supplier true
        }, "")
        backButton = ComponentButton(this, 92, 120, 190, 16, Supplier {
            game.enqueueAction({ gameInstance, _ ->
                gameInstance.guiManager.openGui(parent)
            }, null)
            return@Supplier true
        }, assetManager.localize(RockBottomAPI.createRes(RockBottomAPI.getModLoader().getMod("rockbottom"), "button.back")))
        updateComponentList()
    }

    fun updateComponentList() {
        components.clear()
        outer_loop@ for (i in row..row + 3) {
            for (j in 0..1) {
                try {
                    val c = ToggleButton(rows[i][j], 30 * (i - row))
                    components.add(c)
                } catch (ignored: IndexOutOfBoundsException) {
                    break@outer_loop
                }

            }
        }
        components.add(upButton)
        components.add(downButton)
        components.add(backButton)

    }

    override fun renderOverlay(game: IGameInstance, manager: IAssetManager, g: IGraphics) {
        super.renderOverlay(game, manager, g)
        manager.getTexture(Resources.UP_ARROW).draw(upButton.x.toFloat(), upButton.y.toFloat(), 10f, 10f)
        manager.getTexture(Resources.DOWN_ARROW).draw(downButton.x.toFloat(), downButton.y.toFloat(), 10f, 10f)
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

    override fun onClosed(game: IGameInstance?) {
        configuration.save()
    }

    override fun getName(): IResourceName = RockBottomAPI.createRes(LOP.instance, "mod_config")
}
