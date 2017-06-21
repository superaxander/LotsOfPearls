package alexanders.mods.lop.init

import alexanders.mods.lop.LOP
import de.ellpeck.rockbottom.api.RockBottomAPI
import de.ellpeck.rockbottom.api.util.reg.IResourceName


object Resources {
    lateinit var PEARL_RESOURCE: IResourceName
    lateinit var BOUNCY_PEARL_RESOURCE: IResourceName
    lateinit var RIDEABLE_PEARL_RESOURCE: IResourceName
    lateinit var MINING_PEARL_RESOURCE: IResourceName
    lateinit var SPIKY_PEARL_RESOURCE: IResourceName
    lateinit var SPAWN_PEARL_RESOURCE: IResourceName
    lateinit var WAYPOINT_PEARL_RESOURCE: IResourceName
    lateinit var BRIDGING_PEARL_RESOURCE: IResourceName
    lateinit var COOLDOWN_RESOURCE: IResourceName

    lateinit var PEARL_DESC_RESOURCE: IResourceName
    lateinit var BOUNCY_PEARL_DESC_RESOURCE: IResourceName
    lateinit var RIDEABLE_PEARL_DESC_RESOURCE: IResourceName
    lateinit var MINING_PEARL_DESC_RESOURCE: IResourceName
    lateinit var SPIKY_PEARL_DESC_RESOURCE: IResourceName
    lateinit var SPAWN_PEARL_DESC_RESOURCE: IResourceName
    lateinit var WAYPOINT_PEARL_DESC_RESOURCE: IResourceName
    lateinit var WAYPOINT_PEARL_USAGE_DESC_RESOURCE: IResourceName
    lateinit var BRIDGING_PEARL_DESC_RESOURCE: IResourceName
    lateinit var SET_TO_DESC_RESOURCE: IResourceName
    lateinit var MORE_INFO_DESC_RESOURCE: IResourceName

    lateinit var PHANTOM_TILE_RESOURCE: IResourceName
    lateinit var PEARL_ORE_RESOURCE: IResourceName
    lateinit var SLIME_RESOURCE: IResourceName

    lateinit var TELEPORTATION_PARTICLE_RESOURCE: IResourceName
    lateinit var BLOOD_PARTICLE_RESOURCE: IResourceName

    lateinit var UP_ARROW: IResourceName
    lateinit var DOWN_ARROW: IResourceName

    fun init() {
        val lop = LOP.instance
        PEARL_RESOURCE = RockBottomAPI.createRes(lop, "pearl")!!
        BOUNCY_PEARL_RESOURCE = RockBottomAPI.createRes(lop, "bouncy_pearl")!!
        RIDEABLE_PEARL_RESOURCE = RockBottomAPI.createRes(lop, "rideable_pearl")!!
        MINING_PEARL_RESOURCE = RockBottomAPI.createRes(lop, "mining_pearl")!!
        SPIKY_PEARL_RESOURCE = RockBottomAPI.createRes(lop, "spiky_pearl")!!
        SPAWN_PEARL_RESOURCE = RockBottomAPI.createRes(lop, "spawn_pearl")!!
        WAYPOINT_PEARL_RESOURCE = RockBottomAPI.createRes(lop, "waypoint_pearl")!!
        BRIDGING_PEARL_RESOURCE = RockBottomAPI.createRes(lop, "bridging_pearl")!!
        COOLDOWN_RESOURCE = RockBottomAPI.createRes(lop, "cooldown")!!
        PEARL_DESC_RESOURCE = RockBottomAPI.createRes(lop, "desc.pearl")!!
        BOUNCY_PEARL_DESC_RESOURCE = RockBottomAPI.createRes(lop, "desc.bouncy_pearl")!!
        RIDEABLE_PEARL_DESC_RESOURCE = RockBottomAPI.createRes(lop, "desc.rideable_pearl")!!
        MINING_PEARL_DESC_RESOURCE = RockBottomAPI.createRes(lop, "desc.mining_pearl")!!
        SPIKY_PEARL_DESC_RESOURCE = RockBottomAPI.createRes(lop, "desc.spiky_pearl")!!
        SPAWN_PEARL_DESC_RESOURCE = RockBottomAPI.createRes(lop, "desc.spawn_pearl")!!
        WAYPOINT_PEARL_DESC_RESOURCE = RockBottomAPI.createRes(lop, "desc.waypoint_pearl")!!
        WAYPOINT_PEARL_USAGE_DESC_RESOURCE = RockBottomAPI.createRes(lop, "desc.waypoint_pearl_usage")!!
        BRIDGING_PEARL_DESC_RESOURCE = RockBottomAPI.createRes(lop, "desc.bridging_pearl")!!
        SET_TO_DESC_RESOURCE = RockBottomAPI.createRes(lop, "desc.set_to")!!
        MORE_INFO_DESC_RESOURCE = RockBottomAPI.createRes(lop, "desc.more_info")!!
        PHANTOM_TILE_RESOURCE = RockBottomAPI.createRes(lop, "phantom")!!
        PEARL_ORE_RESOURCE = RockBottomAPI.createRes(lop, "pearl_ore")!!
        SLIME_RESOURCE = RockBottomAPI.createRes(lop, "slime")
        TELEPORTATION_PARTICLE_RESOURCE = RockBottomAPI.createRes(lop, "particles.teleportation")!!
        BLOOD_PARTICLE_RESOURCE = RockBottomAPI.createRes(lop, "particles.blood")!!

       UP_ARROW = RockBottomAPI.createRes(lop, "button.up")
       DOWN_ARROW = RockBottomAPI.createRes(lop, "button.down")

    }
}
