package me.mikołaj.myfirstmod.networking.packet

import me.mikołaj.myfirstmod.needToShit.PlayerNeedToShit
import me.mikołaj.myfirstmod.needToShit.PlayerNeedToShitProvider
import me.mikołaj.myfirstmod.networking.ModMessages
import net.minecraft.ChatFormatting
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
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

            player.getCapability(PlayerNeedToShitProvider.PLAYER_NEED_TO_SHIT)
            .ifPresent{ need_to_shit: PlayerNeedToShit ->
                if (need_to_shit.getNeedToShit() > 2) {
                    need_to_shit.resetNeedToShit()
                    player.sendSystemMessage(
                        Component
                        .literal("Your NTS level: " + need_to_shit.getNeedToShit())
                        .withStyle(ChatFormatting.DARK_AQUA))


                    EntityType.PHANTOM.spawn(
                        level, null, null, player.blockPosition(),
                        MobSpawnType.COMMAND, true, false)

                    ModMessages.sendToPlayer(NTSDataSyncS2CPacket(need_to_shit.getNeedToShit()), player)
                }
                else {
                    player.sendSystemMessage(Component
                        .literal("You don't need to shit yet")
                        .withStyle(ChatFormatting.RED))
                    player.sendSystemMessage(Component
                        .literal("Your NTS level: " + need_to_shit.getNeedToShit())
                        .withStyle(ChatFormatting.DARK_AQUA))
                    ModMessages.sendToPlayer(NTSDataSyncS2CPacket(need_to_shit.getNeedToShit()), player)
                }
            }
        }
        return true
    }
}