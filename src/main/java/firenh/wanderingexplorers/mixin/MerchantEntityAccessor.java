package firenh.wanderingexplorers.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.village.TradeOfferList;
import net.minecraft.village.TradeOffers;

@Mixin(MerchantEntity.class)
public interface MerchantEntityAccessor {
    @Invoker("fillRecipesFromPool")
    public void fillRecipesFromPool_invoker(TradeOfferList recipeList, TradeOffers.Factory[] pool, int count);
}
