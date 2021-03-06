package alexanders.mods.lop.item

import alexanders.mods.lop.entity.BouncyPearlEntity
import alexanders.mods.lop.init.Resources.BOUNCY_PEARL_DESC_RESOURCE
import alexanders.mods.lop.init.Resources.BOUNCY_PEARL_RESOURCE
import alexanders.mods.lop.net.CooldownUpdatePacket
import alexanders.mods.lop.render.PearlItemRenderer
import de.ellpeck.rockbottom.api.assets.IAssetManager
import de.ellpeck.rockbottom.api.data.set.DataSet
import de.ellpeck.rockbottom.api.entity.player.AbstractEntityPlayer
import de.ellpeck.rockbottom.api.item.ItemBasic
import de.ellpeck.rockbottom.api.item.ItemInstance
import de.ellpeck.rockbottom.api.render.item.IItemRenderer
import org.newdawn.slick.geom.Vector2f


class BouncyPearlItem() : ItemBasic(BOUNCY_PEARL_RESOURCE), Useable {
    val renderer = PearlItemRenderer(BOUNCY_PEARL_RESOURCE)
    override fun use(itemInstance: ItemInstance, mouseDirection: Vector2f, player: AbstractEntityPlayer) {
        if (itemInstance.additionalData == null) {
            itemInstance.additionalData = DataSet()
            itemInstance.additionalData.addInt("cooldown", 0)
        }
        val cooldown = itemInstance.additionalData.getInt("cooldown")
        if (cooldown <= 0) {
            val pearlEntity = BouncyPearlEntity(player.world, player.uniqueId, mouseDirection)
            player.world.addEntity(pearlEntity)
            itemInstance.additionalData.addInt("cooldown", 60)
            if (itemInstance.removeAmount(1).amount <= 0)
                player.inv[player.selectedSlot] = null
            player.sendPacket(CooldownUpdatePacket(60))
        }
    }

    override fun describeItem(manager: IAssetManager, instance: ItemInstance, desc: MutableList<String>, isAdvanced: Boolean) {
        super.describeItem(manager, instance, desc, isAdvanced)
        desc.add(manager.localize(BOUNCY_PEARL_DESC_RESOURCE))
    }

    override fun getRenderer(): IItemRenderer<*> {
        return renderer
    }
}
