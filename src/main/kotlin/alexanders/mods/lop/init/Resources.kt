package alexanders.mods.lop.init

import de.ellpeck.rockbottom.api.RockBottomAPI
import de.ellpeck.rockbottom.api.util.reg.IResourceName


object Resources {
    lateinit var PEARL_RESOURCE: de.ellpeck.rockbottom.api.util.reg.IResourceName
    lateinit var BOUNCY_PEARL_RESOURCE: de.ellpeck.rockbottom.api.util.reg.IResourceName
    lateinit var RIDEABLE_PEARL_RESOURCE: de.ellpeck.rockbottom.api.util.reg.IResourceName
    lateinit var MINING_PEARL_RESOURCE: de.ellpeck.rockbottom.api.util.reg.IResourceName
    lateinit var SPIKY_PEARL_RESOURCE: de.ellpeck.rockbottom.api.util.reg.IResourceName
    lateinit var SPAWN_PEARL_RESOURCE: de.ellpeck.rockbottom.api.util.reg.IResourceName
    lateinit var WAYPOINT_PEARL_RESOURCE: de.ellpeck.rockbottom.api.util.reg.IResourceName
    lateinit var BRIDGING_PEARL_RESOURCE: de.ellpeck.rockbottom.api.util.reg.IResourceName
    lateinit var COOLDOWN_RESOURCE: de.ellpeck.rockbottom.api.util.reg.IResourceName

    lateinit var PEARL_DESC_RESOURCE: de.ellpeck.rockbottom.api.util.reg.IResourceName
    lateinit var BOUNCY_PEARL_DESC_RESOURCE: de.ellpeck.rockbottom.api.util.reg.IResourceName
    lateinit var RIDEABLE_PEARL_DESC_RESOURCE: de.ellpeck.rockbottom.api.util.reg.IResourceName
    lateinit var MINING_PEARL_DESC_RESOURCE: de.ellpeck.rockbottom.api.util.reg.IResourceName
    lateinit var SPIKY_PEARL_DESC_RESOURCE: de.ellpeck.rockbottom.api.util.reg.IResourceName
    lateinit var SPAWN_PEARL_DESC_RESOURCE: de.ellpeck.rockbottom.api.util.reg.IResourceName
    lateinit var WAYPOINT_PEARL_DESC_RESOURCE: de.ellpeck.rockbottom.api.util.reg.IResourceName
    lateinit var WAYPOINT_PEARL_USAGE_DESC_RESOURCE: de.ellpeck.rockbottom.api.util.reg.IResourceName
    lateinit var BRIDGING_PEARL_DESC_RESOURCE: de.ellpeck.rockbottom.api.util.reg.IResourceName
    lateinit var SET_TO_DESC_RESOURCE: de.ellpeck.rockbottom.api.util.reg.IResourceName
    lateinit var MORE_INFO_DESC_RESOURCE: de.ellpeck.rockbottom.api.util.reg.IResourceName

    lateinit var PHANTOM_TILE_RESOURCE: de.ellpeck.rockbottom.api.util.reg.IResourceName
    lateinit var PEARL_ORE_RESOURCE: de.ellpeck.rockbottom.api.util.reg.IResourceName
    lateinit var SLIME_RESOURCE: de.ellpeck.rockbottom.api.util.reg.IResourceName

    lateinit var TELEPORTATION_PARTICLE_RESOURCE: de.ellpeck.rockbottom.api.util.reg.IResourceName
    lateinit var BLOOD_PARTICLE_RESOURCE: de.ellpeck.rockbottom.api.util.reg.IResourceName


