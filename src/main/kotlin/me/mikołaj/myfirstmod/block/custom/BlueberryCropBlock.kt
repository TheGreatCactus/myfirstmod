package me.mikołaj.myfirstmod.block.custom

import me.mikołaj.myfirstmod.item.ModItems
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.CropBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.IntegerProperty

class BlueberryCropBlock: CropBlock {
    object AGE: IntegerProperty("age", 0, 6)

    override fun getBaseSeedId(): ItemLike {
        return ModItems.BLUEBERRY_SEEDS
    }

    override fun getAgeProperty(): IntegerProperty {
        return AGE
    }

    override fun getMaxAge(): Int {
        return 6
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(AGE)
    }

    constructor(properties: Properties): super(properties)
}
