package uk.joshiejack.piscary.item;

import com.google.common.collect.Lists;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nonnull;
import java.util.List;

public class TreasureItem extends Item {
    public TreasureItem(Item.Properties properties) {
        super(properties);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    @Nonnull
    public ActionResult<ItemStack> use(@Nonnull World world, PlayerEntity player, @Nonnull Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!world.isClientSide) {
            LootContext.Builder lootcontext$builder = (new LootContext.Builder((ServerWorld) world))
                    .withParameter(LootParameters.ORIGIN, player.position())
                    .withParameter(LootParameters.TOOL, stack)
                    .withParameter(LootParameters.KILLER_ENTITY, player)
                    .withParameter(LootParameters.THIS_ENTITY, player)
                    .withRandom(player.getRandom())
                    .withLuck(player.getLuck());

            List<ItemStack> result = Lists.newArrayList();
            while (result.isEmpty()) {
                ResourceLocation table = LootTables.all().stream()
                        .skip(player.getRandom().nextInt(LootTables.all().size()))
                        .findFirst().orElse(LootTables.FISHING);
                LootTable loottable = world.getServer().getLootTables().get(table);
                result = loottable.getRandomItems(lootcontext$builder.create(LootParameterSets.FISHING));
            }

            result.forEach((itemstack) -> ItemHandlerHelper.giveItemToPlayer(player, itemstack));
        }

        stack.shrink(1);
        return ActionResult.success(stack);
    }
}
