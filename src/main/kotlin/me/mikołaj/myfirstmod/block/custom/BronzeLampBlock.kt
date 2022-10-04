package me.mikołaj.myfirstmod.block.custom

import net.minecraft.client.gui.screens.Screen
import net.minecraft.core.BlockPos
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.phys.BlockHitResult

class BronzeLampBlock: Block {
    object LIT: BooleanProperty("lit")

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(LIT)
    }

    override fun use(
        state: BlockState, level: Level, blockPos: BlockPos, player: Player, hand: InteractionHand,
        result: BlockHitResult): InteractionResult {

        if(!level.isClientSide() && hand == InteractionHand.MAIN_HAND && !Screen.hasShiftDown()) {
            level.setBlock(blockPos, state.cycle(LIT), 3)
        }

        return super.use(state, level, blockPos, player, hand, result)
    }

        constructor(properties: Properties) : super(properties)
}