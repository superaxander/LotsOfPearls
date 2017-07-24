package alexanders.mods.lop.tile

import de.ellpeck.rockbottom.api.IGameInstance
import de.ellpeck.rockbottom.api.RockBottomAPI
import de.ellpeck.rockbottom.api.data.set.DataSet
import de.ellpeck.rockbottom.api.tile.entity.TileEntity
import de.ellpeck.rockbottom.api.world.IWorld
import de.ellpeck.rockbottom.api.world.TileLayer
import io.netty.buffer.ByteBuf


class PhantomTileEntity(world: IWorld, x: Int, y: Int) : TileEntity(world, x, y) {
    var timeExisted = 0
    
    override fun save(set: DataSet, forSync: Boolean) {
        super.save(set, forSync)
        set.addInt("timeExisted", timeExisted)
    }

    override fun load(set: DataSet, forSync: Boolean) {
        super.load(set, forSync)
        timeExisted = set.getInt("timeExisted")
    }

    override fun update(game: IGameInstance) {
        super.update(game)
        if (RockBottomAPI.getNet().isServer || !RockBottomAPI.getNet().isConnectedToServer) {
            if (timeExisted++ >= 120)
                world.destroyTile(x, y,TileLayer.MAIN, null, false)
        }
    }
}
