package alexanders.mods.lop.render

import alexanders.mods.lop.LOP
import de.ellpeck.rockbottom.api.IGameInstance
import de.ellpeck.rockbottom.api.RockBottomAPI
import de.ellpeck.rockbottom.api.assets.IAssetManager
import de.ellpeck.rockbottom.api.particle.Particle
import de.ellpeck.rockbottom.api.world.IWorld
import org.newdawn.slick.Color
import org.newdawn.slick.Graphics


class TeleportationParticle(world: IWorld, x: Double, y: Double, motionX: Double = randomSignedDouble()*.3, motionY: Double = (RockBottomAPI.RANDOM.nextDouble())*.3, maxLife: Int=60) : Particle(world, x, y, motionX, motionY, maxLife) {
    companion object {
        fun randomSignedDouble() : Double = if(RockBottomAPI.RANDOM.nextBoolean()) RockBottomAPI.RANDOM.nextDouble() else -RockBottomAPI.RANDOM.nextDouble()
    }
    constructor(world: IWorld) : this(world = world, x = .0, y = .0, maxLife = 0) // Is this needed

    override fun render(game: IGameInstance, manager: IAssetManager, g: Graphics, x: Float, y: Float, filter: Color) {
        super.render(game, manager, g, x, y, filter)
        manager.getImage(LOP.instance.TELEPORTATION_PARTICLE_RESOURCE).draw(x, y, .25f, .25f, filter)
    }
}
