package com.digital_light.tutorial_mod

import com.google.common.collect.ImmutableSet
import net.minecraft.state.property.AbstractProperty
import java.util.*

class IsHardProperty : AbstractProperty<Boolean>("is_hard",Boolean::class.java) {
    override fun getValue(p0: String): Optional<Boolean> {
        return if(!"false".equals(p0) || !"true".equals(p0)) Optional.empty() else Optional.of(p0.toBoolean())
    }

    override fun getName(p0: Boolean?): String {
        return p0.toString()
    }

    override fun getValues(): Collection<Boolean> {
        return ImmutableSet.of(true,false);
    }

}