package org.ffpsss.forcefieldreborn.common.util;

import java.util.HashMap;
import java.util.Map;

import org.ffpsss.forcefieldreborn.ForcefieldReborn;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder.Factory;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockUtil {
    public final static Map<String, Block> REGISTERED_BLOCKS = new HashMap<>();
    public final static Map<String, Item> REGISTERED_BLOCK_ITEMS = new HashMap<>();
    public final static Map<String, BlockEntityType<? extends BlockEntity>> REGISTERED_BLOCK_ENTITIES = new HashMap<>();

    public static void register(Block block, String ID, boolean item) {
        REGISTERED_BLOCKS.put(ID, Registry.register(Registry.BLOCK, new Identifier("forcefieldreborn", ID), block));
        if (item) 
            REGISTERED_BLOCK_ITEMS.put(
                ID, 
                Registry.register(
                    Registry.ITEM, 
                    new Identifier("forcefieldreborn", ID), 
                    new BlockItem(block, new Item.Settings().maxCount(64).group(ForcefieldReborn.ITEM_GROUP))
                )
            );
    }
    public static void assignBlockEntity(String ID, Factory<? extends BlockEntity> factory) {
        REGISTERED_BLOCK_ENTITIES.put(ID, Registry.register(
			Registry.BLOCK_ENTITY_TYPE,
			new Identifier("forcefieldreborn", ID),
			FabricBlockEntityTypeBuilder.create(factory, BlockUtil.REGISTERED_BLOCKS.get(ID)).build()
		));
    }
}
