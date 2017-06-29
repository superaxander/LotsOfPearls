package alexanders.mods.lop.entity

import alexanders.mods.lop.init.Items.spikyPearlItem
import alexanders.mods.lop.net.BloodPacket
import alexanders.mods.lop.render.BloodParticle
import alexanders.mods.lop.render.PearlParticle
import alexanders.mods.lop.render.TeleportationParticle
import de.ellpeck.rockbottom.api.IGameInstance
import de.ellpeck.rockbottom.api.RockBottomAPI
import de.ellpeck.rockbottom.api.data.set.DataSet
import de.ellpeck.rockbottom.api.entity.EntityItem
import de.ellpeck.rockbottom.api.entity.player.AbstractEntityPlayer
import de.ellpeck.rockbottom.api.item.ItemInstance
import de.ellpeck.rockbottom.api.world.IWorld
import org.newdawn.slick.geom.Vector2f
import java.util.*


class SpikyPearlEntity(world: IWorld, player: UUID? = null, mouseDirection: Vector2f = Vector2f()) : EntityItem(world, ItemInstance(spikyPearlItem)) {
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
        val collidingPlayers = world.getEntities(boundingBox.copy().add(x, y), AbstractEntityPlayer::class.java)
        for (player in collidingPlayers) {
            val uuid = this.additionalData.getUniqueId("playerUUID")
            if (uuid != null) {
                if (player.uniqueId != uuid) {
                    player.health = player.health - 1
                    if (RockBottomAPI.getNet().isServer)
                        RockBottomAPI.getNet().sendToAllPlayers(world, BloodPacket(x, y))
                    if (!game.isDedicatedServer)
                        for (i in 0..20) game.particleManager.addParticle(BloodParticle(world = game.world, x = x, y = y, maxLife = 40))
                    this.kill()

                }
            }
        }
        if (collidingPlayers.isEmpty())
            if (!game.isDedicatedServer)
                game.particleManager.addParticle(TeleportationParticle(world = game.world, x = x, y = y, motionX = motionX / 2 * PearlParticle.randomSignedDouble(), maxLife = 10))
        if (collidedVert || collidedHor) {
            this.kill()
        }
    }

    override fun canPickUp(): Boolean {
        return false
    }
}
