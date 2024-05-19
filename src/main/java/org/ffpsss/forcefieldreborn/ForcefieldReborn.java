package org.ffpsss.forcefieldreborn;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ForcefieldReborn implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("forcefieldreborn");

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
	}
}