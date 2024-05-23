package org.ffpsss.forcefieldreborn;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.block.OreBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

import org.ffpsss.forcefieldreborn.block.capacitor.*;
import org.ffpsss.forcefieldreborn.block.extension.*;
import org.ffpsss.forcefieldreborn.common.util.BlockUtil;
import org.ffpsss.forcefieldreborn.common.util.ItemUtil;
import org.ffpsss.forcefieldreborn.item.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ForcefieldReborn implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("forcefieldreborn");
	public static ItemGroup ITEM_GROUP;

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
		
		ITEM_GROUP = FabricItemGroupBuilder.create(new Identifier("forcefieldreborn", "forcefieldreborn_group")).icon(() -> new ItemStack(
			ItemUtil.REGISTERED_ITEMS.get("field_projector_unit")
		)).build();

		BlockUtil.register(
			new BasicCapacitor(FabricBlockSettings.of(Material.METAL).strength(6.0f).requiresTool()), 
			"basic_capacitor", 
			true
		);
		BlockUtil.register(
			new AdvancedCapacitor(FabricBlockSettings.of(Material.METAL).strength(6.0f).requiresTool()), 
			"advanced_capacitor", 
			true
		);
		BlockUtil.register(
			new HighAdvancedCapacitor(FabricBlockSettings.of(Material.METAL).strength(6.0f).requiresTool()), 
			"high_advanced_capacitor", 
			true
		);

		BlockUtil.register(
			new InventoryDropExtension(FabricBlockSettings.of(Material.METAL).strength(6.0f).requiresTool()), 
			"inventory_drop_extension", 
			true
		);

		BlockUtil.register(
			new OreBlock(
				FabricBlockSettings.of(Material.METAL).strength(8.0f).requiresTool(),
				UniformIntProvider.create(5, 9)
			), 
			"forcicium_ore", 
			true
		);

		ItemUtil.register(
			new FieldProjectorUnit(new Item.Settings().maxCount(64).group(ForcefieldReborn.ITEM_GROUP)),
			"field_projector_unit"
		);
		ItemUtil.register(
			new Forcicium(new Item.Settings().maxCount(64).group(ForcefieldReborn.ITEM_GROUP)),
			"forcicium"
		);
	}
}