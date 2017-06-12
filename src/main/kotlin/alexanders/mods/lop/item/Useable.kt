package alexanders.mods.lop.item

import de.ellpeck.rockbottom.api.entity.player.AbstractEntityPlayer
import de.ellpeck.rockbottom.api.item.ItemInstance
import org.newdawn.slick.geom.Vector2f


interface Useable {
    fun use(itemInstance: ItemInstance, mouseDirection: Vector2f, player: AbstractEntityPlayer){}
    fun use(itemInstance: ItemInstance, mouseDirection: Vector2f, player: AbstractEntityPlayer, shiftPressed: Boolean) {
        use(itemInstance, mouseDirection, player)
    }
}
