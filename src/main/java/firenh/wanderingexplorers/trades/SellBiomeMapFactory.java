package firenh.wanderingexplorers.trades;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.jetbrains.annotations.Nullable;

import firenh.wanderingexplorers.maps.WEExplorerMapUtil;
import net.fabricmc.fabric.impl.biome.modification.BuiltInRegistryKeys;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.map.MapDecorationType;
import net.minecraft.item.map.MapDecorationTypes;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.TradedItem;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

public class SellBiomeMapFactory implements TradeOffers.Factory {
    private final int price;
    private final RegistryEntry<Biome> biome;
    private final RegistryEntry<MapDecorationType> decoration;
    private final int maxUses;
    private final int experience;

    public SellBiomeMapFactory(int price, RegistryEntry<Biome> biome,
            RegistryEntry<MapDecorationType> decoration, int maxUses, int experience) {
        this.price = price;
        this.biome = biome;
        this.decoration = decoration;
        this.maxUses = maxUses;
        this.experience = experience;
    }

    public SellBiomeMapFactory(int price, RegistryKey<Biome> biome,
            RegistryEntry<MapDecorationType> decoration, int maxUses, int experience) {
        this.price = price;
        this.decoration = decoration;
        this.maxUses = maxUses;
        this.experience = experience;
        this.biome = BuiltInRegistryKeys.biomeRegistryWrapper().getOrThrow(biome);
    }



    @Nullable
    @Override
    public TradeOffer create(Entity entity, Random random) {
        if (!(entity.getWorld() instanceof ServerWorld)) {
            return null;
        } else {
            Optional<ItemStack> maybeStack = WEExplorerMapUtil.createBiomeMapItem((ServerWorld) entity.getWorld(),
                    biome, new BlockPos((int) entity.getX(), (int) entity.getY(), (int) entity.getZ()), 6400,
                    decoration);
            ItemStack itemStack = Items.MAP.getDefaultStack();
            if (maybeStack.isPresent())
                itemStack = maybeStack.get();

            return new TradeOffer(
                    new TradedItem(Items.EMERALD, this.price), Optional.of(new TradedItem(Items.COMPASS)),
                    itemStack, this.maxUses, this.experience, 0.2F);
        }
    }

    public static List<TradeOffers.Factory> createFactories(DynamicRegistryManager manager, Predicate<? super RegistryKey<Biome>> predicate, int count, Random random, int price) {
        List<RegistryKey<Biome>> biomeList = manager.getOrThrow(RegistryKeys.BIOME).streamKeys().filter(predicate).toList();
        int size = biomeList.size();

        ArrayList<TradeOffers.Factory> returnList = new ArrayList<>();

        for (int i = 0; i < count; i += 1) {
            returnList.add(new SellBiomeMapFactory(price, biomeList.get(random.nextInt(size)), MapDecorationTypes.TARGET_X, 1, 0));
        }

        return returnList;
    }
}
