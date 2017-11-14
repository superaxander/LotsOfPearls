package alexanders.mods.lop.render

import alexanders.mods.lop.init.Resources.COOLDOWN_RESOURCE
import de.ellpeck.rockbottom.api.IGameInstance
import de.ellpeck.rockbottom.api.IGraphics
import de.ellpeck.rockbottom.api.assets.IAssetManager
import de.ellpeck.rockbottom.api.item.Item
import de.ellpeck.rockbottom.api.item.ItemInstance
import de.ellpeck.rockbottom.api.render.item.DefaultItemRenderer
import de.ellpeck.rockbottom.api.util.reg.IResourceName
import org.newdawn.slick.Color
import org.newdawn.slick.Graphics


class PearlItemRenderer(resourceName: IResourceName) : DefaultItemRenderer<Item>(resourceName) {
    override fun render(game: IGameInstance, manager: IAssetManager, g: IGraphics, item: Item, instance: ItemInstance, x: Float, y: Float, scale: Float, filter: Int) {
        super.render(game, manager, g, item, instance, x, y, scale, filter)
        if (instance.amount == -1)
            return

        if (instance.additionalData != null && instance.additionalData.getInt("cooldown") > 0) {
            val image = manager.getTexture(COOLDOWN_RESOURCE)
            image.draw(x, y, scale, (scale / 60 * instance.additionalData.getInt("cooldown")), filter)
        }
    }
}
