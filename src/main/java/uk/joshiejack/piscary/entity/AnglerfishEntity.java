package uk.joshiejack.piscary.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class AnglerfishEntity extends SolitaryFishEntity {
    public AnglerfishEntity(EntityType<? extends SolitaryFishEntity> entity, World world) {
        super(entity, world);
    }
}
