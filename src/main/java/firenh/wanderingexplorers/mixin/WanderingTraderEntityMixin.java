package firenh.wanderingexplorers.mixin;

import java.util.ArrayList;
import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import firenh.wanderingexplorers.trades.BiomePredicates;
import firenh.wanderingexplorers.trades.SellBiomeMapFactory;
import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;
import net.minecraft.village.TradeOffers;

@Mixin(WanderingTraderEntity.class)
public class WanderingTraderEntityMixin {
	@Inject(at = @At("RETURN"), method = "fillRecipes")
	private void fillRecipes_inject(CallbackInfo info) {
		TradeOfferList tradeOfferList = ((WanderingTraderEntity)(Object)this).getOffers();
		ArrayList<TradeOffers.Factory> factories = new ArrayList<>();
		
		factories.addAll(SellBiomeMapFactory.createFactories(((WanderingTraderEntity)(Object)this).getRegistryManager(), p -> true, 3, ((WanderingTraderEntity)(Object)this).getRandom(), 3));
		// if (((WanderingTraderEntity)(Object)this).getRandom().nextBoolean()) factories.addAll(SellBiomeMapFactory.createFactories(((WanderingTraderEntity)(Object)this).getRegistryManager(), BiomePredicates.IS_CAVE_BIOME, 1, ((WanderingTraderEntity)(Object)this).getRandom(), 3));
		// factories.addAll(SellBiomeMapFactory.createFactories(((WanderingTraderEntity)(Object)this).getRegistryManager(), BiomePredicates.IS_SEEKED, ((WanderingTraderEntity)(Object)this).getRandom().nextBetween(1, 2), ((WanderingTraderEntity)(Object)this).getRandom(), 3));

		
		for (TradeOffers.Factory factory : factories) {
			TradeOffer offer = factory.create((WanderingTraderEntity)(Object)this, ((WanderingTraderEntity)(Object)this).getRandom());
			if (offer.getSellItem().isOf(Items.MAP)) continue;

			tradeOfferList.add(offer);
		}
	}
}