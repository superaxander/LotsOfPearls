package alexanders.mods.lop.tile

import de.ellpeck.rockbottom.api.IGameInstance
import de.ellpeck.rockbottom.api.tile.entity.TileEntity
import de.ellpeck.rockbottom.api.world.IWorld
import de.ellpeck.rockbottom.api.world.layer.TileLayer


class SlimeTileEntity(world: IWorld, x: Int, y: Int, layer: TileLayer) : TileEntity(world, x, y, layer) {
    override fun update(game: IGameInstance?) {
        //if (!RockBottomAPI.getNet().isClient)
        //world.getEntities(BoundBox(x.toDouble(), y.toDouble(), x + 1.0, y + 1.2)).forEach { it.motionY = (-it.motionY * 0.85) }
    }
}
