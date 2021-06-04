package uk.joshiejack.piscary.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.items.ItemHandlerHelper;
import uk.joshiejack.penguinlib.network.PenguinNetwork;
import uk.joshiejack.penguinlib.network.packet.SetHeldItemPacket;
import uk.joshiejack.penguinlib.util.helpers.generic.ReflectionHelper;
import uk.joshiejack.piscary.Piscary;
import uk.joshiejack.piscary.crafting.BaitRegistry;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(modid = Piscary.MODID)
public class BaitHandler {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void removeBaitAfterFishing(ItemFishedEvent event) {
        PlayerEntity player = event.getPlayer();
        if (player == null) return;
        removeBait(player, getHand(player), 1);
    }

    @SubscribeEvent
    public static void removeBaitFromFishingRod(PlayerInteractEvent.RightClickItem event) {
        PlayerEntity player = event.getPlayer();
        if (!player.isShiftKeyDown()) return;
        removeBait(player, getHand(player), 64);
        event.setCancellationResult(ActionResultType.SUCCESS);
        event.setCanceled(true);
    }

    @SuppressWarnings("ConstantConditions")
    @SubscribeEvent
    public static void applyBaitModifiers(EntityJoinWorldEvent event) {
        if (!event.getWorld().isClientSide() || !(event.getEntity() instanceof FishingBobberEntity)) return;
        FishingBobberEntity bobber = (FishingBobberEntity) event.getEntity();
        PlayerEntity player = bobber.getPlayerOwner();
        if (player == null) return;
        ItemStack fishingRod = getFishingRodFromPlayer(player);
        if (!fishingRod.isEmpty()) return;
        ItemStack bait = getBaitStack(fishingRod);
        if (!bait.isEmpty()) return;
        BaitRegistry.BaitData data = BaitRegistry.getValue(bait);
        int enchantability = fishingRod.getItemEnchantability();
        int speedBoost = enchantability * data.getSpeed() + 35;
        int luckBoost = data.getLuck();
        //Attempts here we go //TODO TEST THESE
        int speed = ObfuscationReflectionHelper.getPrivateValue(FishingBobberEntity.class, bobber, "field_191519_ax");
        int luck = ObfuscationReflectionHelper.getPrivateValue(FishingBobberEntity.class, bobber, "field_191518_aw");
        ReflectionHelper.setPrivateFinalValue(FishingBobberEntity.class, bobber, speed + enchantability * speedBoost, "field_191519_ax");
        ReflectionHelper.setPrivateFinalValue(FishingBobberEntity.class, bobber, luck + luckBoost, "field_191518_aw");
    }

    private static ItemStack getFishingRodFromPlayer(PlayerEntity player) {
        return player.getMainHandItem().getItem() instanceof FishingRodItem ? player.getMainHandItem()
                : player.getOffhandItem().getItem() instanceof FishingRodItem ? player.getOffhandItem() : ItemStack.EMPTY;
    }

    private static Hand getHand(PlayerEntity player) {
        return player.getMainHandItem().getItem() instanceof FishingRodItem ? Hand.MAIN_HAND : player.getOffhandItem().getItem() instanceof FishingRodItem ? Hand.OFF_HAND : null;
    }

    @SuppressWarnings("ConstantConditions")
    private static void removeBait(PlayerEntity player, @Nullable Hand hand, int amount) {
        if (hand == null) return;
        ItemStack rod = player.getItemInHand(hand);
        CompoundNBT tag = rod.hasTag() ? rod.getTag() : new CompoundNBT();
        int existing = tag.getInt("Bait");
        int newValue = existing - amount;
        if (newValue < 0) tag.remove("Bait");
        else tag.putInt("Bait", newValue);

        rod.setTag(tag); //Reset the tag
        if (amount > 1 && existing > 0) {
            ItemStack ret = ItemStack.of(tag.getCompound("Stack"));
            if (newValue < 0) ret.setCount(amount + newValue);
            else ret.setCount(amount);
            ItemHandlerHelper.giveItemToPlayer(player, ret);
        }

        player.setItemInHand(hand, rod);
        if (!player.level.isClientSide)
            PenguinNetwork.sendToClient(new SetHeldItemPacket(hand, rod), (ServerPlayerEntity) player);
    }

    @SuppressWarnings("ConstantConditions")
    public static ItemStack getBaitStack(ItemStack stack) {
        return stack.hasTag() ? ItemStack.of(stack.getTag().getCompound("Stack")) : ItemStack.EMPTY;
    }
}
