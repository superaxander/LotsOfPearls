package alexanders.mods.lop.tile

import alexanders.mods.lop.init.Resources
import de.ellpeck.rockbottom.api.data.set.DataSet
import de.ellpeck.rockbottom.api.entity.Entity
import de.ellpeck.rockbottom.api.item.ToolType
import de.ellpeck.rockbottom.api.tile.TileBasic
import de.ellpeck.rockbottom.api.world.IWorld
import de.ellpeck.rockbottom.api.world.TileLayer


class SlimeTile : TileBasic(Resources.SLIME_RESOURCE) {
    init {
        this.setHardness(.5f)
        this.addEffectiveTool(ToolType.PICKAXE, 0)
        this.setForceDrop()
    }

    override fun onCollideWithEntity(world: IWorld, x: Int, y: Int, layer: TileLayer, entity: Entity) {
        if (entity.additionalData == null)
            entity.additionalData = DataSet()
        val lastTimeBounced = entity.additionalData.getLong("lastTimeBounced")
        val now = System.currentTimeMillis()
        if (lastTimeBounced + 100 < now) {
            if (entity.motionY < 0) {
                entity.motionY = -entity.motionY
                entity.fallAmount = 0

                entity.applyMotion()
            }
            entity.additionalData.addLong("lastTimeBounced", now)
        }
    }

}
