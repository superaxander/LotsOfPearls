package alexanders.mods.lop.net

import alexanders.mods.lop.render.TeleportationParticle
import de.ellpeck.rockbottom.api.IGameInstance
import de.ellpeck.rockbottom.api.net.packet.IPacket
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import java.nio.charset.StandardCharsets
import java.util.*


class EntityPostionUpdatePacket(var uuid: UUID?, var x: Double, var y: Double) : IPacket {
    constructor() : this(null, 0.0, 0.0)

    override fun toBuffer(buffer: ByteBuf) {
        buffer.writeBytes(uuid.toString().toByteArray(StandardCharsets.UTF_8))
        buffer.writeDouble(x)
        buffer.writeDouble(y)
    }

    override fun handle(game: IGameInstance, channelHandlerContext: ChannelHandlerContext) {
        game.scheduleAction {
            if (uuid != null) {
                game.world.getEntity(uuid).setPos(x, y)
                for (i in 0..20) game.particleManager.addParticle(TeleportationParticle(world = game.world, x = x, y = y, maxLife = 60))
            }
            return@scheduleAction true
        }
    }

    override fun fromBuffer(buffer: ByteBuf) {
        uuid = UUID.fromString(buffer.readBytes(36).toString(StandardCharsets.UTF_8))
        x = buffer.readDouble()
        y = buffer.readDouble()
    }
}
