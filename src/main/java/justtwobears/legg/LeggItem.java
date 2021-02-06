package justtwobears.legg;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SwordItem;
import net.minecraft.item.Vanishable;

public class LeggItem extends SwordItem implements Vanishable {
    public LeggItem() {
        super(LeggToolMaterials.ZOMBIE_LEG, 5, -3.0F, new FabricItemSettings().group(ItemGroup.COMBAT));
    }
}
