package alexanders.mods.lop.entity

import alexanders.mods.lop.init.Items.pearlItem
import alexanders.mods.lop.init.Tiles.phantomTile
import alexanders.mods.lop.render.PearlParticle
import alexanders.mods.lop.render.TeleportationParticle
import alexanders.mods.lop.tile.PhantomTile
import de.ellpeck.rockbottom.api.IGameInstance
import de.ellpeck.rockbottom.api.RockBottomAPI
import de.ellpeck.rockbottom.api.data.set.DataSet
import de.ellpeck.rockbottom.api.entity.EntityItem
import de.ellpeck.rockbottom.api.entity.player.AbstractEntityPlayer
import de.ellpeck.rockbottom.api.item.ItemInstance
import de.ellpeck.rockbottom.api.world.IWorld
import de.ellpeck.rockbottom.api.world.TileLayer
import org.newdawn.slick.geom.Vector2f
import java.util.*


class BridgingPearlEntity(world: IWorld, player: UUID? = null, mouseDirection: Vector2f = Vector2f()) : EntityItem(world, ItemInstance(pearlItem)) {

    init {
        if (this.additionalData == null) {
            this.additionalData = DataSet()
            if (player != null)
                this.additionalData.addUniqueId("playerUUID", player)
            this.additionalData.addInt("tilesPlaced", 0)
        }
        this.item.amount = -1
        this.motionX = mouseDirection.x.toDouble()
        this.motionY = -mouseDirection.y.toDouble()
        if (player != null) {
            val ePlayer = world.getEntity(player)
            this.setPos(ePlayer.x, ePlayer.y - .5f)
        } else if (this.additionalData.getUniqueId("playerUUID") != null) {
            val ePlayer = world.getEntity(this.additionalData.getUniqueId("playerUUID"))
            this.setPos(ePlayer.x, ePlayer.y - .5f)
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
            val tile = world.getTile(TileLayer.MAIN, Math.round(x).toInt(), Math.round(y).toInt())
            if (tile is PhantomTile) {
                collidedHor = false
                collidedVert = false
                onGround = false
                motionX = recoveryX
                motionY = recoveryY
                applyMotion()
            }
        }
        val tilesPlaced = this.additionalData.getInt("tilesPlaced")
        val uuid = this.additionalData.getUniqueId("playerUUID")
        //println("$x , $y")
        if (uuid != null) {
            val player = world.getEntity(uuid)
            if (player is AbstractEntityPlayer && (RockBottomAPI.getNet().isServer || !RockBottomAPI.getNet().isActive)) {
                placePhantomTile()
                this.additionalData.addInt("tilesPlaced", tilesPlaced + 1)
            }
        }
        if (this.onGround || tilesPlaced >= 12)
            this.kill()

    }

    private fun placePhantomTile() {
        //println("Placing at $x, $y");
        val tile = world.getTile(TileLayer.MAIN, Math.round(x).toInt(), Math.round(y).toInt())
        //println(tile.isAir)
        if (tile.isAir)
            world.setTile(TileLayer.MAIN, Math.round(x).toInt(), Math.round(y).toInt(), phantomTile)
        //tile.doPlace(world, Math.round(x).toInt(), Math.round(y).toInt(), TileLayer.MAIN, ItemInstance(LOP.instance.phantomTile), player)
    }

    override fun canPickUp(): Boolean {
        return false
    }
}
