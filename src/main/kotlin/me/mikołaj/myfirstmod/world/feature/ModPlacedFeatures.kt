package me.mikołaj.myfirstmod.world.feature

import me.mikołaj.myfirstmod.MyFirstMod
import net.minecraft.core.Registry
import net.minecraft.world.level.levelgen.VerticalAnchor
import net.minecraft.world.level.levelgen.placement.*
import net.minecraftforge.registries.DeferredRegister


object ModPlacedFeatures {
 val PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, MyFirstMod.MODID)

val TIN_ORE_PLACED = PLACED_FEATURES.register("tin_ore_placed") {
 PlacedFeature(ModConfiguredFeatures.TIN_ORE.holder.get(),
  commonOrePlacement(65, HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.aboveBottom(100))))
}


 fun orePlacement(p_195347_: PlacementModifier, p_195348_: PlacementModifier): List<PlacementModifier>? {
  return listOf(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome())
 }

 fun commonOrePlacement(p_195344_: Int, p_195345_: PlacementModifier): List<PlacementModifier>? {
  return orePlacement(CountPlacement.of(p_195344_), p_195345_)
 }

 fun rareOrePlacement(p_195350_: Int, p_195351_: PlacementModifier): List<PlacementModifier>? {
  return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_)
 }
}