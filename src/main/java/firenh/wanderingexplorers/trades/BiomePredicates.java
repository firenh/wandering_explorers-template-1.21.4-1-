package firenh.wanderingexplorers.trades;

import java.util.function.Predicate;

import firenh.wanderingexplorers.util.WEBiomeTags;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.fabricmc.fabric.impl.biome.modification.BuiltInRegistryKeys;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.biome.Biome;

public class BiomePredicates {
    // public static final Predicate<? super RegistryKey<Biome>> IS_CAVE_BIOME = biome -> {
    //     RegistryEntry<Biome> biomeEntry = BuiltInRegistryKeys.biomeRegistryWrapper().getOrThrow(biome);
    //     return biomeEntry.isIn(ConventionalBiomeTags.IS_CAVE);
    // };

    public static final Predicate<? super RegistryKey<Biome>> IS_SEEKED = biome -> {
        RegistryEntry<Biome> biomeEntry = BuiltInRegistryKeys.biomeRegistryWrapper().getOrThrow(biome);
        return biomeEntry.isIn(WEBiomeTags.SEEKED_BIOMES);
    };
}
