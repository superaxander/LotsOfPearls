package alexanders.mods.lop.net

import alexanders.mods.lop.item.Useable
import de.ellpeck.rockbottom.api.IGameInstance
import de.ellpeck.rockbottom.api.entity.player.AbstractEntityPlayer
import de.ellpeck.rockbottom.api.net.packet.IPacket
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import org.newdawn.slick.geom.Vector2f
import java.nio.charset.StandardCharsets
import java.util.*


class ItemUsePacket(private var mouseDirection: Vector2f?, private var uuid: UUID?, private var shiftPressed: Boolean) : IPacket {
    constructor() : this(null, null, false)

    override fun toBuffer(buffer: ByteBuf) {
        buffer.writeFloat(if (mouseDirection == null) 0f else (mouseDirection as Vector2f).x)
        buffer.writeFloat(if (mouseDirection == null) 0f else (mouseDirection as Vector2f).y)
        buffer.writeBytes(uuid.toString().toByteArray(StandardCharsets.UTF_8))
        buffer.writeBoolean(shiftPressed)
    }

    override fun handle(game: IGameInstance, channelHandlerContext: ChannelHandlerContext?) {
        // Always ran on server:
        //println(uuid.toString())
        game.scheduleAction {
            val p = game.world.getEntity(uuid) as AbstractEntityPlayer
            val itemInstance = p.inv[p.selectedSlot]
            val itemType = itemInstance.item
            if (itemType is Useable && mouseDirection != null) {
                itemType.use(itemInstance, mouseDirection as Vector2f, p, shiftPressed)
            }
            return@scheduleAction true
        }
    }

    override fun fromBuffer(buffer: ByteBuf) {
        mouseDirection = Vector2f(buffer.readFloat(), buffer.readFloat())
        uuid = UUID.fromString(buffer.readBytes(36).toString(StandardCharsets.UTF_8))
        shiftPressed = buffer.readBoolean()
    }
}
