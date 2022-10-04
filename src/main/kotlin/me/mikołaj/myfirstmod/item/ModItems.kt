package me.mikołaj.myfirstmod.item

import me.mikołaj.myfirstmod.MyFirstMod
import me.mikołaj.myfirstmod.block.ModBlocks
import me.mikołaj.myfirstmod.item.custom.DiceItem
import net.minecraft.world.food.FoodProperties
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemNameBlockItem
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject

object ModItems {
    val ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MyFirstMod.MODID)

    val BRONZE_INGOT by ITEMS.registerObject("bronze_ingot") {
        Item(Item.Properties().tab(ModCreativeModeTab.MOD_TAB))
    }
    val TIN_INGOT by ITEMS.registerObject("tin_ingot") {
        Item(Item.Properties().tab(ModCreativeModeTab.MOD_TAB))
    }

    val RAW_TIN by ITEMS.registerObject("raw_tin") {
        Item(Item.Properties().tab(ModCreativeModeTab.MOD_TAB))
    }

    val TIN_NUGGET by ITEMS.registerObject("tin_nugget") {
        Item(Item.Properties().tab(ModCreativeModeTab.MOD_TAB))
    }
    val BRONZE_NUGGET by ITEMS.registerObject("bronze_nugget") {
        Item(Item.Properties().tab(ModCreativeModeTab.MOD_TAB))
    }

    val DICE by ITEMS.registerObject("dice") {
        DiceItem(Item.Properties().tab(ModCreativeModeTab.MOD_TAB).stacksTo(1))
    }

    val BLUEBERRY_SEEDS by ITEMS.registerObject("blueberry_seeds") {
        ItemNameBlockItem(ModBlocks.BLUEBERRY_CROP.get(),
        Item.Properties().tab(ModCreativeModeTab.MOD_TAB))
    }

    val BLUEBERRY by ITEMS.registerObject("blueberry") {
        Item(Item.Properties().tab(ModCreativeModeTab.MOD_TAB)
            .food(FoodProperties.Builder().nutrition(2).saturationMod(2f).build()))
    }

}