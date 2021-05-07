package uk.joshiejack.piscary.network;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.registries.ForgeRegistries;
import uk.joshiejack.penguinlib.network.packet.BlockRenderUpdatePacket;
import uk.joshiejack.penguinlib.util.PenguinLoader;
import uk.joshiejack.piscary.tile.HatcheryTileEntity;

@PenguinLoader.Packet(NetworkDirection.PLAY_TO_CLIENT)
public class SyncHatcheryPacket extends BlockRenderUpdatePacket {
    private EntityType<?> type;
    private int count;

    public SyncHatcheryPacket(){}
    public SyncHatcheryPacket(BlockPos pos, EntityType<?> type, int count) {
        super(pos);
        this.type = type;
        this.count = count;
    }

    @Override
    public void encode(PacketBuffer pb) {
        super.encode(pb);
        pb.writeBoolean(type != null);
        if (type != null)
            pb.writeRegistryId(type);
        pb.writeByte(count);
    }

    @Override
    public void decode(PacketBuffer pb) {
        super.decode(pb);
        if (pb.readBoolean())
            type = pb.readRegistryIdSafe(ForgeRegistries.ENTITIES.getRegistrySuperType());
        count = pb.readByte();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void handleClientPacket() {
        assert Minecraft.getInstance().level != null;
        TileEntity tile = Minecraft.getInstance().level.getBlockEntity(pos);
        if (tile instanceof HatcheryTileEntity) {
            ((HatcheryTileEntity)tile).setEntityTypeAndCount(type, count);
        }
    }
}
