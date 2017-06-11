package alexanders.mods.lop.net

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
        if(uuid != null) {
            game.world.getEntity(uuid).setPos(x, y)
        }
    }

    override fun fromBuffer(buffer: ByteBuf) {
        uuid = UUID.fromString(buffer.readBytes(36).toString(StandardCharsets.UTF_8))
        x = buffer.readDouble()
        y = buffer.readDouble()
    }
}
