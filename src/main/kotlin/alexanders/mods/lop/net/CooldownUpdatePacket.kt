package alexanders.mods.lop.net

import alexanders.mods.lop.item.Useable
import de.ellpeck.rockbottom.api.IGameInstance
import de.ellpeck.rockbottom.api.data.set.DataSet
import de.ellpeck.rockbottom.api.net.packet.IPacket
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext


class CooldownUpdatePacket(var cooldown: Int) : IPacket {
    constructor() : this(0)

    override fun toBuffer(buffer: ByteBuf) {
        buffer.writeInt(cooldown)
    }

    override fun handle(game: IGameInstance, channelHandlerContext: ChannelHandlerContext) {
        val slot = game.player.inv[game.player.selectedSlot]
        println("received cooldown update")
        if (slot != null && slot.additionalData == null && slot.item is Useable) {
            slot.additionalData = DataSet()
            slot.additionalData.addInt("cooldown", cooldown)
        } else if (slot != null && slot.additionalData != null && slot.additionalData.hasKey("cooldown")) {
            slot.additionalData.addInt("cooldown", cooldown)
            if (cooldown == 60 && slot.removeAmount(1).amount <= 0)
                game.player.inv[game.player.selectedSlot] = null
        }
    }

    override fun fromBuffer(buffer: ByteBuf) {
        cooldown = buffer.readInt()
    }
}
