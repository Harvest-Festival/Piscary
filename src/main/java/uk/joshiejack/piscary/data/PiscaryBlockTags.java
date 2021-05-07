package uk.joshiejack.piscary.data;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import uk.joshiejack.piscary.Piscary;

import javax.annotation.Nullable;

public class PiscaryBlockTags extends BlockTagsProvider {
    public PiscaryBlockTags(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, Piscary.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {}
}
