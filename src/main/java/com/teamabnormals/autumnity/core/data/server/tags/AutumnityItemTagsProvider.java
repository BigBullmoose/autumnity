package com.teamabnormals.autumnity.core.data.server.tags;

import com.teamabnormals.autumnity.core.Autumnity;
import com.teamabnormals.autumnity.core.other.AutumnityConstants;
import com.teamabnormals.autumnity.core.other.tags.AutumnityBlockTags;
import com.teamabnormals.autumnity.core.other.tags.AutumnityItemTags;
import com.teamabnormals.autumnity.core.registry.AutumnityBlocks;
import com.teamabnormals.autumnity.core.registry.AutumnityItems;
import com.teamabnormals.blueprint.core.other.tags.BlueprintItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

import static com.teamabnormals.autumnity.core.registry.AutumnityBlocks.*;

public class AutumnityItemTagsProvider extends ItemTagsProvider {

	public AutumnityItemTagsProvider(PackOutput output, CompletableFuture<Provider> provider, CompletableFuture<TagsProvider.TagLookup<Block>> lookup, ExistingFileHelper helper) {
		super(output, provider, lookup, Autumnity.MOD_ID, helper);
	}

	@Override
	public void addTags(HolderLookup.Provider provider) {
		this.copy(BlockTags.SLABS, ItemTags.SLABS);
		this.copy(BlockTags.STAIRS, ItemTags.STAIRS);
		this.copy(BlockTags.WALLS, ItemTags.WALLS);
		this.copy(BlockTags.PLANKS, ItemTags.PLANKS);
		this.copy(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);
		this.copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
		this.copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);
		this.copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
		this.copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
		this.copy(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS);
		this.copy(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);
		this.copy(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);
		this.copy(BlockTags.STANDING_SIGNS, ItemTags.SIGNS);
		this.copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS);
		this.copy(BlockTags.SMALL_FLOWERS, ItemTags.SMALL_FLOWERS);
		this.copy(BlockTags.LEAVES, ItemTags.LEAVES);
		this.tag(ItemTags.PIGLIN_REPELLENTS).add(SOUL_JACK_O_LANTERN.get().asItem(), LARGE_SOUL_JACK_O_LANTERN_SLICE.get().asItem());
		this.tag(ItemTags.BOATS).add(AutumnityItems.MAPLE_BOAT.getFirst().get());
		this.tag(ItemTags.CHEST_BOATS).add(AutumnityItems.MAPLE_BOAT.getSecond().get());
		this.tag(BlueprintItemTags.FURNACE_BOATS).add(AutumnityItems.MAPLE_FURNACE_BOAT.get());
		this.tag(BlueprintItemTags.LARGE_BOATS).add(AutumnityItems.LARGE_MAPLE_BOAT.get());
		this.tag(ItemTags.FOX_FOOD).add(AutumnityItems.FOUL_BERRIES.get());
		this.tag(BlueprintItemTags.CHICKEN_FOOD).add(AutumnityItems.FOUL_BERRY_PIPS.get());

		this.copy(AutumnityBlockTags.MAPLE_LOGS, AutumnityItemTags.MAPLE_LOGS);
		this.tag(AutumnityItemTags.SNAIL_FOOD).add(Items.MUSHROOM_STEW, Items.SUSPICIOUS_STEW);
		this.tag(AutumnityItemTags.SNAIL_SNACKS).add(Items.RED_MUSHROOM, Items.BROWN_MUSHROOM).addTag(AutumnityItemTags.SNAIL_SPEED_SNACKS).addTag(AutumnityItemTags.SNAIL_GLOW_SNACKS);
		this.tag(AutumnityItemTags.SNAIL_SPEED_SNACKS).add(Items.CRIMSON_FUNGUS, Items.WARPED_FUNGUS);
		this.tag(AutumnityItemTags.SNAIL_GLOW_SNACKS).addOptional(new ResourceLocation("quark", "glow_shroom"));
		this.tag(AutumnityItemTags.TURKEY_FOOD).add(AutumnityItems.FOUL_BERRIES.get());
		this.tag(AutumnityItemTags.SNAIL_TEMPT_ITEMS).add(Items.WARPED_FUNGUS_ON_A_STICK).addTag(AutumnityItemTags.SNAIL_FOOD).addTag(AutumnityItemTags.SNAIL_SNACKS);
		this.tag(AutumnityItemTags.KNIVES);
		this.tag(AutumnityItemTags.TORCHES_ENDER).addOptional(AutumnityConstants.ENDER_TORCH);
		this.tag(AutumnityItemTags.TORCHES_CUPRIC).addOptional(AutumnityConstants.CUPRIC_TORCH);

		this.copy(Tags.Blocks.CHESTS_WOODEN, Tags.Items.CHESTS_WOODEN);
		this.copy(Tags.Blocks.CHESTS_TRAPPED, Tags.Items.CHESTS_TRAPPED);
		this.copy(Tags.Blocks.FENCES_WOODEN, Tags.Items.FENCES_WOODEN);
		this.copy(Tags.Blocks.FENCE_GATES_WOODEN, Tags.Items.FENCE_GATES_WOODEN);
		this.tag(Tags.Items.BOOKSHELVES).add(MAPLE_BOOKSHELF.get().asItem());
		this.tag(AutumnityItemTags.COOKED_TURKEY).add(AutumnityBlocks.COOKED_TURKEY.get().asItem(), AutumnityItems.COOKED_TURKEY_PIECE.get());
		this.tag(AutumnityItemTags.RAW_TURKEY).add(AutumnityBlocks.TURKEY.get().asItem(), AutumnityItems.TURKEY_PIECE.get());
		this.tag(AutumnityItemTags.SEEDS_FOUL_BERRY).add(AutumnityItems.FOUL_BERRY_PIPS.get());
		this.tag(Tags.Items.SEEDS).addTag(AutumnityItemTags.SEEDS_FOUL_BERRY);
		this.tag(Tags.Items.EGGS).add(AutumnityItems.TURKEY_EGG.get());
		this.tag(BlueprintItemTags.PUMPKINS).add(LARGE_PUMPKIN_SLICE.get().asItem());
		this.tag(Tags.Items.ARMORS_CHESTPLATES).add(AutumnityItems.SNAIL_SHELL_CHESTPLATE.get());
	}
}