package me.mikołaj.myfirstmod

import me.mikołaj.myfirstmod.block.ModBlocks
import me.mikołaj.myfirstmod.item.ModItems
import me.mikołaj.myfirstmod.networking.ModMessages
import me.mikołaj.myfirstmod.painting.ModPaintings
import me.mikołaj.myfirstmod.villager.ModVillagers
import me.mikołaj.myfirstmod.world.feature.ModConfiguredFeatures
import me.mikołaj.myfirstmod.world.feature.ModPlacedFeatures
import net.minecraft.client.Minecraft
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.forge.runForDist


// The value here should match an entry in the META-INF/mods.toml file
@Mod(MyFirstMod.MODID)
object MyFirstMod {

    // Define mod id in a common place for everything to reference
    const val MODID = "myfirstmod"


    // the logger for our mod
    val LOGGER: Logger = LogManager.getLogger(MODID)

    init {
        LOGGER.log(Level.INFO, "Hello world!")

        // Register the KDeferredRegister to the mod-specific event bus
        ModItems.ITEMS.register(MOD_BUS)
        ModBlocks.BLOCKS.register(MOD_BUS)
        ModPaintings.PAINTING_VARIANTS.register(MOD_BUS)
        ModVillagers.VILLAGER_PROFESSIONS.register(MOD_BUS)
        ModVillagers.POI_TYPES.register(MOD_BUS)
        ModConfiguredFeatures.CONFIGURED_FEATURES.register(MOD_BUS)
        ModPlacedFeatures.PLACED_FEATURES.register(MOD_BUS)

        val obj = runForDist(
            clientTarget = {
                MOD_BUS.addListener(::onClientSetup)
                Minecraft.getInstance()
            },
            serverTarget = {
                MOD_BUS.addListener(::onServerSetup)
                "test"
            })
        println(obj)
    }

   private fun commonSetup(event: FMLCommonSetupEvent) {
        event.enqueueWork {
                ModVillagers.registerPOIs()

            ModMessages.register()
        }
    }

    /**
     * This is used for initializing client specific
     * things such as renderers and keymaps
     * Fired on the mod specific event bus.
     */
    private fun onClientSetup(event: FMLClientSetupEvent) {
        LOGGER.log(Level.INFO, "Initializing client...")

    }

    /**
     * Fired on the global Forge bus.
     */
    private fun onServerSetup(event: FMLDedicatedServerSetupEvent) {
        LOGGER.log(Level.INFO, "Server starting...")
    }
}