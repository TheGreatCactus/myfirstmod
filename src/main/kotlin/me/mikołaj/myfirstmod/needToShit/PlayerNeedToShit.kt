package me.mikołaj.myfirstmod.needToShit

import net.minecraft.nbt.CompoundTag

class PlayerNeedToShit {
    private var need_to_shit: Int = 0
    private val MIN_NEED_TO_SHIT = 0
    private val MAX_NEED_TO_SHIT = 10

    fun addNeedToShit(add: Int) {
        need_to_shit = Math.min(need_to_shit + add, MAX_NEED_TO_SHIT)
    }

    fun subNeedToShit(sub: Int) {
        need_to_shit = Math.max(need_to_shit - sub, MIN_NEED_TO_SHIT)
    }

    fun resetNeedToShit() {
        need_to_shit = MIN_NEED_TO_SHIT
    }

    fun getNeedToShit(): Int {
        return need_to_shit
    }

    fun copyFrom(source: PlayerNeedToShit) {
        need_to_shit = source.need_to_shit
    }

    fun saveNBTData(nbt: CompoundTag) {
        need_to_shit.let { nbt.putInt("need_to_shit", it) }
    }

    fun loadNBTData(nbt: CompoundTag) {
        need_to_shit = nbt.getInt("need_to_shit")
    }
}