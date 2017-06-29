package alexanders.mods.lop.render

import alexanders.mods.lop.init.Resources.BLOOD_PARTICLE_RESOURCE
import de.ellpeck.rockbottom.api.util.Util
import de.ellpeck.rockbottom.api.world.IWorld


class BloodParticle(world: IWorld, x: Double, y: Double, motionX: Double = randomSignedDouble() * .3, motionY: Double = (Util.RANDOM.nextDouble()) * .3, maxLife: Int = 60) : PearlParticle(BLOOD_PARTICLE_RESOURCE, world, x, y, motionX, motionY, maxLife) {

    constructor(world: IWorld) : this(world = world, x = .0, y = .0, maxLife = 0) // Is this needed
}
