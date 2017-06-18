package alexanders.mods.lop.init

import alexanders.mods.lop.net.BloodPacket
import alexanders.mods.lop.net.CooldownUpdatePacket
import alexanders.mods.lop.net.EntityPositionUpdatePacket
import alexanders.mods.lop.net.ItemUsePacket
import de.ellpeck.rockbottom.api.RockBottomAPI


object Packets {
    var itemUsePacket: Int = 0
    var entityPositionUpdatePacket: Int = 0
    var cooldownUpdatePacket: Int = 0
    var bloodPacket: Int = 0

    fun init() {
        itemUsePacket = RockBottomAPI.PACKET_REGISTRY.nextFreeId
        RockBottomAPI.PACKET_REGISTRY.register(itemUsePacket, ItemUsePacket::class.java)
        entityPositionUpdatePacket = RockBottomAPI.PACKET_REGISTRY.nextFreeId
        RockBottomAPI.PACKET_REGISTRY.register(entityPositionUpdatePacket, EntityPositionUpdatePacket::class.java)
        cooldownUpdatePacket = RockBottomAPI.PACKET_REGISTRY.nextFreeId
        RockBottomAPI.PACKET_REGISTRY.register(cooldownUpdatePacket, CooldownUpdatePacket::class.java)
        bloodPacket = RockBottomAPI.PACKET_REGISTRY.nextFreeId
        RockBottomAPI.PACKET_REGISTRY.register(bloodPacket, BloodPacket::class.java)
    }
}
