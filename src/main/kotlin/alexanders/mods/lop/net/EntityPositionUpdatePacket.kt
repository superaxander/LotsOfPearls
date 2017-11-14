package alexanders.mods.lop.net

import alexanders.mods.lop.render.TeleportationParticle
import de.ellpeck.rockbottom.api.IGameInstance
import de.ellpeck.rockbottom.api.net.packet.IPacket
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import java.nio.charset.StandardCharsets
import java.util.*


class EntityPositionUpdatePacket(var uuid: UUID?, var x: Double, var y: Double, var disableParticles: Boolean = false) : IPacket {
    constructor() : this(null, 0.0, 0.0)

    override fun toBuffer(buffer: ByteBuf) {
        buffer.writeBytes(uuid.toString().toByteArray(StandardCharsets.UTF_8))
        buffer.writeDouble(x)
        buffer.writeDouble(y)
        buffer.writeBoolean(disableParticles)
    }

    override fun handle(game: IGameInstance, channelHandlerContext: ChannelHandlerContext) {
        game.enqueueAction({ gameInstance, _ ->
            if (uuid != null) {
                gameInstance.world.getEntity(uuid).setPos(x, y)
                if (!disableParticles)
                    for (i in 0..20) game.particleManager.addParticle(TeleportationParticle(world = game.world, x = x, y = y, maxLife = 60))
            }
        }, null)
    }

    override fun fromBuffer(buffer: ByteBuf) {
        uuid = UUID.fromString(buffer.readBytes(36).toString(StandardCharsets.UTF_8))
        x = buffer.readDouble()
        y = buffer.readDouble()
        disableParticles = buffer.readBoolean()
    }
}
