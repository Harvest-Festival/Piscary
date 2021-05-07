package uk.joshiejack.piscary.loot;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.BiomeDictionary;

import javax.annotation.Nullable;
import java.util.Optional;

public class BiomeTypePredicate {
    public static final BiomeTypePredicate ANY = new BiomeTypePredicate(null);
    private final BiomeDictionary.Type type;

    public BiomeTypePredicate(@Nullable BiomeDictionary.Type type) {
        this.type = type;
    }

    public boolean matches(ServerWorld world, double x, double y, double z) {
        if (type == null) return true;
        BlockPos blockpos = new BlockPos(x, y, z);
        Optional<RegistryKey<Biome>> optional = world.registryAccess()
                .registryOrThrow(Registry.BIOME_REGISTRY).getResourceKey(world.getBiome(blockpos));
        return optional.filter(biome -> BiomeDictionary.hasType(biome, type)).isPresent();
    }

    public JsonElement serializeToJson() {
        if (this == ANY)
            return JsonNull.INSTANCE;
        else {
            JsonObject jsonobject = new JsonObject();
            if (this.type != null) {
                jsonobject.addProperty("type", type.getName());
            }

            return jsonobject;
        }
    }

    public static BiomeTypePredicate fromJson(@Nullable JsonElement json) {
        if (json != null && !json.isJsonNull()) {
            if (json.getAsJsonObject().has("type"))
                return new BiomeTypePredicate(BiomeDictionary.Type.getType(json.getAsJsonObject().get("type").getAsString()));
        }

        return ANY;
    }

    public static class Builder {
        private BiomeDictionary.Type type;

        public static BiomeTypePredicate.Builder type() {
            return new BiomeTypePredicate.Builder();
        }

        public BiomeTypePredicate.Builder setBiomeType(@Nullable BiomeDictionary.Type type) {
            this.type = type;
            return this;
        }

        public BiomeTypePredicate build() {
            return new BiomeTypePredicate(this.type);
        }
    }
}
