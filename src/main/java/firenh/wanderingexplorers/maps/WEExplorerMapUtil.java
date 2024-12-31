package firenh.wanderingexplorers.maps;

import java.util.Optional;

import com.mojang.datafixers.util.Pair;

import firenh.wanderingexplorers.WanderingExplorers;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.map.MapDecorationType;
import net.minecraft.item.map.MapState;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;

public class WEExplorerMapUtil {
    public static Optional<ItemStack> createBiomeMapItem(ServerWorld world, RegistryEntry<Biome> biome, BlockPos origin,
            int searchRadius, RegistryEntry<MapDecorationType> decorationType) {
        WanderingExplorers.LOGGER.info("Searching for biome " + biome.getIdAsString());
        Pair<BlockPos, RegistryEntry<Biome>> located = world.locateBiome(b -> b.getIdAsString().equals(biome.getIdAsString()), origin, 6400, 64, 64);

        if (located == null) {
            return Optional.empty();
        }

        BlockPos blockPos = located.getFirst();

        ItemStack itemStack = FilledMapItem.createMap(world, blockPos.getX(), blockPos.getZ(), (byte) 2, true, true);
        FilledMapItem.fillExplorationMap(world, itemStack);
        MapState.addDecorationsNbt(itemStack, blockPos, "+", decorationType);
        itemStack.set(DataComponentTypes.ITEM_NAME,
                Text.translatable("biome." + biome.getKey().get().getValue().toTranslationKey()));

        return Optional.of(itemStack);
    }
}
