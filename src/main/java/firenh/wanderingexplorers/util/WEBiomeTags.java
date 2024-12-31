package firenh.wanderingexplorers.util;

import firenh.wanderingexplorers.WanderingExplorers;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.biome.Biome;

public class WEBiomeTags {
    public static final TagKey<Biome> SEEKED_BIOMES = of("seeked_biomes");

    private static TagKey<Biome> of(String id) {
		return TagKey.of(RegistryKeys.BIOME, WanderingExplorers.id(id));
	}
}
