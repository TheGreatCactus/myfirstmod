package me.mikołaj.myfirstmod.needToShit

import net.minecraft.core.Direction
import net.minecraft.nbt.CompoundTag
import net.minecraftforge.common.capabilities.*
import net.minecraftforge.common.util.INBTSerializable
import net.minecraftforge.common.util.LazyOptional

class PlayerNeedToShitProvider: ICapabilityProvider, INBTSerializable<CompoundTag>{
    private var need_to_shit: PlayerNeedToShit? = null
    private val optional: LazyOptional<PlayerNeedToShit?> = LazyOptional.of{ createPlayerNeedToShit()!! }

    private fun createPlayerNeedToShit(): PlayerNeedToShit? {
        if (need_to_shit == null) {
            need_to_shit = PlayerNeedToShit()
        }

        return need_to_shit
    }

    companion object {
        var PLAYER_NEED_TO_SHIT: Capability<PlayerNeedToShit?> =
            CapabilityManager.get(object : CapabilityToken<PlayerNeedToShit?>() {})
    }


    override fun <T : Any?> getCapability(cap: Capability<T>, side: Direction?): LazyOptional<T> {
        if (cap == PLAYER_NEED_TO_SHIT){
            return optional.cast()
        }

        return LazyOptional.empty()
    }

    override fun serializeNBT(): CompoundTag {
       val nbt = CompoundTag()
        createPlayerNeedToShit()?.saveNBTData(nbt)
        return nbt
    }

    override fun deserializeNBT(nbt: CompoundTag) {
        createPlayerNeedToShit()?.loadNBTData(nbt)
    }

}