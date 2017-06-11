package alexanders.mods.lop.render

import alexanders.mods.lop.LOP
import de.ellpeck.rockbottom.api.IGameInstance
import de.ellpeck.rockbottom.api.assets.IAssetManager
import de.ellpeck.rockbottom.api.item.Item
import de.ellpeck.rockbottom.api.item.ItemInstance
import de.ellpeck.rockbottom.api.render.item.DefaultItemRenderer
import de.ellpeck.rockbottom.api.util.reg.IResourceName
import org.newdawn.slick.Color
import org.newdawn.slick.Graphics


class PearlItemRenderer(resourceName: IResourceName) : DefaultItemRenderer(resourceName) {
    override fun render(game: IGameInstance, manager: IAssetManager, g: Graphics, item: Item, instance: ItemInstance, x: Float, y: Float, scale: Float, filter: Color) {
        super.render(game, manager, g, item, instance, x, y, scale, filter)
        if (instance.amount == -1)
            return

        if (instance.additionalData != null && instance.additionalData.getInt("cooldown") > 0) {
            val image = manager.getImage(LOP.instance.COOLDOWN_RESOURCE)
            image.alpha = .5f
            image.draw(x, y, scale, (scale / 60 * instance.additionalData.getInt("cooldown")), filter)
        }
    }
}
