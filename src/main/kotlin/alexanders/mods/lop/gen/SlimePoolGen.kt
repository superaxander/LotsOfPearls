package alexanders.mods.lop.gen

import alexanders.mods.lop.LOP
import alexanders.mods.lop.init.Tiles
import de.ellpeck.rockbottom.api.RockBottomAPI
import de.ellpeck.rockbottom.api.tile.Tile
import de.ellpeck.rockbottom.api.util.Util
import de.ellpeck.rockbottom.api.world.IChunk
import de.ellpeck.rockbottom.api.world.IChunkOrWorld
import de.ellpeck.rockbottom.api.world.IWorld
import de.ellpeck.rockbottom.api.world.layer.TileLayer
import de.ellpeck.rockbottom.api.world.gen.IWorldGenerator
import java.util.*


class SlimePoolGen : IWorldGenerator {
    private val rand = Random()
    
    override fun generate(world: IWorld, chunk: IChunk) {
        rand.setSeed(Util.scrambleSeed(chunk.x, chunk.y, world.seed))
        val randX = chunk.x + 8 + rand.nextInt(16)
        val randY = world.getLowestAirUpwards(TileLayer.MAIN, randX, 0)
        if (randY in 1..15) {
            this.generateAt(world, chunk, randX, randY, rand)
        }
    }

    private fun generateAt(world: IWorld, chunk: IChunk, x: Int, y: Int, rand: Random) {
        val tile = world.getState(x, y - 1).tile
        if (tile.name == RockBottomAPI.createInternalRes("dirt") || tile.name == RockBottomAPI.createInternalRes("grass")) {
            val poolBlobs = rand.nextInt(6) + 1
            for (b in 0..poolBlobs) this.makePool(chunk, x, y - 3, rand)
        }
    }

    private fun makePool(chunk: IChunk, x: Int, y: Int, rand: Random) {
        //println("making pool at $x, $y")
        for (w in -1..1)
            if (rand.nextBoolean()) {
                for (h in -1..1) if (rand.nextInt(2) != 0) chunk.setTileSafe(TileLayer.MAIN, x + w, y + h, Tiles.slime) // 1 in 2 chance
            } else {
                for (h in 0..1) if (rand.nextInt(2) != 0) chunk.setTileSafe(TileLayer.MAIN, x + w, y + h, Tiles.slime) // 1 in 2 chance
            }
    }

    private fun IChunkOrWorld.setTileSafe(layer: TileLayer, x: Int, y: Int, tile: Tile) { //TODO: This makes no sense
        try {
            this.setState(layer, x, y, tile.defState)
        } catch (ignored: IndexOutOfBoundsException) {
        }
    }

    override fun shouldGenerate(world: IWorld, chunk: IChunk) = chunk.gridY == 0

    override fun getPriority(): Int = 400

    companion object {
        fun register() {
            RockBottomAPI.WORLD_GENERATORS.register(RockBottomAPI.createRes(LOP.instance, "slime_pool_gen"),SlimePoolGen::class.java)
        }
    }
}
