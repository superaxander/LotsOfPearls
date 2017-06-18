package alexanders.mods.lop.entity

import alexanders.mods.lop.init.Items.rideablePearlItem
import alexanders.mods.lop.net.EntityPositionUpdatePacket
import alexanders.mods.lop.render.PearlParticle
import alexanders.mods.lop.render.TeleportationParticle
import de.ellpeck.rockbottom.api.IGameInstance
import de.ellpeck.rockbottom.api.RockBottomAPI
import de.ellpeck.rockbottom.api.data.set.DataSet
import de.ellpeck.rockbottom.api.entity.EntityItem
import de.ellpeck.rockbottom.api.item.ItemInstance
import de.ellpeck.rockbottom.api.world.IWorld
import org.newdawn.slick.geom.Vector2f
import java.util.*


class RideablePearlEntity(world: IWorld, player: UUID? = null, mouseDirection: Vector2f = Vector2f()) : EntityItem(world, ItemInstance(rideablePearlItem)) {
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
        //println("$x , $y")
    }

    constructor(world2: IWorld) : this(world = world2)

    override fun update(game: IGameInstance) {
        super.update(game)
        val uuid = this.additionalData.getUniqueId("playerUUID")
        game.particleManager.addParticle(TeleportationParticle(world = game.world, x = x, y = y, motionX = motionX / 2 * PearlParticle.randomSignedDouble(), maxLife = 10))
        if (uuid != null) {
            world.getEntity(uuid)?.setPos(x, y + .8f)
            world.getEntity(uuid)?.fallAmount = 0
            if (RockBottomAPI.getNet().isServer) {
                RockBottomAPI.getNet().sendToAllPlayers(world, EntityPositionUpdatePacket(uuid, x, y + .8f, true))
            }
        }
        if (collidedVert || collidedHor) {
            //println("$x , $y")
            if (uuid != null) {
                val e = world.getEntity(uuid)
                if (e != null) {
                    e.setPos(this.x, this.y + 1.2f)
                    if (RockBottomAPI.getNet().isServer) {
                        RockBottomAPI.getNet().sendToAllPlayers(world, EntityPositionUpdatePacket(uuid, x, y + 1.2f))
                    }
                    for (i in 0..20) game.particleManager.addParticle(TeleportationParticle(world = game.world, x = x, y = y, maxLife = 60))
                    this.kill()
                }
            }
        }
    }

    override fun canPickUp(): Boolean {
        return false
    }
}
