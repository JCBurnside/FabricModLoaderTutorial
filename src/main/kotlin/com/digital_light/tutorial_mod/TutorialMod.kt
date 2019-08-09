package com.digital_light.tutorial_mod

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.block.FabricBlockSettings
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.minecraft.block.Block
import net.minecraft.state.property.BooleanProperty
import net.minecraft.block.BlockState
import net.minecraft.block.Material
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemConvertible
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.sound.SoundEvents
import net.minecraft.state.StateFactory
import net.minecraft.text.Text
import net.minecraft.text.TranslatableText
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.Identifier
import net.minecraft.util.TypedActionResult
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.registry.Registry
import net.minecraft.world.BlockView
import net.minecraft.world.World
import org.lwjgl.opengl.EXT422Pixels
import kotlin.math.E

fun ItemConvertible.makeStack(count: Int = 1) = ItemStack(this, count)

object ExItem:Item(Item.Settings()){
    init{
    }

    override fun appendTooltip(
        itemStack: ItemStack,
        world: World?,
        list: MutableList<Text>,
        tooltipContext: TooltipContext?
    ) {
        list.add(TranslatableText("item.tutorial_mod.ex_item.tooltip"))
    }

    override fun use(world: World, pe: PlayerEntity, hand: Hand?): TypedActionResult<ItemStack> {
        pe.playSound(SoundEvents.BLOCK_ANVIL_LAND, 1F,1F)

        return TypedActionResult(ActionResult.SUCCESS,pe.getStackInHand(hand))
    }
}

class ExBlock : Block
{
    companion object {
        val INSTANCE = ExBlock()
        @JvmStatic var ExBlockIsHard:IsHardProperty = IsHardProperty()
    }


    private constructor()
            : super(FabricBlockSettings.of(Material.METAL).breakByHand(true).build())
    {
        setDefaultState(
            getStateFactory()
                .getDefaultState()
                .with(
                    ExBlockIsHard,true
                )
        )
    }

    protected override fun appendProperties(sf: StateFactory.Builder<Block, BlockState>) {
        super.appendProperties(sf)
        sf?.add(ExBlockIsHard)
    }

}

val ExItemBlock = BlockItem(ExBlock.INSTANCE,Item.Settings())

@SuppressWarnings("unused")
fun init() {
    FabricItemGroupBuilder.create(Identifier("tutorial_mod"))
        .icon { ExItemBlock.makeStack() }
        .appendItems {
            it += ExItemBlock.makeStack()
            it += ExItem.makeStack()
        }.build()
    Registry.register(Registry.ITEM, Identifier("tutorial_mod","ex_item"), ExItem)
    Registry.register(Registry.ITEM, Identifier("tutorial_mod","ex_block"), ExItemBlock)
    Registry.register(Registry.BLOCK, Identifier("tutorial_mod","ex_block"), ExBlock.INSTANCE)
}



