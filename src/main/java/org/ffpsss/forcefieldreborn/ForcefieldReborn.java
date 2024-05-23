package org.ffpsss.forcefieldreborn;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import org.ffpsss.forcefieldreborn.block.capacitor.*;
import org.ffpsss.forcefieldreborn.block.extension.*;
import org.ffpsss.forcefieldreborn.common.util.BlockUtil;
import org.ffpsss.forcefieldreborn.common.util.ItemUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ForcefieldReborn implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("forcefieldreborn");
	public static ItemGroup ITEM_GROUP;

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
		
		ITEM_GROUP = FabricItemGroupBuilder.create(new Identifier("forcefieldreborn", "forcefieldreborn_group")).icon(() -> new ItemStack(
			BlockUtil.REGISTERED_BLOCK_ITEMS.get("basic_field_generator")
		)).build();

		BlockUtil.register(
			new BasicCapacitor(FabricBlockSettings.of(Material.METAL).strength(4.0f).requiresTool()), 
			"basic_capacitor", 
			true
		);
		BlockUtil.register(
			new AdvancedCapacitor(FabricBlockSettings.of(Material.METAL).strength(4.0f).requiresTool()), 
			"advanced_capacitor", 
			true
		);
		BlockUtil.register(
			new HighAdvancedCapacitor(FabricBlockSettings.of(Material.METAL).strength(4.0f).requiresTool()), 
			"high_advanced_capacitor", 
			true
		);

		BlockUtil.register(
			new InventoryDropExtension(FabricBlockSettings.of(Material.METAL).strength(4.0f).requiresTool()), 
			"inventory_drop_extension", 
			true
		);
	}
}