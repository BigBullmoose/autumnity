package com.teamabnormals.autumnity.core.other;

import com.teamabnormals.autumnity.core.registry.AutumnityBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.state.BlockState;

public class AutumnityClientCompat {

	public static void registerRenderLayers() {
		ItemBlockRenderTypes.setRenderLayer(AutumnityBlocks.SNAIL_GOO.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(AutumnityBlocks.SNAIL_GOO_BLOCK.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(AutumnityBlocks.FOUL_BERRY_BUSH.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AutumnityBlocks.TALL_FOUL_BERRY_BUSH.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AutumnityBlocks.AUTUMN_CROCUS.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AutumnityBlocks.POTTED_AUTUMN_CROCUS.get(), RenderType.cutout());

		ItemBlockRenderTypes.setRenderLayer(AutumnityBlocks.MAPLE_DOOR.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AutumnityBlocks.MAPLE_TRAPDOOR.get(), RenderType.cutout());

		ItemBlockRenderTypes.setRenderLayer(AutumnityBlocks.MAPLE_SAPLING.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AutumnityBlocks.YELLOW_MAPLE_SAPLING.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AutumnityBlocks.ORANGE_MAPLE_SAPLING.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AutumnityBlocks.RED_MAPLE_SAPLING.get(), RenderType.cutout());

		ItemBlockRenderTypes.setRenderLayer(AutumnityBlocks.POTTED_MAPLE_SAPLING.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AutumnityBlocks.POTTED_YELLOW_MAPLE_SAPLING.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AutumnityBlocks.POTTED_ORANGE_MAPLE_SAPLING.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AutumnityBlocks.POTTED_RED_MAPLE_SAPLING.get(), RenderType.cutout());

		ItemBlockRenderTypes.setRenderLayer(AutumnityBlocks.POTTED_FOUL_BERRIES.get(), RenderType.cutout());
	}

	public static void registerBlockColors() {
		BlockColors blockcolors = Minecraft.getInstance().getBlockColors();
		ItemColors itemcolors = Minecraft.getInstance().getItemColors();

		blockcolors.register((state, world, pos, tintIndex) -> 9352231, AutumnityBlocks.RED_MAPLE_LEAVES.get(), AutumnityBlocks.MAPLE_LEAF_PILE.get());
		blockcolors.register((state, world, pos, tintIndex) -> 10427697, AutumnityBlocks.RED_MAPLE_LEAVES.get(), AutumnityBlocks.RED_MAPLE_LEAF_PILE.get());
		blockcolors.register((state, world, pos, tintIndex) -> 16743194, AutumnityBlocks.ORANGE_MAPLE_LEAVES.get(), AutumnityBlocks.ORANGE_MAPLE_LEAF_PILE.get());
		blockcolors.register((state, world, pos, tintIndex) -> 16767334, AutumnityBlocks.YELLOW_MAPLE_LEAVES.get(), AutumnityBlocks.YELLOW_MAPLE_LEAF_PILE.get());

		itemcolors.register((stack, tintIndex) -> {
					BlockState blockstate = ((BlockItem) stack.getItem()).getBlock().defaultBlockState();
					return blockcolors.getColor(blockstate, null, null, tintIndex);
				},
				AutumnityBlocks.MAPLE_LEAVES.get(), AutumnityBlocks.RED_MAPLE_LEAVES.get(), AutumnityBlocks.ORANGE_MAPLE_LEAVES.get(), AutumnityBlocks.YELLOW_MAPLE_LEAVES.get(),
				AutumnityBlocks.MAPLE_LEAF_PILE.get(), AutumnityBlocks.RED_MAPLE_LEAF_PILE.get(), AutumnityBlocks.ORANGE_MAPLE_LEAF_PILE.get(), AutumnityBlocks.YELLOW_MAPLE_LEAF_PILE.get());
	}
}
