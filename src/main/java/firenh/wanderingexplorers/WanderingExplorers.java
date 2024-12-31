package firenh.wanderingexplorers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class WanderingExplorers implements ModInitializer {
	public static final String MOD_ID = "wandering_explorers";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
	}

	public static Identifier id(String id) {
		return Identifier.of(MOD_ID, id);
	}
}