package me.mikołaj.myfirstmod.item

import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.ItemLike

class ModCreativeModeTab: ItemLike {
    object MOD_TAB: CreativeModeTab( "modtab") {
        override
        fun makeIcon():ItemStack {
                return ItemStack(ModItems.BRONZE_INGOT)
        }
    }

    override fun asItem(): Item {
        TODO("Not yet implemented")
    }
}

