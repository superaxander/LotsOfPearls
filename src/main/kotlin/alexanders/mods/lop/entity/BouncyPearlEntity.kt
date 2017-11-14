package alexanders.mods.lop.entity

import alexanders.mods.lop.init.Items.bouncyPearlItem
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

//TODO: fix all the errors
//TODO: worldgen

class BouncyPearlEntity(world: IWorld, player: UUID? = null, mouseDirection: Vector2f = Vector2f()) : EntityItem(world, ItemInstance(bouncyPearlItem)) {
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
        applyMotion()
        if (!game.isDedicatedServer)
            game.particleManager.addParticle(TeleportationParticle(world = game.world, x = x, y = y, motionX = motionX / 2 * PearlParticle.randomSignedDouble(), maxLife = 10))
        move()
        if (collidedVert || collidedHor) {
            if (this.additionalData.getInt("bounces") >= 3) {
                val uuid = this.additionalData.getUniqueId("playerUUID")
                //println("$x , $y")
                if (uuid != null) {
                    val e = world.getEntity(uuid)
                    if (e != null) {
                        e.setPos(this.x, this.y + 1.2f)
                        if (RockBottomAPI.getNet().isServer) {
                            RockBottomAPI.getNet().sendToAllPlayers(world, EntityPositionUpdatePacket(uuid, x, y + 1.2f))
                        }
                        if (!game.isDedicatedServer)
                            for (i in 0..20) game.particleManager.addParticle(TeleportationParticle(world = game.world, x = x, y = y, maxLife = 60))

                    }
                }
                this.kill()
            } else {
                if (!this.additionalData.hasKey("bounces"))
                    this.additionalData.addInt("bounces", 1)
                this.additionalData.addInt("bounces", this.additionalData.getInt("bounces") + 1)
                if (this.collidedHor)
                    this.motionX = -(this.motionX * .85f)
                if (this.collidedVert)
                    this.motionY = -(this.motionY * .85f)
            }
        }
    }

    override fun canPickUp() = false
}
