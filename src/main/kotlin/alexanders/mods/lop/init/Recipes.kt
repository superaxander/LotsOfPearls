package alexanders.mods.lop.init

import alexanders.mods.lop.LOP
import alexanders.mods.lop.of
import de.ellpeck.rockbottom.api.GameContent
import de.ellpeck.rockbottom.api.RockBottomAPI
import de.ellpeck.rockbottom.api.construction.BasicRecipe
import de.ellpeck.rockbottom.api.construction.resource.ResInfo
import de.ellpeck.rockbottom.api.construction.resource.ResourceRegistry.*
import de.ellpeck.rockbottom.api.item.ItemInstance


object Recipes {
    fun init() { /*
        if (LOP.instance.configManager.isEnabled("pearl")) {
            val pearl = "pearl"
            if (LOP.instance.configManager.isEnabled("spiky")) {
                addRecipe(BasicRecipe(ItemInstance(Items.spikyPearlItem), 1 of pearl, 4 of RAW_STONE))
            }
            if (LOP.instance.configManager.isEnabled("bouncy") && LOP.instance.configManager.isEnabled("slime")) {
                val slime = "slime"
                addRecipe(BasicRecipe(ItemInstance(Items.bouncyPearlItem), 1 of pearl, 4 of slime))
            }
            if (LOP.instance.configManager.isEnabled("mining")) {
                val pickaxeRock = "pickaxe_rock"
                addResources(pickaxeRock, ResInfo(GameContent.ITEM_STONE_PICKAXE))
                addRecipe(BasicRecipe(ItemInstance(Items.miningPearlItem), 1 of pearl, 2 of pickaxeRock))
            }
            if (LOP.instance.configManager.isEnabled("bridging")) {
                val dirt = "dirt"
                addResources(dirt, ResInfo(GameContent.TILE_DIRT))
                addRecipe(BasicRecipe(ItemInstance(Items.bridgingPearlItem), 1 of pearl, 6 of dirt))
            }
            if (LOP.instance.configManager.isEnabled("spawn")) {
                addRecipe(BasicRecipe(ItemInstance(Items.spawnPearlItem), 1 of pearl, 1 of PARTLY_PROCESSED_COPPER))
            }
            if (LOP.instance.configManager.isEnabled("waypoint")) {
                addRecipe(BasicRecipe(ItemInstance(Items.waypointPearlItem), 1 of pearl, 1 of PROCESSED_COPPER))
            }
            if (LOP.instance.configManager.isEnabled("rideable")) {
                addRecipe(BasicRecipe(ItemInstance(Items.rideablePearlItem), 1 of pearl, 1 of SLAG))
            }


        }*/
        //addRecipe(BasicRecipe(ItemInstance(Items.bouncyPearlItem), ItemInstance(Items.pearlItem), ItemInstance(RockBottomAPI.ITEM_REGISTRY[RockBottomAPI.createInternalRes("rock")], 4)))
    }

    //private fun addRecipe(recipe: BasicRecipe) {
        //RockBottomAPI.MANUAL_CONSTRUCTION_RECIPES.register(recipe)
    //}
}
