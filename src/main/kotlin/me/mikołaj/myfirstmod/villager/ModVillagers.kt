package me.mikołaj.myfirstmod.villager

import com.google.common.collect.ImmutableSet
import me.mikołaj.myfirstmod.MyFirstMod
import me.mikołaj.myfirstmod.block.ModBlocks
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.entity.ai.village.poi.PoiType
import net.minecraft.world.entity.npc.VillagerProfession
import net.minecraftforge.fml.util.ObfuscationReflectionHelper
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject
import java.lang.reflect.InvocationTargetException

object ModVillagers {
    val POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, MyFirstMod.MODID)
    val VILLAGER_PROFESSIONS = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, MyFirstMod.MODID)

    val DEATH_BLOCK_POI by POI_TYPES.registerObject("death_block_poi") {
        PoiType(ImmutableSet.copyOf(ModBlocks.DEATH_BLOCK.get().stateDefinition.possibleStates), 1, 1)
    }

    val DEALER  by VILLAGER_PROFESSIONS.registerObject("dealer") {
        VillagerProfession("dealer", {x -> x.get() == DEATH_BLOCK_POI}, {x -> x.get() == DEATH_BLOCK_POI}, ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_CLERIC)
    }

    fun registerPOIs(){
        try {
            ObfuscationReflectionHelper.findMethod(PoiType::class.java,
                "registerBlockStates", PoiType::class.java).invoke(null, DEATH_BLOCK_POI)
        } catch (exception: Exception) {
            if(exception is IllegalAccessException || exception is InvocationTargetException) {
                exception.printStackTrace()
            }
        }
    }
}