package me.mikołaj.myfirstmod.item.custom

import net.minecraft.ChatFormatting
import net.minecraft.client.gui.screens.Screen
import net.minecraft.network.chat.Component
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level

class DiceItem: Item {
    constructor(properties: Properties) : super(properties)

    override fun use(level: Level, player: Player, hand: InteractionHand): InteractionResultHolder<ItemStack> {
        if (!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {
            player.sendSystemMessage(Component.literal("You've thrown out " + (1..6).random()))
            player.cooldowns.addCooldown(this,100)
        }

        return super.use(level, player, hand)
    }

    override fun appendHoverText(stack: ItemStack, level: Level?, components: MutableList<Component>, flag: TooltipFlag) {

        if (Screen.hasShiftDown()) {
            components.add(Component.literal("Right click to throw a hexahedral dice!").withStyle(ChatFormatting.DARK_AQUA))
        } else {
            components.add(Component.literal("Press SHIFT for more info").withStyle(ChatFormatting.YELLOW))
        }

        super.appendHoverText(stack, level, components, flag)
    }
}