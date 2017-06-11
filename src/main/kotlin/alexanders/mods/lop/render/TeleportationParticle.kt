package alexanders.mods.lop.render

import alexanders.mods.lop.LOP
import de.ellpeck.rockbottom.api.RockBottomAPI
import de.ellpeck.rockbottom.api.world.IWorld


class TeleportationParticle(world: IWorld, x: Double, y: Double, motionX: Double = randomSignedDouble() * .3, motionY: Double = (RockBottomAPI.RANDOM.nextDouble()) * .3, maxLife: Int = 60) : PearlParticle(LOP.instance.TELEPORTATION_PARTICLE_RESOURCE, world, x, y, motionX, motionY, maxLife) {

    constructor(world: IWorld) : this(world = world, x = .0, y = .0, maxLife = 0) // Is this needed
}
