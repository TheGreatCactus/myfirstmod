package me.mikołaj.myfirstmod.networking

import me.mikołaj.myfirstmod.MyFirstMod
import me.mikołaj.myfirstmod.networking.packet.ExampleC2SPacket
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.minecraftforge.network.NetworkDirection
import net.minecraftforge.network.NetworkRegistry
import net.minecraftforge.network.PacketDistributor
import net.minecraftforge.network.simple.SimpleChannel
import kotlin.reflect.full.createInstance

object ModMessages {
   var INSTANCE: SimpleChannel =
      NetworkRegistry.ChannelBuilder.named(ResourceLocation(MyFirstMod.MODID, "messages"))
         .networkProtocolVersion { "1.0" }
         .clientAcceptedVersions { true }
         .serverAcceptedVersions { true }
         .simpleChannel()


   private var packetId = 0
   private fun id(): Int {
      return packetId++
   }

   fun register(){
      INSTANCE.messageBuilder(ExampleC2SPacket::class.java, id(), NetworkDirection.PLAY_TO_SERVER)
         .decoder { ExampleC2SPacket::class.createInstance() }
         .encoder(ExampleC2SPacket::toBytes)
         .consumerMainThread(ExampleC2SPacket::handle)
         .add()
   }

   fun <MSG> sendToServer(message: MSG) {
      INSTANCE.sendToServer(message)
   }

   fun <MSG> sendToPlayer(message: MSG, player: ServerPlayer) {
      INSTANCE.send(PacketDistributor.PLAYER.with { player }, message)
   }
}