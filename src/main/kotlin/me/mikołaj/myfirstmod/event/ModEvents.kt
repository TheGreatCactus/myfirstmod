package me.mikołaj.myfirstmod.event

import me.mikołaj.myfirstmod.MyFirstMod
import me.mikołaj.myfirstmod.item.ModItems
import me.mikołaj.myfirstmod.needToShit.PlayerNeedToShit
import me.mikołaj.myfirstmod.needToShit.PlayerNeedToShitProvider
import me.mikołaj.myfirstmod.villager.ModVillagers
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.trading.MerchantOffer
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent
import net.minecraftforge.event.AttachCapabilitiesEvent
import net.minecraftforge.event.TickEvent.PlayerTickEvent
import net.minecraftforge.event.entity.player.PlayerEvent
import net.minecraftforge.event.village.VillagerTradesEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.LogicalSide
import net.minecraftforge.fml.common.Mod


@Mod.EventBusSubscriber(modid = MyFirstMod.MODID)
object ModEvents {
   @SubscribeEvent
   fun addCustomTrades(event: VillagerTradesEvent){
      if (event.type == ModVillagers.DEALER) {
        val trades = event.trades
      val stack =  ItemStack(ModItems.DICE, 1)
         val villagerLevel = 1

         trades.get(villagerLevel).add{trader, rand -> MerchantOffer(
            ItemStack(Items.EMERALD, 4),
         stack, 5, 8, 0.02F)}
      }
   }

   @SubscribeEvent
   fun onAttachCapabilitiesPlayer(event: AttachCapabilitiesEvent<Entity>) {
      if (event.getObject() is Player) {
         if (!event.getObject().getCapability(PlayerNeedToShitProvider.PLAYER_NEED_TO_SHIT).isPresent()) {
            event.addCapability(ResourceLocation(MyFirstMod.MODID, "properties"), PlayerNeedToShitProvider())
         }
      }
   }

   @SubscribeEvent
   fun onPlayerCloned(event: PlayerEvent.Clone) {
      if (event.isWasDeath) {
         event.original.getCapability(PlayerNeedToShitProvider.PLAYER_NEED_TO_SHIT).ifPresent { oldStore: PlayerNeedToShit ->
            event.original.getCapability(PlayerNeedToShitProvider.PLAYER_NEED_TO_SHIT).ifPresent { newStore: PlayerNeedToShit ->
               newStore.copyFrom(oldStore)
            }
         }
      }
   }

   @SubscribeEvent
   fun onRegisterCapabilities(event: RegisterCapabilitiesEvent) {
      event.register(PlayerNeedToShit::class.java)
   }

   @SubscribeEvent
   fun onPlayerTick(event: PlayerTickEvent) {
      if (event.side == LogicalSide.SERVER) {
         event.player.getCapability(PlayerNeedToShitProvider.PLAYER_NEED_TO_SHIT).ifPresent { need_to_shit: PlayerNeedToShit ->
            if (need_to_shit.getNeedToShit()!! < 10 && event.player.random
                  .nextFloat() < 0.005f
            ) { // Once Every 10 Seconds on Avg
               need_to_shit.addNeedToShit(1)
               event.player.sendSystemMessage(Component.literal("Added Thirst" + need_to_shit.getNeedToShit()))
            }
         }
      }
   }
}
