package alexanders.mods.lop.event

import alexanders.mods.lop.net.CooldownUpdatePacket
import alexanders.mods.lop.net.ItemUsePacket
import de.ellpeck.rockbottom.api.RockBottomAPI
import de.ellpeck.rockbottom.api.entity.player.AbstractEntityPlayer
import de.ellpeck.rockbottom.api.event.EventResult
import de.ellpeck.rockbottom.api.event.IEventListener
import de.ellpeck.rockbottom.api.event.impl.EntityTickEvent
import org.lwjgl.opengl.Display
import org.newdawn.slick.Input
import org.newdawn.slick.MouseListener
import org.newdawn.slick.geom.Vector2f


class ItemUsageListener : MouseListener, IEventListener<EntityTickEvent> {
    override fun listen(result: EventResult, event: EntityTickEvent): EventResult {
        val entity = event.entity
        if (entity is AbstractEntityPlayer && entity.inv[entity.selectedSlot] != null) {
            if (entity.inv[entity.selectedSlot].additionalData != null) {
                val cooldown = entity.inv[entity.selectedSlot].additionalData.getInt("cooldown")
                if (cooldown > 0) {
                    entity.inv[entity.selectedSlot].additionalData.addInt("cooldown", cooldown - 1)
                    //println("set cooldown to ${cooldown -1}")
                    if (RockBottomAPI.getNet().isServer)
                        entity.sendPacket(CooldownUpdatePacket(cooldown - 1))
                }
            }
        }
        return result
    }

    override fun mouseMoved(oldx: Int, oldy: Int, newx: Int, newy: Int) {
    }

    override fun inputStarted() {
    }

    override fun setInput(input: Input?) {
    }

    override fun isAcceptingInput() = true

    override fun mouseClicked(button: Int, x: Int, y: Int, clickCount: Int) {
        if (RockBottomAPI.getGame().guiManager.gui == null && button == 1) {
            val net = RockBottomAPI.getNet()
            val game = RockBottomAPI.getGame()
            //println("${net.isClient} ${net.isServer}")

            //if (net.isClient || !net.isActive) {
            // We are on a client so we have access to input
            val player = RockBottomAPI.getGame().player

            if (player.inv[player.selectedSlot] != null) {

                if (net.isClient) {
                    net.sendToServer(ItemUsePacket(angle(x, y), player.uniqueId, game.input.isKeyDown(Input.KEY_LSHIFT)))
                } else {
                    ItemUsePacket(angle(x, y), player.uniqueId, game.input.isKeyDown(Input.KEY_LSHIFT)).handle(game, null)
                }
            }
            //}
        }
    }

    override fun mouseWheelMoved(change: Int) {
    }

    override fun mouseReleased(button: Int, x: Int, y: Int) {
    }

    override fun inputEnded() {
    }

    override fun mouseDragged(oldx: Int, oldy: Int, newx: Int, newy: Int) {
    }

    override fun mousePressed(button: Int, x: Int, y: Int) {
    }

    private fun angle(mouseX: Int, mouseY: Int): Vector2f {
        val w = Display.getWidth()
        val h = Display.getHeight()
        val radians = Math.atan2(mouseY - (h / 2.0), mouseX - (w / 2.0))
        return Vector2f(Math.cos(radians).toFloat(), Math.sin(radians).toFloat())
    }
}
