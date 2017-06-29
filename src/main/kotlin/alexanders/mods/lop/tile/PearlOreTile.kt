package alexanders.mods.lop.tile

import alexanders.mods.lop.init.Items
import alexanders.mods.lop.init.Resources
import de.ellpeck.rockbottom.api.entity.Entity
import de.ellpeck.rockbottom.api.item.ItemInstance
import de.ellpeck.rockbottom.api.item.ToolType
import de.ellpeck.rockbottom.api.tile.TileBasic
import de.ellpeck.rockbottom.api.world.IWorld
import de.ellpeck.rockbottom.api.world.TileLayer


class PearlOreTile : TileBasic(Resources.PEARL_ORE_RESOURCE) {
    init {
        this.setHardness(5f)
        addEffectiveTool(ToolType.PICKAXE, 2)
    }

    override fun getDrops(world: IWorld, x: Int, y: Int, layer: TileLayer, destroyer: Entity) = mutableListOf(ItemInstance(Items.pearlItem))
}
