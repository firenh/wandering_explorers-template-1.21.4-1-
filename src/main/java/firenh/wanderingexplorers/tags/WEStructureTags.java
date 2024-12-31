package firenh.wanderingexplorers.tags;

import firenh.wanderingexplorers.WanderingExplorers;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.gen.structure.Structure;

public class WEStructureTags {
    public static final TagKey<Structure> PALE_GARDEN = of("biome/pale_garden");

    private static TagKey<Structure> of(String id) {
        return TagKey.of(RegistryKeys.STRUCTURE, WanderingExplorers.id(id));
    }
}
