package me.mikołaj.myfirstmod.block

import me.mikołaj.myfirstmod.MyFirstMod
import me.mikołaj.myfirstmod.block.custom.BronzeLampBlock
import me.mikołaj.myfirstmod.block.custom.DeathBlock
import me.mikołaj.myfirstmod.block.custom.BlueberryCropBlock
import me.mikołaj.myfirstmod.item.ModCreativeModeTab
import me.mikołaj.myfirstmod.item.ModItems
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.material.Material
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject
import java.util.function.Supplier

object ModBlocks {
    val BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MyFirstMod.MODID)

    val BRONZE_BLOCK = registerBlock("bronze_block",
        {
            Block(
                BlockBehaviour.Properties.of(Material.METAL).strength(5.0f)
                    .requiresCorrectToolForDrops().sound(SoundType.COPPER)
            )
        }, ModCreativeModeTab.MOD_TAB
    )
    val TIN_BLOCK = registerBlock("tin_block",
        {
            Block(
                BlockBehaviour.Properties.of(Material.METAL).strength(5.0f)
                    .requiresCorrectToolForDrops().sound(SoundType.COPPER)
            )
        }, ModCreativeModeTab.MOD_TAB
    )
    val TIN_ORE = registerBlock("tin_ore",
        {
            Block(
                BlockBehaviour.Properties.of(Material.STONE).strength(3.5f)
                    .requiresCorrectToolForDrops()
            )
        }, ModCreativeModeTab.MOD_TAB
    )
    val DEATH_BLOCK = registerBlock("death_block", {DeathBlock(BlockBehaviour.Properties.of(Material.STONE).strength(80.0f))}, ModCreativeModeTab.MOD_TAB)
    val BRONZE_LAMP = registerBlock("bronze_lamp",
        {
            BronzeLampBlock(
                BlockBehaviour.Properties.of(Material.STONE).strength(2.5f)
                    .requiresCorrectToolForDrops().lightLevel {state -> if(state.getValue(BronzeLampBlock.LIT)){15} else {0} })
        }, ModCreativeModeTab.MOD_TAB)

    val BLUEBERRY_CROP: RegistryObject<Block> = BLOCKS.register("blueberry_crop") {
        BlueberryCropBlock(
            BlockBehaviour.Properties.copy(Blocks.WHEAT))
    }


    private fun <T : Block> registerBlock(name: String, block: Supplier<T>, tab: CreativeModeTab): RegistryObject<T> {
        val toReturn: RegistryObject<T> = BLOCKS.register(name, block)
        registerBlockItem(name, toReturn, tab)
        return toReturn
    }

    private fun <T : Block> registerBlockItem(
        name: String,
        block: RegistryObject<T>,
        tab: CreativeModeTab
    ): RegistryObject<Item> {
        return ModItems.ITEMS.register(name) { BlockItem(block.get(), Item.Properties().tab(tab)) }
    }
}