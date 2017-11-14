package alexanders.mods.lop.item

import alexanders.mods.lop.init.Resources.SPAWN_PEARL_DESC_RESOURCE
import alexanders.mods.lop.init.Resources.SPAWN_PEARL_RESOURCE
import alexanders.mods.lop.net.CooldownUpdatePacket
import alexanders.mods.lop.net.EntityPositionUpdatePacket
import alexanders.mods.lop.render.PearlItemRenderer
import alexanders.mods.lop.render.TeleportationParticle
import de.ellpeck.rockbottom.api.RockBottomAPI
import de.ellpeck.rockbottom.api.assets.IAssetManager
import de.ellpeck.rockbottom.api.data.set.DataSet
import de.ellpeck.rockbottom.api.entity.player.AbstractEntityPlayer
import de.ellpeck.rockbottom.api.item.ItemBasic
import de.ellpeck.rockbottom.api.item.ItemInstance
import de.ellpeck.rockbottom.api.render.item.IItemRenderer
import de.ellpeck.rockbottom.api.world.layer.TileLayer
import org.newdawn.slick.geom.Vector2f


class SpawnPearlItem() : ItemBasic(SPAWN_PEARL_RESOURCE), Useable {
    val renderer = PearlItemRenderer(SPAWN_PEARL_RESOURCE)
    override fun use(itemInstance: ItemInstance, mouseDirection: Vector2f, player: AbstractEntityPlayer) {
        if (itemInstance.additionalData == null) {
            itemInstance.additionalData = DataSet()
            itemInstance.additionalData.addInt("cooldown", 0)
        }
        val cooldown = itemInstance.additionalData.getInt("cooldown")
        if (cooldown <= 0) {
            player.setPos(player.world.spawnX + .5, player.world.getLowestAirUpwards(TileLayer.MAIN, player.world.spawnX, 0) + .5)
            if (RockBottomAPI.getNet().isServer)
                RockBottomAPI.getNet().sendToAllPlayers(player.world, EntityPositionUpdatePacket(player.uniqueId, player.x, player.y))
            for (i in 0..20) RockBottomAPI.getGame().particleManager.addParticle(TeleportationParticle(world = player.world, x = player.x, y = player.y, maxLife = 60))
            itemInstance.additionalData.addInt("cooldown", 60)
            //if (itemInstance.removeAmount(1).amount <= 0)
            //    player.inv[player.selectedSlot] = null
            player.sendPacket(CooldownUpdatePacket(60))
        }
    }

    override fun describeItem(manager: IAssetManager, instance: ItemInstance, desc: MutableList<String>, isAdvanced: Boolean) {
        super.describeItem(manager, instance, desc, isAdvanced)
        desc.add(manager.localize(SPAWN_PEARL_DESC_RESOURCE))
    }

    override fun getRenderer(): IItemRenderer<*> {
        return renderer
    }

    override fun getMaxAmount(): Int {
        return 1
    }
}

