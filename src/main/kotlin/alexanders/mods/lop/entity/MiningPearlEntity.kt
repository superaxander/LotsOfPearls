package alexanders.mods.lop.entity

import alexanders.mods.lop.init.Items.miningPearlItem
import alexanders.mods.lop.render.PearlParticle
import alexanders.mods.lop.render.TeleportationParticle
import de.ellpeck.rockbottom.api.IGameInstance
import de.ellpeck.rockbottom.api.RockBottomAPI
import de.ellpeck.rockbottom.api.data.set.DataSet
import de.ellpeck.rockbottom.api.entity.EntityItem
import de.ellpeck.rockbottom.api.entity.player.AbstractEntityPlayer
import de.ellpeck.rockbottom.api.item.ItemInstance
import de.ellpeck.rockbottom.api.world.IWorld
import de.ellpeck.rockbottom.api.world.layer.TileLayer
import org.newdawn.slick.geom.Vector2f
import java.util.*


class MiningPearlEntity(world: IWorld, player: UUID? = null, mouseDirection: Vector2f = Vector2f()) : EntityItem(world, ItemInstance(miningPearlItem)) {

    init {
        if (this.additionalData == null) {
            this.additionalData = DataSet()
            if (player != null)
                this.additionalData.addUniqueId("playerUUID", player)
            this.additionalData.addInt("tilesBroken", 0)
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
        val recoveryX = motionX
        val recoveryY = motionY
        super.update(game)
        if (!game.isDedicatedServer)
            game.particleManager.addParticle(TeleportationParticle(world = game.world, x = x, y = y, motionX = motionX / 2 * PearlParticle.randomSignedDouble(), maxLife = 10))
        if (collidedVert || collidedHor) {
            motionX = recoveryX
            motionY = recoveryY
            applyMotion()
            this.onGround = false
            val tilesBroken = this.additionalData.getInt("tilesBroken")
            val uuid = this.additionalData.getUniqueId("playerUUID")
            //println("$x , $y")
            if (uuid != null) {
                val player = world.getEntity(uuid)
                if (player is AbstractEntityPlayer && (RockBottomAPI.getNet().isServer || !RockBottomAPI.getNet().isActive) && breakAround(player))
                    this.additionalData.addInt("tilesBroken", tilesBroken + 1)
            }
            if (this.motionX == .0 && motionY == .0 || tilesBroken >= 6)
                this.kill()
        }
    }

    private fun breakAround(player: AbstractEntityPlayer): Boolean {
        var out = false
        for (i in -1..1) {
            for (z in -1..1) {
                val tile = world.getState(TileLayer.MAIN, Math.round(x).toInt() + i, Math.round(y).toInt() + z).tile
                if (!tile.isAir) {
                    tile.doBreak(world, Math.round(x).toInt() + i, Math.round(y).toInt() + z, TileLayer.MAIN, player, true, true)
                    out = true
                }
            }
        }
        return out
    }

    override fun canPickUp(): Boolean {
        return false
    }
}
