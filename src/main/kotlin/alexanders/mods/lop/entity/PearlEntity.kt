package alexanders.mods.lop.entity

import alexanders.mods.lop.LOP
import alexanders.mods.lop.net.EntityPostionUpdatePacket
import de.ellpeck.rockbottom.api.IGameInstance
import de.ellpeck.rockbottom.api.RockBottomAPI
import de.ellpeck.rockbottom.api.data.set.DataSet
import de.ellpeck.rockbottom.api.entity.EntityItem
import de.ellpeck.rockbottom.api.item.ItemInstance
import de.ellpeck.rockbottom.api.world.IWorld
import org.newdawn.slick.geom.Vector2f
import java.util.*


class PearlEntity(world: IWorld, player: UUID? = null, mouseDirection: Vector2f = Vector2f()) : EntityItem(world, ItemInstance(RockBottomAPI.ITEM_REGISTRY.get(LOP.instance.PEARL_RESOURCE))) {
    init {
        if (this.additionalData == null) {
            this.additionalData = DataSet()
            if (player != null)
                this.additionalData.addUniqueId("playerUUID", player)
        }
        this.item.amount = -1
        this.motionX = 0.5 * mouseDirection.x
        this.motionY = -0.5 * mouseDirection.y
        if (player != null) {
            val ePlayer = world.getEntity(player)
            this.setPos(ePlayer.x, ePlayer.y + 1.5)
        } else if (this.additionalData.getUniqueId("playerUUID") != null) {
            val ePlayer = world.getEntity(this.additionalData.getUniqueId("playerUUID"))
            this.setPos(ePlayer.x, ePlayer.y)
        }
        println("$x , $y")
    }

    constructor(world2: IWorld) : this(world = world2)

    override fun update(game: IGameInstance?) {
        super.update(game)
        if (collidedVert || collidedHor) {
            val uuid = this.additionalData.getUniqueId("playerUUID")
            println("$x , $y")
            if (uuid != null) {
                val e = world.getEntity(uuid)
                if(e != null) {
                    e.setPos(this.x, this.y + 1.2f)
                    if (RockBottomAPI.getNet().isServer)
                        RockBottomAPI.getNet().sendToAllPlayers(world, EntityPostionUpdatePacket(uuid, x, y + 1.2f))
                }
            }
            this.dead = true
        }
    }

    override fun canPickUp(): Boolean {
        return false
    }
}