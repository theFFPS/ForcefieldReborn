package org.ffpsss.forcefieldreborn;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.block.OreBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreConfiguredFeatures;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

import java.util.Arrays;

import org.ffpsss.forcefieldreborn.block.capacitor.*;
import org.ffpsss.forcefieldreborn.block.controller.*;
import org.ffpsss.forcefieldreborn.common.util.BlockUtil;
import org.ffpsss.forcefieldreborn.common.util.ItemUtil;
import org.ffpsss.forcefieldreborn.item.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ForcefieldReborn implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("forcefieldreborn");
	public static ItemGroup ITEM_GROUP;
	public static ConfiguredFeature<?, ?> ORE_CONFIG_FEATURE;

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
			new ExtensionController(FabricBlockSettings.of(Material.METAL).strength(6.0f).requiresTool()), 
			"extension_controller", 
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
		
		ORE_CONFIG_FEATURE = new ConfiguredFeature(Feature.ORE, new OreFeatureConfig(
			OreConfiguredFeatures.STONE_ORE_REPLACEABLES, 
			BlockUtil.REGISTERED_BLOCKS.get("forcicium_ore").getDefaultState(), 
			4
		));
		
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("forcefieldreborn", "overworld_ore"), ORE_CONFIG_FEATURE);
		Registry.register(
			BuiltinRegistries.PLACED_FEATURE, 
			new Identifier("forcefieldreborn", "overworld_ore"), 
			new PlacedFeature(RegistryEntry.of(ORE_CONFIG_FEATURE), Arrays.asList(
				CountPlacementModifier.of(4),
				SquarePlacementModifier.of(),
				HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(20))
			))
		);
		BiomeModifications.addFeature(
			BiomeSelectors.foundInOverworld(), 
			GenerationStep.Feature.UNDERGROUND_ORES, 
			RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier("forcefieldreborn", "overworld_ore"))
		);
	}
}