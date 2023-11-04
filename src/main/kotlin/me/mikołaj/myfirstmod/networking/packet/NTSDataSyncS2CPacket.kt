package me.mikołaj.myfirstmod.networking.packet

import me.mikołaj.myfirstmod.client.NTSHudOverlay
import net.minecraft.network.FriendlyByteBuf
import net.minecraftforge.network.NetworkEvent
import java.util.function.Supplier

class NTSDataSyncS2CPacket {
    private var NTS = 0

    constructor(NTS : Int) {
        this.NTS = NTS
    }

    constructor(buf: FriendlyByteBuf) {
        this.NTS = buf.readInt()
    }

    constructor() {

    }

    fun toBytes(buf: FriendlyByteBuf) {
        buf.writeInt(NTS)
    }

    fun handle(supplier: Supplier<NetworkEvent.Context>): Boolean {
        val context = supplier.get()
        context.enqueueWork{
            NTSHudOverlay.PlayerNTS = NTS
        }
        return true
    }
}