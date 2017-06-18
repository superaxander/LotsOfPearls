package alexanders.mods.lop.init

import alexanders.mods.lop.LOP
import de.ellpeck.rockbottom.api.RockBottomAPI
import de.ellpeck.rockbottom.api.construction.BasicRecipe
import de.ellpeck.rockbottom.api.item.ItemInstance


object Recipes {
    fun init() {
        @Suppress("UNCHECKED_CAST")
        //MANUAL_RECIPES = javaClass.classLoader.loadClass("de.ellpeck.rockbottom.construction.ConstructionRegistry").getField("MANUAL_RECIPES").get(null) as MutableList<BasicRecipe>
        if (LOP.instance.configManager.isEnabled("pearl")) {
            if (LOP.instance.configManager.isEnabled("spiky"))
                Recipes.addRecipe(BasicRecipe(ItemInstance(Items.spikyPearlItem), ItemInstance(Items.pearlItem), ItemInstance(RockBottomAPI.ITEM_REGISTRY[RockBottomAPI.createInternalRes("rock")], 4)))
            if (LOP.instance.configManager.isEnabled("bouncy") && LOP.instance.configManager.isEnabled("slime"))
                Recipes.addRecipe(BasicRecipe(ItemInstance(Items.bouncyPearlItem), ItemInstance(Items.pearlItem), ItemInstance(Tiles.slime, 4)))
            if (LOP.instance.configManager.isEnabled("mining"))
                Recipes.addRecipe(BasicRecipe(ItemInstance(Items.miningPearlItem), ItemInstance(Items.pearlItem), ItemInstance(RockBottomAPI.ITEM_REGISTRY[RockBottomAPI.createInternalRes("pick_rock")], 2)))
            if (LOP.instance.configManager.isEnabled("bridging"))
                Recipes.addRecipe(BasicRecipe(ItemInstance(Items.bridgingPearlItem), ItemInstance(Items.pearlItem), ItemInstance(RockBottomAPI.ITEM_REGISTRY[RockBottomAPI.createInternalRes("dirt")], 6)))
            if (LOP.instance.configManager.isEnabled("spawn"))
                Recipes.addRecipe(BasicRecipe(ItemInstance(Items.spawnPearlItem), ItemInstance(Items.pearlItem), ItemInstance(RockBottomAPI.ITEM_REGISTRY[RockBottomAPI.createInternalRes("copper_grit")])))
            if (LOP.instance.configManager.isEnabled("waypoint"))
                Recipes.addRecipe(BasicRecipe(ItemInstance(Items.waypointPearlItem), ItemInstance(Items.pearlItem), ItemInstance(RockBottomAPI.ITEM_REGISTRY[RockBottomAPI.createInternalRes("copper_ingot")])))
            if (LOP.instance.configManager.isEnabled("rideable"))
                Recipes.addRecipe(BasicRecipe(ItemInstance(Items.rideablePearlItem), ItemInstance(Items.pearlItem), ItemInstance(RockBottomAPI.ITEM_REGISTRY[RockBottomAPI.createInternalRes("slag")])))
        }
        //addRecipe(BasicRecipe(ItemInstance(Items.bouncyPearlItem), ItemInstance(Items.pearlItem), ItemInstance(RockBottomAPI.ITEM_REGISTRY[RockBottomAPI.createInternalRes("rock")], 4)))
    }

    //private lateinit var MANUAL_RECIPES: MutableList<BasicRecipe>

    private fun addRecipe(recipe: BasicRecipe) {
        RockBottomAPI.MANUAL_CONSTRUCTION_RECIPES.add(recipe)
    }
}
