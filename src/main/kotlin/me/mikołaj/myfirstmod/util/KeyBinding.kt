package me.mikołaj.myfirstmod.util

import com.mojang.blaze3d.platform.InputConstants
import net.minecraft.client.KeyMapping
import net.minecraftforge.client.settings.KeyConflictContext
import org.lwjgl.glfw.GLFW

object KeyBinding {
    val KEY_CATEGORY_MYMOD = "key.category.myfirstmod.mymod"
    val KEY_POOP = "key.myfirstmod.poop"
    val POOPING_KEY = KeyMapping(KEY_POOP, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_O, KEY_CATEGORY_MYMOD)
}