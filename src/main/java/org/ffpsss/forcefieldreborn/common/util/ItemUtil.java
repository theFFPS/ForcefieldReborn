package org.ffpsss.forcefieldreborn.common.util;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemUtil {
    public final static Map<String, Item> REGISTERED_ITEMS = new HashMap<>();

    public static void register(Item item, String ID) {
        REGISTERED_ITEMS.put(ID, Registry.register(Registry.ITEM, new Identifier("forcefieldreborn", ID), item));
    }
}
