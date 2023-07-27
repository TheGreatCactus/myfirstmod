package me.mikołaj.myfirstmod.networking.packet

import me.mikołaj.myfirstmod.client.NTSHudOverlay
import me.mikołaj.myfirstmod.needToShit.PlayerNeedToShit
import me.mikołaj.myfirstmod.needToShit.PlayerNeedToShitProvider
import net.minecraft.ChatFormatting
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraftforge.network.NetworkEvent
import java.util.function.BiConsumer
import java.util.function.Supplier

class NTSDataSyncS2CPacket {
    private var NTS = 0

    constructor(NTS : Int) {
        this.NTS  = NTS
    }

    constructor(buf: FriendlyByteBuf) {
        this.NTS = buf.readInt()
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