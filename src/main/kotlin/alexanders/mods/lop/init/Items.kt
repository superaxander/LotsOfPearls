package alexanders.mods.lop.init

import alexanders.mods.lop.LOP
import alexanders.mods.lop.item.*


object Items {
    lateinit var pearlItem: PearlItem
    lateinit var bouncyPearlItem: BouncyPearlItem
    lateinit var rideablePearlItem: RideablePearlItem
    lateinit var miningPearlItem: MiningPearlItem
    lateinit var spikyPearlItem: SpikyPearlItem
    lateinit var spawnPearlItem: SpawnPearlItem
    lateinit var waypointPearlItem: WaypointPearlItem
    lateinit var bridgingPearlItem: BridgingPearlItem

    fun init() {
        val lop = LOP.instance
        if (lop.configManager.isEnabled("pearl")) {
            pearlItem = PearlItem()
            pearlItem.register().addResource("pearl")
        }
        if (lop.configManager.isEnabled("bouncy")) {
            bouncyPearlItem = BouncyPearlItem()
            bouncyPearlItem.register()
        }
        if (lop.configManager.isEnabled("rideable")) {
            rideablePearlItem = RideablePearlItem()
            rideablePearlItem.register()
        }
        if (lop.configManager.isEnabled("mining")) {
            miningPearlItem = MiningPearlItem()
            miningPearlItem.register()
        }
        if (lop.configManager.isEnabled("spiky")) {
            spikyPearlItem = SpikyPearlItem()
            spikyPearlItem.register()
        }
        if (lop.configManager.isEnabled("spawn")) {
            spawnPearlItem = SpawnPearlItem()
            spawnPearlItem.register()
        }
        if (lop.configManager.isEnabled("waypoint")) {
            waypointPearlItem = WaypointPearlItem()
            waypointPearlItem.register()
        }
        if (lop.configManager.isEnabled("bridging")) {
            bridgingPearlItem = BridgingPearlItem()
            bridgingPearlItem.register()
        }
    }
}
