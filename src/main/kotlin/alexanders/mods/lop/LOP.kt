package alexanders.mods.lop

import alexanders.mods.lop.entity.*
import alexanders.mods.lop.event.ItemUsageListener
import alexanders.mods.lop.item.*
import alexanders.mods.lop.net.BloodPacket
import alexanders.mods.lop.net.CooldownUpdatePacket
import alexanders.mods.lop.net.EntityPositionUpdatePacket
import alexanders.mods.lop.net.ItemUsePacket
import de.ellpeck.rockbottom.api.IApiHandler
import de.ellpeck.rockbottom.api.IGameInstance
import de.ellpeck.rockbottom.api.RockBottomAPI
import de.ellpeck.rockbottom.api.assets.IAssetManager
import de.ellpeck.rockbottom.api.event.IEventHandler
import de.ellpeck.rockbottom.api.event.impl.EntityTickEvent
import de.ellpeck.rockbottom.api.mod.IMod


class LOP() : IMod {

    companion object {
        lateinit var instance: LOP
    }

    init {
        instance = this
    }

    val PEARL_RESOURCE = RockBottomAPI.createRes(this, "pearl")
    val BOUNCY_PEARL_RESOURCE = RockBottomAPI.createRes(this, "bouncy_pearl")
    val RIDEABLE_PEARL_RESOURCE = RockBottomAPI.createRes(this, "rideable_pearl")
    val MINING_PEARL_RESOURCE = RockBottomAPI.createRes(this, "mining_pearl")
    val SPIKY_PEARL_RESOURCE = RockBottomAPI.createRes(this, "spiky_pearl")
    val SPAWN_PEARL_RESOURCE = RockBottomAPI.createRes(this, "spawn_pearl")
    val WAYPOINT_PEARL_RESOURCE = RockBottomAPI.createRes(this, "waypoint_pearl")
    val COOLDOWN_RESOURCE = RockBottomAPI.createRes(this, "cooldown")
    val PEARL_DESC_RESOURCE = RockBottomAPI.createRes(this, "desc.pearl")
    val BOUNCY_PEARL_DESC_RESOURCE = RockBottomAPI.createRes(this, "desc.bouncy_pearl")
    val RIDEABLE_PEARL_DESC_RESOURCE = RockBottomAPI.createRes(this, "desc.rideable_pearl")
    val MINING_PEARL_DESC_RESOURCE = RockBottomAPI.createRes(this, "desc.mining_pearl")
    val SPIKY_PEARL_DESC_RESOURCE = RockBottomAPI.createRes(this, "desc.spiky_pearl")
    val SPAWN_PEARL_DESC_RESOURCE = RockBottomAPI.createRes(this, "desc.spawn_pearl")
    val WAYPOINT_PEARL_DESC_RESOURCE = RockBottomAPI.createRes(this, "desc.waypoint_pearl")
    val WAYPOINT_PEARL_USAGE_DESC_RESOURCE = RockBottomAPI.createRes(this, "desc.waypoint_pearl_usage")
    val SET_TO_DESC_RESOURCE = RockBottomAPI.createRes(this, "desc.set_to")
    val MORE_INFO_DESC_RESOURCE = RockBottomAPI.createRes(this, "desc.more_info")
    val TELEPORTATION_PARTICLE_RESOURCE = RockBottomAPI.createRes(this, "particles.teleportation")
    val BLOOD_PARTICLE_RESOURCE = RockBottomAPI.createRes(this, "particles.blood")

    override fun getVersion() = "0.6"

    override fun getId() = "lop"

    override fun getDisplayName() = "Lots of Pearls"

    override fun getResourceLocation() = "/assets/$id"

    override fun getDescription(): String = "Adds a bunch of pearls. What else did you expect?"

    override fun init(game: IGameInstance, assetManager: IAssetManager, apiHandler: IApiHandler, eventHandler: IEventHandler) {
        val itemUsageListener = ItemUsageListener()
        // Register event handlers
        eventHandler.registerListener(EntityTickEvent::class.java, itemUsageListener)
        game.container.input.addMouseListener(itemUsageListener)

        // Register network packets
        RockBottomAPI.PACKET_REGISTRY.register(RockBottomAPI.PACKET_REGISTRY.nextFreeId, ItemUsePacket::class.java)
        RockBottomAPI.PACKET_REGISTRY.register(RockBottomAPI.PACKET_REGISTRY.nextFreeId, EntityPositionUpdatePacket::class.java)
        RockBottomAPI.PACKET_REGISTRY.register(RockBottomAPI.PACKET_REGISTRY.nextFreeId, CooldownUpdatePacket::class.java)
        RockBottomAPI.PACKET_REGISTRY.register(RockBottomAPI.PACKET_REGISTRY.nextFreeId, BloodPacket::class.java)

        // Register items
        RockBottomAPI.ITEM_REGISTRY.register(PEARL_RESOURCE, PearlItem())
        RockBottomAPI.ITEM_REGISTRY.register(BOUNCY_PEARL_RESOURCE, BouncyPearlItem())
        RockBottomAPI.ITEM_REGISTRY.register(RIDEABLE_PEARL_RESOURCE, RideablePearlItem())
        RockBottomAPI.ITEM_REGISTRY.register(MINING_PEARL_RESOURCE, MiningPearlItem())
        RockBottomAPI.ITEM_REGISTRY.register(SPIKY_PEARL_RESOURCE, SpikyPearlItem())
        RockBottomAPI.ITEM_REGISTRY.register(SPAWN_PEARL_RESOURCE, SpawnPearlItem())
        RockBottomAPI.ITEM_REGISTRY.register(WAYPOINT_PEARL_RESOURCE, WaypointPearlItem())

        // Register entities
        RockBottomAPI.ENTITY_REGISTRY.register(PEARL_RESOURCE, PearlEntity::class.java)
        RockBottomAPI.ENTITY_REGISTRY.register(BOUNCY_PEARL_RESOURCE, BouncyPearlEntity::class.java)
        RockBottomAPI.ENTITY_REGISTRY.register(RIDEABLE_PEARL_RESOURCE, RideablePearlEntity::class.java)
        RockBottomAPI.ENTITY_REGISTRY.register(MINING_PEARL_RESOURCE, MiningPearlEntity::class.java)
        RockBottomAPI.ENTITY_REGISTRY.register(SPIKY_PEARL_RESOURCE, SpikyPearlEntity::class.java)
    }
}
