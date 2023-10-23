package com.sschr15.mods.quasicraft.mixin

import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.BaseEntityBlock
import net.minecraft.world.level.block.CrafterBlock
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Redirect

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "FunctionName")
@Mixin(CrafterBlock::class)
abstract class CrafterBlockMixin : BaseEntityBlock(null) {
    @Redirect(method = ["neighborChanged"], at = At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;hasNeighborSignal(Lnet/minecraft/core/BlockPos;)Z"))
    private fun `quasicraft-add-quasi-connectivity`(getter: Level, pos: BlockPos) =
        getter.hasNeighborSignal(pos) || getter.hasNeighborSignal(pos.above())
}
