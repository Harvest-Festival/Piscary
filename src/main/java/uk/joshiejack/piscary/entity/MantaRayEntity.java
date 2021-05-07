package uk.joshiejack.piscary.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class MantaRayEntity extends SolitaryFishEntity {
    public MantaRayEntity(EntityType<? extends MantaRayEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected boolean canRide(Entity entity) {
        return true;
    }
}
