package me.mikołaj.myfirstmod.networking.packet

import net.minecraft.network.FriendlyByteBuf
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraftforge.network.NetworkEvent
import java.util.function.BiConsumer
import java.util.function.Supplier

class ExampleC2SPacket {

    constructor()

    constructor(buf: FriendlyByteBuf)

    fun toBytes(buf: FriendlyByteBuf) {

    }

    fun handle(supplier: Supplier<NetworkEvent.Context>): Boolean {
        val context = supplier.get()
        context.enqueueWork{
            val player = context.sender
            val level: ServerLevel = player!!.getLevel()

            EntityType.PHANTOM.spawn(
                level, null, null, player.blockPosition(),
                MobSpawnType.COMMAND, true, false
            )
        }
        return true
    }
}