package alexanders.mods.lop.init


object Tiles {
    lateinit var phantomTile: alexanders.mods.lop.tile.PhantomTile
    lateinit var pearlOre: alexanders.mods.lop.tile.PearlOreTile
    lateinit var slime: alexanders.mods.lop.tile.SlimeTile

    fun init() {
        val lop = alexanders.mods.lop.LOP.Companion.instance
        if (lop.configManager.isEnabled("bridging")) {
            alexanders.mods.lop.init.Tiles.phantomTile = alexanders.mods.lop.tile.PhantomTile()
            alexanders.mods.lop.init.Tiles.phantomTile.register()
        }
        if (lop.configManager.isEnabled("ore")) {
            alexanders.mods.lop.init.Tiles.pearlOre = alexanders.mods.lop.tile.PearlOreTile()
            alexanders.mods.lop.init.Tiles.pearlOre.register()
        }
        if (lop.configManager.isEnabled("slime")) {
            alexanders.mods.lop.init.Tiles.slime = alexanders.mods.lop.tile.SlimeTile()
            alexanders.mods.lop.init.Tiles.slime.register()
        }
    }
}
