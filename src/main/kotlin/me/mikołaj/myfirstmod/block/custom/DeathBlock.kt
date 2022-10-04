package me.mikołaj.myfirstmod.block.custom

import net.minecraft.core.BlockPos
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState

class DeathBlock: Block {
constructor(properties: Properties): super(properties)

    override fun stepOn(level: Level, pos: BlockPos, state: BlockState, entity: Entity) {
        if (entity is LivingEntity) {
            entity.hurt(DamageSource.OUT_OF_WORLD, 1000000F)
        }


        super.stepOn(level, pos, state, entity)
    }
}