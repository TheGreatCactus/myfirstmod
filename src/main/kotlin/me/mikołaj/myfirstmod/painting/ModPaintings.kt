package me.mikołaj.myfirstmod.painting

import me.mikołaj.myfirstmod.MyFirstMod
import net.minecraft.world.entity.decoration.PaintingVariant
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject

object ModPaintings {
    val PAINTING_VARIANTS = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, MyFirstMod.MODID)

    val WANDERER by PAINTING_VARIANTS.registerObject("wanderer") {
        PaintingVariant(16, 32)
    }
}