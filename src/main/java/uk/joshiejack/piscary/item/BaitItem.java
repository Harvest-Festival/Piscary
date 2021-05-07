package uk.joshiejack.piscary.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import uk.joshiejack.piscary.Piscary;

import javax.annotation.Nonnull;

public class BaitItem extends Item {
    public BaitItem() {
        super(new Item.Properties().tab(Piscary.TAB));
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> use(@Nonnull World worldIn, PlayerEntity player, @Nonnull Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        int slot = getSlotStackIsIn(player.inventory.items, stack);
        if (slot != -1) {
            ItemStack rod = getClosest(player.inventory.items, slot);
            if (rod != null && addBait(rod, stack)) {
                stack.setCount(0); //Clear it out
                return ActionResult.success(stack);
            }
        }

        return ActionResult.pass(stack);
    }

    @SuppressWarnings("ConstantConditions")
    private boolean addBait(ItemStack rod, ItemStack bait) {
        CompoundNBT tag = rod.hasTag() ? rod.getTag() : new CompoundNBT();
        ItemStack stack = ItemStack.of(tag);
        if (!stack.isEmpty() && !ItemStack.isSame(stack, bait)) return false;
        int existing = tag.getInt("Bait");
        if (existing + bait.getCount() > 999) return false;
        else {
            ItemStack baitSaveStack = bait.copy();
            baitSaveStack.setCount(1);
            tag.put("Stack", baitSaveStack.save(new CompoundNBT()));
            tag.putInt("Bait", existing + bait.getCount());
            rod.setTag(tag); //Reset the tag
            return true;
        }
    }

    private int getSlotStackIsIn(NonNullList<ItemStack> mainInventory, ItemStack stack) {
        for (int i = 0; i < 9; i++) {
            if (mainInventory.get(i) == stack) return i;
        }

        return -1;
    }

    private ItemStack getClosest(NonNullList<ItemStack> mainInventory, int slot) {
        //Check to the right first
        int check = slot == 8 ? 0 : slot + 1;
        ItemStack stack = mainInventory.get(check);
        if (stack.getItem() instanceof FishingRodItem) return stack;
        else {
            check = slot == 0 ? 8 : slot + -1;
            stack = mainInventory.get(check);
            if (stack.getItem() instanceof FishingRodItem) return stack;
            else return null;
        }
    }
}
