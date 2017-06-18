package alexanders.mods.lop.init

import alexanders.mods.lop.LOP
import alexanders.mods.lop.entity.*
import de.ellpeck.rockbottom.api.RockBottomAPI
import de.ellpeck.rockbottom.api.entity.Entity

object Entities {
    lateinit var pearl: Class<out Entity>
    lateinit var bouncy_pearl: Class<out Entity>
    lateinit var rideable_pearl: Class<out Entity>
    lateinit var mining_pearl: Class<out Entity>
    lateinit var spiky_pearl: Class<out Entity>
    lateinit var bridging_pearl: Class<out Entity>

    fun init() {
        val lop = LOP.instance
        if (lop.configManager.isEnabled("pearl")) {
            Entities.pearl = PearlEntity::class.java
            RockBottomAPI.ENTITY_REGISTRY.register(Resources.PEARL_RESOURCE, Entities.pearl)
        }
        if (lop.configManager.isEnabled("bouncy")) {
            Entities.bouncy_pearl = BouncyPearlEntity::class.java
            RockBottomAPI.ENTITY_REGISTRY.register(Resources.BOUNCY_PEARL_RESOURCE, Entities.bouncy_pearl)
        }
        if (lop.configManager.isEnabled("rideable")) {
            Entities.rideable_pearl = RideablePearlEntity::class.java
            RockBottomAPI.ENTITY_REGISTRY.register(Resources.RIDEABLE_PEARL_RESOURCE, Entities.rideable_pearl)
        }
        if (lop.configManager.isEnabled("mining")) {
            Entities.mining_pearl = MiningPearlEntity::class.java
            RockBottomAPI.ENTITY_REGISTRY.register(Resources.MINING_PEARL_RESOURCE, Entities.mining_pearl)
        }
        if (lop.configManager.isEnabled("spiky")) {
            Entities.spiky_pearl = SpikyPearlEntity::class.java
            RockBottomAPI.ENTITY_REGISTRY.register(Resources.SPIKY_PEARL_RESOURCE, Entities.spiky_pearl)
        }
        if (lop.configManager.isEnabled("bridging")) {
            Entities.bridging_pearl = BridgingPearlEntity::class.java
            RockBottomAPI.ENTITY_REGISTRY.register(Resources.BRIDGING_PEARL_RESOURCE, Entities.bridging_pearl)
        }
    }
}

