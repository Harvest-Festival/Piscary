package uk.joshiejack.piscary.entity.shoaling;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class SardineEntity extends ShoalingFishEntity {
    public SardineEntity(EntityType<? extends ShoalingFishEntity> entity, World world) {
        super(entity, world);
    }

    @Override
    public int getMaxSchoolSize() {
        return 16;
    }
}
