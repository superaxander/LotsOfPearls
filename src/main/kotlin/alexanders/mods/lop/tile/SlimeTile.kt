package alexanders.mods.lop.tile

import alexanders.mods.lop.init.Resources
import de.ellpeck.rockbottom.api.entity.Entity
import de.ellpeck.rockbottom.api.item.ItemInstance
import de.ellpeck.rockbottom.api.item.ToolType
import de.ellpeck.rockbottom.api.tile.Tile
import de.ellpeck.rockbottom.api.tile.TileBasic
import de.ellpeck.rockbottom.api.world.IWorld
import de.ellpeck.rockbottom.api.world.TileLayer


class SlimeTile : TileBasic(Resources.SLIME_RESOURCE) {
    init {
        this.setHardness(.5f)
        this.addEffectiveTool(ToolType.PICKAXE, 0)
    }

    override fun getDrops(world: IWorld?, x: Int, y: Int, layer: TileLayer?, destroyer: Entity?): MutableList<ItemInstance> {
        return mutableListOf(ItemInstance(item))
    }

    override fun register(): Tile {
        return super.register()
    }
}
