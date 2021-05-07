package uk.joshiejack.piscary.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.FollowBoatGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class PiranhaEntity extends SolitaryFishEntity {
    public PiranhaEntity(EntityType<? extends SolitaryFishEntity> entity, World world) {
        super(entity, world);
    }

    @Override
    protected void registerGoals() {
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2F, true));
        this.goalSelector.addGoal(3, new FollowBoatGoal(this));
        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.0D, 40));
    }
}