    fun init() {
        val lop = alexanders.mods.lop.LOP.Companion.instance
        alexanders.mods.lop.init.Resources.PEARL_RESOURCE = de.ellpeck.rockbottom.api.RockBottomAPI.createRes(lop, "pearl")!!
        alexanders.mods.lop.init.Resources.BOUNCY_PEARL_RESOURCE = de.ellpeck.rockbottom.api.RockBottomAPI.createRes(lop, "bouncy_pearl")!!
        alexanders.mods.lop.init.Resources.RIDEABLE_PEARL_RESOURCE = de.ellpeck.rockbottom.api.RockBottomAPI.createRes(lop, "rideable_pearl")!!
        alexanders.mods.lop.init.Resources.MINING_PEARL_RESOURCE = de.ellpeck.rockbottom.api.RockBottomAPI.createRes(lop, "mining_pearl")!!
        alexanders.mods.lop.init.Resources.SPIKY_PEARL_RESOURCE = de.ellpeck.rockbottom.api.RockBottomAPI.createRes(lop, "spiky_pearl")!!
        alexanders.mods.lop.init.Resources.SPAWN_PEARL_RESOURCE = de.ellpeck.rockbottom.api.RockBottomAPI.createRes(lop, "spawn_pearl")!!
        alexanders.mods.lop.init.Resources.WAYPOINT_PEARL_RESOURCE = de.ellpeck.rockbottom.api.RockBottomAPI.createRes(lop, "waypoint_pearl")!!
        alexanders.mods.lop.init.Resources.BRIDGING_PEARL_RESOURCE = de.ellpeck.rockbottom.api.RockBottomAPI.createRes(lop, "bridging_pearl")!!
        alexanders.mods.lop.init.Resources.COOLDOWN_RESOURCE = de.ellpeck.rockbottom.api.RockBottomAPI.createRes(lop, "cooldown")!!
        alexanders.mods.lop.init.Resources.PEARL_DESC_RESOURCE = de.ellpeck.rockbottom.api.RockBottomAPI.createRes(lop, "desc.pearl")!!
        alexanders.mods.lop.init.Resources.BOUNCY_PEARL_DESC_RESOURCE = de.ellpeck.rockbottom.api.RockBottomAPI.createRes(lop, "desc.bouncy_pearl")!!
        alexanders.mods.lop.init.Resources.RIDEABLE_PEARL_DESC_RESOURCE = de.ellpeck.rockbottom.api.RockBottomAPI.createRes(lop, "desc.rideable_pearl")!!
        alexanders.mods.lop.init.Resources.MINING_PEARL_DESC_RESOURCE = de.ellpeck.rockbottom.api.RockBottomAPI.createRes(lop, "desc.mining_pearl")!!
        alexanders.mods.lop.init.Resources.SPIKY_PEARL_DESC_RESOURCE = de.ellpeck.rockbottom.api.RockBottomAPI.createRes(lop, "desc.spiky_pearl")!!
        alexanders.mods.lop.init.Resources.SPAWN_PEARL_DESC_RESOURCE = de.ellpeck.rockbottom.api.RockBottomAPI.createRes(lop, "desc.spawn_pearl")!!
        alexanders.mods.lop.init.Resources.WAYPOINT_PEARL_DESC_RESOURCE = de.ellpeck.rockbottom.api.RockBottomAPI.createRes(lop, "desc.waypoint_pearl")!!
        alexanders.mods.lop.init.Resources.WAYPOINT_PEARL_USAGE_DESC_RESOURCE = de.ellpeck.rockbottom.api.RockBottomAPI.createRes(lop, "desc.waypoint_pearl_usage")!!
        alexanders.mods.lop.init.Resources.BRIDGING_PEARL_DESC_RESOURCE = de.ellpeck.rockbottom.api.RockBottomAPI.createRes(lop, "desc.bridging_pearl")!!
        alexanders.mods.lop.init.Resources.SET_TO_DESC_RESOURCE = de.ellpeck.rockbottom.api.RockBottomAPI.createRes(lop, "desc.set_to")!!
        alexanders.mods.lop.init.Resources.MORE_INFO_DESC_RESOURCE = de.ellpeck.rockbottom.api.RockBottomAPI.createRes(lop, "desc.more_info")!!
        alexanders.mods.lop.init.Resources.PHANTOM_TILE_RESOURCE = de.ellpeck.rockbottom.api.RockBottomAPI.createRes(lop, "phantom")!!
        alexanders.mods.lop.init.Resources.PEARL_ORE_RESOURCE = de.ellpeck.rockbottom.api.RockBottomAPI.createRes(lop, "pearl_ore")!!
        alexanders.mods.lop.init.Resources.SLIME_RESOURCE = de.ellpeck.rockbottom.api.RockBottomAPI.createRes(lop, "slime")
        alexanders.mods.lop.init.Resources.TELEPORTATION_PARTICLE_RESOURCE = de.ellpeck.rockbottom.api.RockBottomAPI.createRes(lop, "particles.teleportation")!!
        alexanders.mods.lop.init.Resources.BLOOD_PARTICLE_RESOURCE = de.ellpeck.rockbottom.api.RockBottomAPI.createRes(lop, "particles.blood")!!
    }
}
