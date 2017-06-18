package alexanders.mods.lop.tile

import alexanders.mods.lop.init.Resources.PHANTOM_TILE_RESOURCE
import de.ellpeck.rockbottom.api.tile.TileBasic
import de.ellpeck.rockbottom.api.tile.entity.TileEntity
import de.ellpeck.rockbottom.api.world.IWorld


class PhantomTile : TileBasic(PHANTOM_TILE_RESOURCE) {
    override fun provideTileEntity(world: IWorld, x: Int, y: Int): TileEntity {
        val e = world.getTileEntity(x, y)
        if (e != null)
            return e
        else
            return PhantomTileEntity(world, x, y)
    }

    override fun canProvideTileEntity(): Boolean {
        return true
    }
}
