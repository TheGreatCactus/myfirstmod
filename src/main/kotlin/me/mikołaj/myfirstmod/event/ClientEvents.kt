package me.mikołaj.myfirstmod.event

import me.mikołaj.myfirstmod.MyFirstMod
import me.mikołaj.myfirstmod.client.NTSHudOverlay
import me.mikołaj.myfirstmod.networking.ModMessages
import me.mikołaj.myfirstmod.networking.packet.ExampleC2SPacket
import me.mikołaj.myfirstmod.util.KeyBinding
import net.minecraft.client.Minecraft
import net.minecraft.network.chat.Component
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.client.event.InputEvent
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent
import net.minecraftforge.client.event.RegisterKeyMappingsEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod

object ClientEvents {
    @Mod.EventBusSubscriber(modid = MyFirstMod.MODID, value = arrayOf(Dist.CLIENT))
    object ClientForgeEvents {
        @SubscribeEvent
        fun onKeyInput(event: InputEvent.Key) {
            if(KeyBinding.POOPING_KEY.consumeClick()) {
                ModMessages.sendToServer(ExampleC2SPacket())
                Minecraft.getInstance().player!!.sendSystemMessage(Component.literal("You' ve just pooped"))
            }
        }
    }
    @Mod.EventBusSubscriber(modid = MyFirstMod.MODID, value = arrayOf(Dist.CLIENT), bus = Mod.EventBusSubscriber.Bus.MOD)
    object ClientModBusEvent {
        @SubscribeEvent
        fun onKeyRegister(event: RegisterKeyMappingsEvent) {
            event.register(KeyBinding.POOPING_KEY)
        }

        @SubscribeEvent
        fun registerGuiOverlays(event: RegisterGuiOverlaysEvent) {
            event.registerAboveAll("nts", NTSHudOverlay().MOD_NTS)
        }
    }
}