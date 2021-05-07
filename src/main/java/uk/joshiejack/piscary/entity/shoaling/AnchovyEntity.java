package uk.joshiejack.piscary.entity.shoaling;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class AnchovyEntity extends ShoalingFishEntity {
    public AnchovyEntity(EntityType<? extends ShoalingFishEntity> entity, World world) {
        super(entity, world);
    }

    @Override
    public int getMaxSchoolSize() {
        return 24;
    }
}
