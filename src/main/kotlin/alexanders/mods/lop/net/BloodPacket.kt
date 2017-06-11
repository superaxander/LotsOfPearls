package alexanders.mods.lop.net

import alexanders.mods.lop.render.BloodParticle
import de.ellpeck.rockbottom.api.IGameInstance
import de.ellpeck.rockbottom.api.net.packet.IPacket
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext


class BloodPacket(var x: Double, var y: Double) : IPacket {
    constructor() : this(.0, .0)

    override fun toBuffer(buffer: ByteBuf) {
        buffer.writeDouble(x)
        buffer.writeDouble(y)
    }

    override fun handle(game: IGameInstance, channelHandlerContext: ChannelHandlerContext) {
        game.scheduleAction {
            for (i in 0..20) game.particleManager.addParticle(BloodParticle(world = game.world, x = x, y = y, maxLife = 40))
            return@scheduleAction true
        }
    }

    override fun fromBuffer(buffer: ByteBuf) {
        x = buffer.readDouble()
        y = buffer.readDouble()
    }
}
