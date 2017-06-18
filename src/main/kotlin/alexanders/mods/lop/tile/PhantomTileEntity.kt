package alexanders.mods.lop.tile

import de.ellpeck.rockbottom.api.IGameInstance
import de.ellpeck.rockbottom.api.RockBottomAPI
import de.ellpeck.rockbottom.api.tile.entity.TileEntity
import de.ellpeck.rockbottom.api.world.IWorld
import de.ellpeck.rockbottom.api.world.TileLayer
import io.netty.buffer.ByteBuf


class PhantomTileEntity(world: IWorld, x: Int, y: Int) : TileEntity(world, x, y) {
    var timeExisted = 0

    override fun toBuf(buf: ByteBuf) {
        super.toBuf(buf)
        buf.writeInt(timeExisted)
    }

    override fun fromBuf(buf: ByteBuf) {
        super.fromBuf(buf)
        timeExisted = buf.readInt()
    }

    override fun update(game: IGameInstance) {
        super.update(game)
        if (RockBottomAPI.getNet().isServer || !RockBottomAPI.getNet().isConnectedToServer) {
            if (timeExisted++ >= 120)
                world.getTile(x, y)?.doBreak(world, x, y, TileLayer.MAIN, game.player, false) // Should I use a fake player?
        }
    }
}
