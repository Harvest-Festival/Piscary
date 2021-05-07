package uk.joshiejack.piscary.loot;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.loot.ILootSerializer;
import net.minecraft.loot.LootConditionType;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.math.vector.Vector3d;

import javax.annotation.Nonnull;

public class BiomeTypeLocationCheck implements ILootCondition {
    public static final LootConditionType BIOME_TYPE = new LootConditionType(new Serializer());
    private final BiomeTypePredicate predicate;

    private BiomeTypeLocationCheck(BiomeTypePredicate predicate) {
        this.predicate = predicate;
    }

    @Nonnull
    @Override
    public LootConditionType getType() {
        return BIOME_TYPE;
    }

    @Override
    public boolean test(LootContext ctx) {
        Vector3d v3d = ctx.getParamOrNull(LootParameters.ORIGIN);
        return v3d != null && this.predicate.matches(ctx.getLevel(), v3d.x, v3d.y, v3d.z);
    }

    public static ILootCondition.IBuilder checkBiomeType(BiomeTypePredicate.Builder builder) {
        return () -> new BiomeTypeLocationCheck(builder.build());
    }

    public static class Serializer implements ILootSerializer<BiomeTypeLocationCheck> {
        @Override
        public void serialize(JsonObject jsonObject, BiomeTypeLocationCheck check, @Nonnull JsonSerializationContext ctx) {
            jsonObject.add("predicate", check.predicate.serializeToJson());
        }

        @Nonnull
        public BiomeTypeLocationCheck deserialize(JsonObject jsonObject, @Nonnull JsonDeserializationContext ctx) {
            BiomeTypePredicate check = BiomeTypePredicate.fromJson(jsonObject.get("predicate"));
            return new BiomeTypeLocationCheck(check);
        }
    }
}
