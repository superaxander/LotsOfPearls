package alexanders.mods.lop.gen

import alexanders.mods.lop.init.Tiles
import de.ellpeck.rockbottom.api.RockBottomAPI
import de.ellpeck.rockbottom.api.tile.Tile
import de.ellpeck.rockbottom.api.world.gen.IWorldGenerator
import de.ellpeck.rockbottom.api.world.gen.WorldGenOre

class PearlOreGen : WorldGenOre(), IWorldGenerator {
    override fun getMaxAmount(): Int = 4

    override fun getOreMeta(): Int = 0

    override fun getHighestGridPos(): Int = -2
    
    override fun getLowestGridPos(): Int = -3

    override fun getOreTile(): Tile = Tiles.pearlOre

    override fun getClusterRadiusY(): Int = 6

    override fun getClusterRadiusX(): Int = 6

    override fun getPriority(): Int = 210

    fun register() {
        RockBottomAPI.WORLD_GENERATORS.add(this)
    }
}
