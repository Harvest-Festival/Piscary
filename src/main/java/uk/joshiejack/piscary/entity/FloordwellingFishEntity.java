package uk.joshiejack.piscary.entity;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.pathfinding.PathType;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class FloordwellingFishEntity extends SolitaryFishEntity {
    public FloordwellingFishEntity(EntityType<? extends FloordwellingFishEntity> type, World world) {
        super(type, world);
        this.setPathfindingMalus(PathNodeType.WATER, 0.0F);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, PlayerEntity.class, 4F, 0.8D, 0.7D, EntityPredicates.NO_SPECTATORS::test));
        this.goalSelector.addGoal(4, new SwimGoal(this));
    }

    static class SwimGoal extends RandomSwimmingGoal {
        private final AbstractFishEntity fish;

        public SwimGoal(AbstractFishEntity p_i48856_1_) {
            super(p_i48856_1_, 1.0D, 40);
            this.fish = p_i48856_1_;
        }

        @Nullable
        protected Vector3d getPosition() {
            Vector3d vector3d = RandomPositionGenerator.getPos(this.mob, 1, 1);
            for (int i = 0; vector3d != null && !this.mob.level.getBlockState(new BlockPos(vector3d)).isPathfindable(this.mob.level, new BlockPos(vector3d), PathType.WATER) &&
                    this.mob.level.getBlockState(new BlockPos(vector3d).below()).getMaterial() == Material.WATER && i++ < 10; vector3d = RandomPositionGenerator.getPos(this.mob, 10, 1)) {
            }

            return vector3d;
        }
    }
}
