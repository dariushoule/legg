package justtwobears.legg;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.item.Items;
import net.minecraft.loot.ConstantLootTableRange;
import net.minecraft.loot.UniformLootTableRange;
import net.minecraft.loot.condition.KilledByPlayerLootCondition;
import net.minecraft.loot.condition.RandomChanceWithLootingLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Legg implements ModInitializer {
    private static final Identifier ZOMBIE_LOOT_TABLE_ID = new Identifier("minecraft", "entities/zombie");

    @Override
    public void onInitialize() {
        LeggItem leggItem = new LeggItem();
        Registry.register(Registry.ITEM, new Identifier("legg", "legg_item"), leggItem);

        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if (ZOMBIE_LOOT_TABLE_ID.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .with(ItemEntry.builder(leggItem)
                                .apply(SetCountLootFunction.builder(UniformLootTableRange.between(0.0F, 1.0F))))
                        .rolls(ConstantLootTableRange.create(1))
                        .conditionally(KilledByPlayerLootCondition.builder())
                        .conditionally(RandomChanceWithLootingLootCondition.builder(0.2F, 0.1F));
                supplier.pool(poolBuilder);
            }
        });
    }
}
