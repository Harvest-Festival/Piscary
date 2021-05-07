package uk.joshiejack.piscary.client.data;

import joptsimple.internal.Strings;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import uk.joshiejack.piscary.Piscary;
import uk.joshiejack.piscary.entity.PiscaryEntities;
import uk.joshiejack.piscary.item.PiscaryItems;

import java.util.Objects;

public class PiscaryItemModels extends ItemModelProvider {
    public PiscaryItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Piscary.MODID, existingFileHelper);
    }

    private void registerModels(DeferredRegister<Item> items) {
        items.getEntries().stream()
                .map(RegistryObject::get)
                .forEach(item -> {
                    String path = Objects.requireNonNull(item.getRegistryName()).getPath();
                    if (item instanceof BlockItem)
                        getBuilder(path).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + path)));
                    else if (item == PiscaryItems.FISHING_ROD.get()) {

                    } else {
                        if (path.contains("spawn_egg")) {
                            withExistingParent(path, mcLoc("item/template_spawn_egg"));
                        } else {
                            String subdir =
                                    path.contains("bait") ? Strings.EMPTY :
                                            item.getFoodProperties() != null && item.getFoodProperties().getNutrition() > 3 ? "meal/" :
                                                    item.getRegistryName().getPath().contains("bucket") ? "bucket/" :
                                                            item.getFoodProperties() == null ? "loot/" :
                                                                    "fish/";
                            singleTexture(path, mcLoc("item/generated"), "layer0", modLoc("item/" + subdir + path.replace("_bucket", "")));
                        }
                    }
                });
    }

    @Override
    protected void registerModels() {
        registerModels(PiscaryItems.ITEMS);
        registerModels(PiscaryEntities.ITEMS);
    }
}
