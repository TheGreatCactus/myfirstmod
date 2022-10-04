package me.mikołaj.myfirstmod.event

import me.mikołaj.myfirstmod.MyFirstMod
import me.mikołaj.myfirstmod.item.ModItems
import me.mikołaj.myfirstmod.villager.ModVillagers
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.trading.MerchantOffer
import net.minecraftforge.event.village.VillagerTradesEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
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
}