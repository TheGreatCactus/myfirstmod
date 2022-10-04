package me.mikołaj.myfirstmod.world.feature

import com.google.common.base.Suppliers
import me.mikołaj.myfirstmod.MyFirstMod
import me.mikołaj.myfirstmod.block.ModBlocks
import net.minecraft.core.Registry
import net.minecraft.data.worldgen.features.OreFeatures
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.RegistryObject

object ModConfiguredFeatures {
    val CONFIGURED_FEATURES: DeferredRegister<ConfiguredFeature<*, *>> = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, MyFirstMod.MODID)

    val OVERWORLD_TIN_ORES = Suppliers.memoize{
        listOf(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.TIN_ORE.get().defaultBlockState())
        )
    }

    val TIN_ORE: RegistryObject<ConfiguredFeature<*, *>> = CONFIGURED_FEATURES.register("tin_ore") {
        ConfiguredFeature(Feature.ORE, OreConfiguration(OVERWORLD_TIN_ORES.get(), 10))
    }
    fun register(eventBus: IEventBus) {
        CONFIGURED_FEATURES.register(eventBus)
    }
}