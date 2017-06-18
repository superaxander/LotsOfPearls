package alexanders.mods.lop.init

import alexanders.mods.lop.LOP
import alexanders.mods.lop.item.*
import de.ellpeck.rockbottom.api.RockBottomAPI


object Items {
    lateinit var pearlItem: alexanders.mods.lop.item.PearlItem
    lateinit var bouncyPearlItem: alexanders.mods.lop.item.BouncyPearlItem
    lateinit var rideablePearlItem: alexanders.mods.lop.item.RideablePearlItem
    lateinit var miningPearlItem: alexanders.mods.lop.item.MiningPearlItem
    lateinit var spikyPearlItem: alexanders.mods.lop.item.SpikyPearlItem
    lateinit var spawnPearlItem: alexanders.mods.lop.item.SpawnPearlItem
    lateinit var waypointPearlItem: alexanders.mods.lop.item.WaypointPearlItem
    lateinit var bridgingPearlItem: alexanders.mods.lop.item.BridgingPearlItem

    fun init() {
        val lop = LOP.instance
        if (lop.configManager.isEnabled("pearl")) {
            Items.pearlItem = PearlItem()
            RockBottomAPI.ITEM_REGISTRY.register(Resources.PEARL_RESOURCE, Items.pearlItem)
        }
        if (lop.configManager.isEnabled("bouncy")) {
            Items.bouncyPearlItem = BouncyPearlItem()
            RockBottomAPI.ITEM_REGISTRY.register(Resources.BOUNCY_PEARL_RESOURCE, Items.bouncyPearlItem)
        }
        if (lop.configManager.isEnabled("rideable")) {
            Items.rideablePearlItem = RideablePearlItem()
            RockBottomAPI.ITEM_REGISTRY.register(Resources.RIDEABLE_PEARL_RESOURCE, Items.rideablePearlItem)
        }
        if (lop.configManager.isEnabled("mining")) {
            Items.miningPearlItem = MiningPearlItem()
            RockBottomAPI.ITEM_REGISTRY.register(Resources.MINING_PEARL_RESOURCE, Items.miningPearlItem)
        }
        if (lop.configManager.isEnabled("spiky")) {
            Items.spikyPearlItem = SpikyPearlItem()
            RockBottomAPI.ITEM_REGISTRY.register(Resources.SPIKY_PEARL_RESOURCE, Items.spikyPearlItem)
        }
        if (lop.configManager.isEnabled("spawn")) {
            Items.spawnPearlItem = SpawnPearlItem()
            RockBottomAPI.ITEM_REGISTRY.register(Resources.SPAWN_PEARL_RESOURCE, Items.spawnPearlItem)
        }
        if (lop.configManager.isEnabled("waypoint")) {
            Items.waypointPearlItem = WaypointPearlItem()
            RockBottomAPI.ITEM_REGISTRY.register(Resources.WAYPOINT_PEARL_RESOURCE, Items.waypointPearlItem)
        }
        if (lop.configManager.isEnabled("bridging")) {
            Items.bridgingPearlItem = BridgingPearlItem()
            RockBottomAPI.ITEM_REGISTRY.register(Resources.BRIDGING_PEARL_RESOURCE, Items.bridgingPearlItem)
        }
    }
}
