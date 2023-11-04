package me.mikołaj.myfirstmod.client

import com.mojang.blaze3d.systems.RenderSystem
import me.mikołaj.myfirstmod.MyFirstMod
import net.minecraft.client.gui.GuiComponent
import net.minecraft.client.renderer.GameRenderer
import net.minecraft.resources.ResourceLocation
import net.minecraftforge.client.gui.overlay.IGuiOverlay

class NTSHudOverlay {
    private val FILLED_NTS = ResourceLocation(MyFirstMod.MODID, "textures/needtoshit/filled_nts.png")
    private val EMPTY_NTS = ResourceLocation(MyFirstMod.MODID, "textures/needtoshit/empty_nts.png")

    companion object {
        var PlayerNTS = 5
    }

    val MOD_NTS = IGuiOverlay { gui, poseStack, partialTick, width, height ->
        var x = width / 2
        var y = height

        RenderSystem.setShader(GameRenderer::getPositionShader)
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F)
        RenderSystem.setShaderTexture(0, EMPTY_NTS)
        for(i in 1..10) {
            GuiComponent.blit(poseStack, x - 94 + (i * 9), y - 54, 0f, 0f, 12, 12, 12, 12, )
        }

        RenderSystem.setShaderTexture(0, FILLED_NTS)
        for (i in 1..10) {
           if (PlayerNTS >= i) {
               GuiComponent.blit(poseStack, x - 94 + (i * 9), y - 54, 0f, 0f, 12, 12, 12, 12)
           } else {
               break
           }
        }
    }
}