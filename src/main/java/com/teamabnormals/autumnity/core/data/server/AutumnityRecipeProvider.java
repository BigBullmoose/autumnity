package com.teamabnormals.autumnity.core.data.server;

import com.google.common.collect.Maps;
import com.teamabnormals.autumnity.core.Autumnity;
import com.teamabnormals.autumnity.core.other.AutumnityBlockFamilies;
import com.teamabnormals.autumnity.core.other.tags.AutumnityItemTags;
import com.teamabnormals.autumnity.core.registry.AutumnityBlocks;
import com.teamabnormals.autumnity.core.registry.AutumnityItems;
import com.teamabnormals.blueprint.core.Blueprint;
import com.teamabnormals.blueprint.core.api.conditions.BlueprintAndCondition;
import com.teamabnormals.blueprint.core.api.conditions.ConfigValueCondition;
import com.teamabnormals.blueprint.core.other.tags.BlueprintItemTags;
import com.teamabnormals.woodworks.core.Woodworks;
import com.teamabnormals.woodworks.core.WoodworksConfig;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.common.crafting.conditions.NotCondition;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;

import static com.teamabnormals.woodworks.core.WoodworksConfig.COMMON;

public class AutumnityRecipeProvider extends RecipeProvider {
	public static final ModLoadedCondition WOODWORKS_LOADED = new ModLoadedCondition("woodworks");
	public static final ModLoadedCondition BERRY_GOOD_LOADED = new ModLoadedCondition("berry_good");
	public static final ModLoadedCondition BOATLOAD_LOADED = new ModLoadedCondition("boatload");
	public static final ModLoadedCondition ENDERGETIC_LOADED = new ModLoadedCondition("endergetic");
	public static final ModLoadedCondition INCUBATION_LOADED = new ModLoadedCondition("incubation");
	public static final ModLoadedCondition CAVERNS_AND_CHASMS_LOADED = new ModLoadedCondition("caverns_and_chasms");
	public static final NotCondition ABNORMALS_DELIGHT_NOT_LOADED = new NotCondition(new ModLoadedCondition("abnormals_delight"));

	public static final BlueprintAndCondition WOODEN_BOARDS = woodworksCondition(null, "wooden_boards");
	public static final BlueprintAndCondition WOODEN_BEEHIVES = woodworksCondition(null, "wooden_beehives");
	public static final BlueprintAndCondition WOODEN_BOOKSHELVES = woodworksCondition(null, "wooden_bookshelves");
	public static final BlueprintAndCondition WOODEN_LADDERS = woodworksCondition(null, "wooden_ladders");
	public static final BlueprintAndCondition WOODEN_CHESTS = woodworksCondition(null, "wooden_chests");

	public AutumnityRecipeProvider(PackOutput output) {
		super(output);
	}

	@Override
	public void buildRecipes(Consumer<FinishedRecipe> consumer) {
		oneToOneConversionRecipe(consumer, Items.MAGENTA_DYE, AutumnityBlocks.AUTUMN_CROCUS.get(), "magenta_dye");
		conditionalRecipe(consumer, BERRY_GOOD_LOADED, RecipeCategory.MISC, oneToOneConversionRecipeBuilder(AutumnityItems.FOUL_BERRY_PIPS.get(), AutumnityItems.FOUL_BERRIES.get(), 1));
		conditionalNineBlockStorageRecipes(consumer, BERRY_GOOD_LOADED, RecipeCategory.FOOD, AutumnityItems.FOUL_BERRIES.get(), RecipeCategory.DECORATIONS, AutumnityBlocks.FOUL_BERRY_BASKET.get());
		conditionalRecipe(consumer, ABNORMALS_DELIGHT_NOT_LOADED, RecipeCategory.FOOD, ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, AutumnityItems.FOUL_SOUP.get()).requires(AutumnityItems.FOUL_BERRIES.get(), 2).requires(Items.SPIDER_EYE).requires(Items.BOWL, 1).unlockedBy("has_foul_berries", has(AutumnityItems.FOUL_BERRIES.get())));

		foodCookingRecipes(consumer, AutumnityItems.TURKEY_PIECE.get(), AutumnityItems.COOKED_TURKEY_PIECE.get());
		foodCookingRecipes(consumer, AutumnityBlocks.TURKEY.get(), AutumnityBlocks.COOKED_TURKEY.get());
		conditionalRecipe(consumer, ABNORMALS_DELIGHT_NOT_LOADED, RecipeCategory.FOOD, oneToOneConversionRecipeBuilder(RecipeCategory.FOOD, AutumnityItems.TURKEY_PIECE.get(), AutumnityBlocks.TURKEY.get(), 5), getModConversionRecipeName(AutumnityItems.TURKEY_PIECE.get(), AutumnityBlocks.TURKEY.get()));
		conditionalRecipe(consumer, ABNORMALS_DELIGHT_NOT_LOADED, RecipeCategory.FOOD, oneToOneConversionRecipeBuilder(RecipeCategory.FOOD, AutumnityItems.COOKED_TURKEY_PIECE.get(), AutumnityBlocks.COOKED_TURKEY.get(), 5), getModConversionRecipeName(AutumnityItems.COOKED_TURKEY_PIECE.get(), AutumnityBlocks.COOKED_TURKEY.get()));
		conditionalNineBlockStorageRecipes(consumer, INCUBATION_LOADED, RecipeCategory.MISC, AutumnityItems.TURKEY_EGG.get(), RecipeCategory.DECORATIONS, AutumnityBlocks.TURKEY_EGG_CRATE.get());

		foodCookingRecipes(consumer, AutumnityItems.SAP_BOTTLE.get(), AutumnityItems.SYRUP_BOTTLE.get());
		oneToOneConversionRecipe(consumer, Items.SUGAR, AutumnityItems.SAP_BOTTLE.get(), "sugar");
		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, AutumnityBlocks.SAPPY_MAPLE_LOG.get()).requires(AutumnityItems.SAP_BOTTLE.get()).requires(AutumnityBlocks.STRIPPED_MAPLE_LOG.get()).unlockedBy("has_sap_bottle", has(AutumnityItems.SAP_BOTTLE.get())).save(consumer, getModConversionRecipeName(AutumnityBlocks.SAPPY_MAPLE_LOG.get(), AutumnityItems.SAP_BOTTLE.get()));
		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, AutumnityBlocks.SAPPY_MAPLE_WOOD.get()).requires(AutumnityItems.SAP_BOTTLE.get()).requires(AutumnityBlocks.STRIPPED_MAPLE_WOOD.get()).unlockedBy("has_sap_bottle", has(AutumnityItems.SAP_BOTTLE.get())).save(consumer, getModConversionRecipeName(AutumnityBlocks.SAPPY_MAPLE_WOOD.get(), AutumnityItems.SAP_BOTTLE.get()));
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, AutumnityBlocks.PANCAKE.get()).requires(AutumnityItems.SYRUP_BOTTLE.get()).requires(BlueprintItemTags.MILK).requires(Tags.Items.EGGS).requires(Items.WHEAT, 2).unlockedBy("has_syrup_bottle", has(AutumnityItems.SYRUP_BOTTLE.get())).save(consumer);
		conditionalRecipe(consumer, ABNORMALS_DELIGHT_NOT_LOADED, RecipeCategory.FOOD, ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, AutumnityItems.PUMPKIN_BREAD.get(), 2).requires(AutumnityItems.SYRUP_BOTTLE.get()).requires(BlueprintItemTags.PUMPKINS).requires(Items.WHEAT, 2).unlockedBy("has_syrup_bottle", has(AutumnityItems.SYRUP_BOTTLE.get())));

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AutumnityBlocks.REDSTONE_JACK_O_LANTERN.get()).define('A', Blocks.CARVED_PUMPKIN).define('B', Blocks.REDSTONE_TORCH).pattern("A").pattern("B").unlockedBy("has_carved_pumpkin", has(Blocks.CARVED_PUMPKIN)).save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AutumnityBlocks.SOUL_JACK_O_LANTERN.get()).define('A', Blocks.CARVED_PUMPKIN).define('B', Blocks.SOUL_TORCH).pattern("A").pattern("B").unlockedBy("has_carved_pumpkin", has(Blocks.CARVED_PUMPKIN)).save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AutumnityBlocks.LARGE_PUMPKIN_SLICE.get(), 4).define('#', Blocks.PUMPKIN).pattern("##").pattern("##").unlockedBy("has_pumpkin", has(Blocks.PUMPKIN)).save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AutumnityBlocks.LARGE_JACK_O_LANTERN_SLICE.get()).define('A', AutumnityBlocks.CARVED_LARGE_PUMPKIN_SLICE.get()).define('B', Blocks.TORCH).pattern("A").pattern("B").unlockedBy("has_carved_large_pumpkin_slice", has(AutumnityBlocks.CARVED_LARGE_PUMPKIN_SLICE.get())).save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AutumnityBlocks.LARGE_REDSTONE_JACK_O_LANTERN_SLICE.get()).define('A', AutumnityBlocks.CARVED_LARGE_PUMPKIN_SLICE.get()).define('B', Blocks.REDSTONE_TORCH).pattern("A").pattern("B").unlockedBy("has_carved_large_pumpkin_slice", has(AutumnityBlocks.CARVED_LARGE_PUMPKIN_SLICE.get())).save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AutumnityBlocks.LARGE_SOUL_JACK_O_LANTERN_SLICE.get()).define('A', AutumnityBlocks.CARVED_LARGE_PUMPKIN_SLICE.get()).define('B', Blocks.SOUL_TORCH).pattern("A").pattern("B").unlockedBy("has_carved_large_pumpkin_slice", has(AutumnityBlocks.CARVED_LARGE_PUMPKIN_SLICE.get())).save(consumer);

		conditionalRecipe(consumer, ENDERGETIC_LOADED, RecipeCategory.BUILDING_BLOCKS, ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AutumnityBlocks.ENDER_JACK_O_LANTERN.get()).define('A', Blocks.CARVED_PUMPKIN).define('B', AutumnityItemTags.TORCHES_ENDER).pattern("A").pattern("B").unlockedBy("has_carved_pumpkin", has(Blocks.CARVED_PUMPKIN)));
		conditionalRecipe(consumer, ENDERGETIC_LOADED, RecipeCategory.BUILDING_BLOCKS, ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AutumnityBlocks.LARGE_ENDER_JACK_O_LANTERN_SLICE.get()).define('A', AutumnityBlocks.CARVED_LARGE_PUMPKIN_SLICE.get()).define('B', AutumnityItemTags.TORCHES_ENDER).pattern("A").pattern("B").unlockedBy("has_carved_large_pumpkin_slice", has(AutumnityBlocks.CARVED_LARGE_PUMPKIN_SLICE.get())));
		conditionalRecipe(consumer, CAVERNS_AND_CHASMS_LOADED, RecipeCategory.BUILDING_BLOCKS, ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AutumnityBlocks.CUPRIC_JACK_O_LANTERN.get()).define('A', Blocks.CARVED_PUMPKIN).define('B', AutumnityItemTags.TORCHES_CUPRIC).pattern("A").pattern("B").unlockedBy("has_carved_pumpkin", has(Blocks.CARVED_PUMPKIN)));
		conditionalRecipe(consumer, CAVERNS_AND_CHASMS_LOADED, RecipeCategory.BUILDING_BLOCKS, ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AutumnityBlocks.LARGE_CUPRIC_JACK_O_LANTERN_SLICE.get()).define('A', AutumnityBlocks.CARVED_LARGE_PUMPKIN_SLICE.get()).define('B', AutumnityItemTags.TORCHES_CUPRIC).pattern("A").pattern("B").unlockedBy("has_carved_large_pumpkin_slice", has(AutumnityBlocks.CARVED_LARGE_PUMPKIN_SLICE.get())));

		nineBlockStorageRecipes(consumer, RecipeCategory.MISC, AutumnityBlocks.SNAIL_GOO.get(), RecipeCategory.DECORATIONS, AutumnityBlocks.SNAIL_GOO_BLOCK.get());
		nineBlockStorageRecipes(consumer, RecipeCategory.MISC, AutumnityItems.SNAIL_SHELL_PIECE.get(), RecipeCategory.BUILDING_BLOCKS, AutumnityBlocks.SNAIL_SHELL_BLOCK.get());
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AutumnityBlocks.SNAIL_SHELL_BRICKS.get(), 8).define('#', Blocks.STONE_BRICKS).define('S', AutumnityItems.SNAIL_SHELL_PIECE.get()).pattern("###").pattern("#S#").pattern("###").unlockedBy("has_snail_shell_piece", has(AutumnityItems.SNAIL_SHELL_PIECE.get())).save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AutumnityBlocks.SNAIL_SHELL_TILES.get(), 8).define('#', Blocks.SMOOTH_STONE).define('S', AutumnityItems.SNAIL_SHELL_PIECE.get()).pattern("###").pattern("#S#").pattern("###").unlockedBy("has_snail_shell_piece", has(AutumnityItems.SNAIL_SHELL_PIECE.get())).save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, AutumnityItems.SNAIL_SHELL_CHESTPLATE.get()).define('X', AutumnityItems.SNAIL_SHELL_PIECE.get()).pattern("X X").pattern("XXX").pattern("XXX").unlockedBy("has_snail_shell_piece", has(AutumnityItems.SNAIL_SHELL_PIECE.get())).save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AutumnityItems.SWIRL_BANNER_PATTERN.get()).requires(Items.PAPER).requires(AutumnityItems.SNAIL_SHELL_PIECE.get()).unlockedBy("has_snail_shell_piece", has(AutumnityItems.SNAIL_SHELL_PIECE.get())).save(consumer);

		generateRecipes(consumer, AutumnityBlockFamilies.SNAIL_SHELL_BRICKS_FAMILY);
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, AutumnityBlocks.SNAIL_SHELL_BRICK_SLAB.get(), AutumnityBlocks.SNAIL_SHELL_BRICKS.get(), 2);
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, AutumnityBlocks.SNAIL_SHELL_BRICK_STAIRS.get(), AutumnityBlocks.SNAIL_SHELL_BRICKS.get());
		stonecutterResultFromBase(consumer, RecipeCategory.DECORATIONS, AutumnityBlocks.SNAIL_SHELL_BRICK_WALL.get(), AutumnityBlocks.SNAIL_SHELL_BRICKS.get());
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, AutumnityBlocks.CHISELED_SNAIL_SHELL_BRICKS.get(), AutumnityBlocks.SNAIL_SHELL_BRICKS.get());

		generateRecipes(consumer, AutumnityBlockFamilies.SNAIL_SHELL_TILES_FAMILY);
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, AutumnityBlocks.SNAIL_SHELL_TILE_SLAB.get(), AutumnityBlocks.SNAIL_SHELL_TILES.get(), 2);
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, AutumnityBlocks.SNAIL_SHELL_TILE_STAIRS.get(), AutumnityBlocks.SNAIL_SHELL_TILES.get());
		stonecutterResultFromBase(consumer, RecipeCategory.DECORATIONS, AutumnityBlocks.SNAIL_SHELL_TILE_WALL.get(), AutumnityBlocks.SNAIL_SHELL_TILES.get());
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, AutumnityBlocks.SNAIL_SHELL_TILES.get(), AutumnityBlocks.SNAIL_SHELL_BRICKS.get());
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, AutumnityBlocks.SNAIL_SHELL_TILE_SLAB.get(), AutumnityBlocks.SNAIL_SHELL_BRICKS.get(), 2);
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, AutumnityBlocks.SNAIL_SHELL_TILE_STAIRS.get(), AutumnityBlocks.SNAIL_SHELL_BRICKS.get());
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, AutumnityBlocks.SNAIL_SHELL_TILE_WALL.get(), AutumnityBlocks.SNAIL_SHELL_BRICKS.get());

		generateRecipes(consumer, AutumnityBlockFamilies.MAPLE_PLANKS_FAMILY);
		planksFromLogs(consumer, AutumnityBlocks.MAPLE_PLANKS.get(), AutumnityItemTags.MAPLE_LOGS, 4);
		woodFromLogs(consumer, AutumnityBlocks.MAPLE_WOOD.get(), AutumnityBlocks.MAPLE_LOG.get());
		woodFromLogs(consumer, AutumnityBlocks.STRIPPED_MAPLE_WOOD.get(), AutumnityBlocks.STRIPPED_MAPLE_LOG.get());
		woodFromLogs(consumer, AutumnityBlocks.SAPPY_MAPLE_WOOD.get(), AutumnityBlocks.SAPPY_MAPLE_LOG.get());
		hangingSign(consumer, AutumnityBlocks.MAPLE_HANGING_SIGNS.getFirst().get(), AutumnityBlocks.STRIPPED_MAPLE_LOG.get());
		leafPileRecipes(consumer, AutumnityBlocks.MAPLE_LEAVES.get(), AutumnityBlocks.MAPLE_LEAF_PILE.get());
		leafPileRecipes(consumer, AutumnityBlocks.YELLOW_MAPLE_LEAVES.get(), AutumnityBlocks.YELLOW_MAPLE_LEAF_PILE.get());
		leafPileRecipes(consumer, AutumnityBlocks.ORANGE_MAPLE_LEAVES.get(), AutumnityBlocks.ORANGE_MAPLE_LEAF_PILE.get());
		leafPileRecipes(consumer, AutumnityBlocks.RED_MAPLE_LEAVES.get(), AutumnityBlocks.RED_MAPLE_LEAF_PILE.get());
		woodenBoat(consumer, AutumnityItems.MAPLE_BOAT.getFirst().get(), AutumnityBlocks.MAPLE_PLANKS.get());
		chestBoat(consumer, AutumnityItems.MAPLE_BOAT.getSecond().get(), AutumnityItems.MAPLE_BOAT.getFirst().get());
		conditionalRecipe(consumer, BOATLOAD_LOADED, RecipeCategory.TRANSPORTATION, ShapelessRecipeBuilder.shapeless(RecipeCategory.TRANSPORTATION, AutumnityItems.MAPLE_FURNACE_BOAT.get()).requires(Blocks.FURNACE).requires(AutumnityItems.MAPLE_BOAT.getFirst().get()).group("furnace_boat").unlockedBy("has_boat", has(ItemTags.BOATS)));
		conditionalRecipe(consumer, BOATLOAD_LOADED, RecipeCategory.TRANSPORTATION, ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, AutumnityItems.LARGE_MAPLE_BOAT.get()).define('#', AutumnityBlocks.MAPLE_PLANKS.get()).define('B', AutumnityItems.MAPLE_BOAT.getFirst().get()).pattern("#B#").pattern("###").group("large_boat").unlockedBy("has_boat", has(ItemTags.BOATS)));
		conditionalRecipe(consumer, WOODEN_BOARDS, RecipeCategory.DECORATIONS, ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AutumnityBlocks.MAPLE_BOARDS.get(), 3).define('#', AutumnityBlocks.MAPLE_PLANKS.get()).pattern("#").pattern("#").pattern("#").group("wooden_boards").unlockedBy(getHasName(AutumnityBlocks.MAPLE_PLANKS.get()), has(AutumnityBlocks.MAPLE_PLANKS.get())));
		conditionalRecipe(consumer, WOODEN_BOOKSHELVES, RecipeCategory.BUILDING_BLOCKS, ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AutumnityBlocks.MAPLE_BOOKSHELF.get()).define('#', AutumnityBlocks.MAPLE_PLANKS.get()).define('X', Items.BOOK).pattern("###").pattern("XXX").pattern("###").group("wooden_bookshelf").unlockedBy("has_book", has(Items.BOOK)));
		conditionalRecipe(consumer, WOODEN_BOOKSHELVES, RecipeCategory.BUILDING_BLOCKS,  ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AutumnityBlocks.CHISELED_MAPLE_BOOKSHELF.get()).define('#', AutumnityBlocks.MAPLE_PLANKS.get()).define('X', AutumnityBlocks.MAPLE_SLAB.get()).pattern("###").pattern("XXX").pattern("###").unlockedBy("has_book", has(Items.BOOK)));
		conditionalRecipe(consumer, WOODEN_LADDERS, RecipeCategory.DECORATIONS, ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AutumnityBlocks.MAPLE_LADDER.get(), 4).define('#', AutumnityBlocks.MAPLE_PLANKS.get()).define('S', Items.STICK).pattern("S S").pattern("S#S").pattern("S S").group("wooden_ladder").unlockedBy("has_stick", has(Items.STICK)));
		conditionalRecipe(consumer, WOODEN_BEEHIVES, RecipeCategory.DECORATIONS, ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AutumnityBlocks.MAPLE_BEEHIVE.get()).define('#', AutumnityBlocks.MAPLE_PLANKS.get()).define('H', Items.HONEYCOMB).pattern("###").pattern("HHH").pattern("###").group("wooden_beehive").unlockedBy("has_honeycomb", has(Items.HONEYCOMB)));
		conditionalRecipe(consumer, WOODEN_CHESTS, RecipeCategory.DECORATIONS, ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AutumnityBlocks.MAPLE_CHEST.get()).define('#', AutumnityBlocks.MAPLE_PLANKS.get()).pattern("###").pattern("# #").pattern("###").group("wooden_chest").unlockedBy("has_lots_of_items", new InventoryChangeTrigger.TriggerInstance(ContextAwarePredicate.ANY, MinMaxBounds.Ints.atLeast(10), MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, new ItemPredicate[0])));
		conditionalRecipe(consumer, WOODEN_CHESTS, RecipeCategory.REDSTONE, ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, AutumnityBlocks.TRAPPED_MAPLE_CHEST.get()).requires(AutumnityBlocks.MAPLE_CHEST.get()).requires(Blocks.TRIPWIRE_HOOK).group("wooden_trapped_chest").unlockedBy("has_tripwire_hook", has(Blocks.TRIPWIRE_HOOK)));

		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, Blocks.CAKE).define('A', BlueprintItemTags.BUCKETS_MILK).define('B', Items.SUGAR).define('C', Items.WHEAT).define('E', Tags.Items.EGGS).pattern("AAA").pattern("BEB").pattern("CCC").unlockedBy("has_egg", has(Items.EGG)).save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, Items.PUMPKIN_PIE).requires(BlueprintItemTags.PUMPKINS).requires(Items.SUGAR).requires(Tags.Items.EGGS).unlockedBy("has_carved_pumpkin", has(Blocks.CARVED_PUMPKIN)).unlockedBy("has_pumpkin", has(BlueprintItemTags.PUMPKINS)).save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, Items.PUMPKIN_SEEDS, 4).requires(BlueprintItemTags.PUMPKINS).unlockedBy("has_pumpkin", has(BlueprintItemTags.PUMPKINS)).save(consumer);
	}

	public static BlueprintAndCondition woodworksCondition(ConfigValue<?> value, String valueID) {
		return new BlueprintAndCondition(new ResourceLocation(Blueprint.MOD_ID, "and"), List.of(WOODWORKS_LOADED, new ConfigValueCondition(new ResourceLocation(Woodworks.MOD_ID, "config"), value, valueID, Maps.newHashMap(), false)));
	}

	public static void nineBlockStorageRecipes(Consumer<FinishedRecipe> consumer, RecipeCategory itemCategory, ItemLike item, RecipeCategory storageCategory, ItemLike storage) {
		nineBlockStorageRecipes(consumer, itemCategory, item, storageCategory, storage, Autumnity.MOD_ID + ":" + getSimpleRecipeName(storage), null, Autumnity.MOD_ID + ":" + getSimpleRecipeName(item), null);
	}

	public static void oneToOneConversionRecipe(Consumer<FinishedRecipe> consumer, ItemLike output, ItemLike input, @Nullable String group) {
		oneToOneConversionRecipe(consumer, output, input, group, 1);
	}

	public static void oneToOneConversionRecipe(Consumer<FinishedRecipe> consumer, ItemLike output, ItemLike input, @Nullable String group, int count) {
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, output, count).requires(input).group(group).unlockedBy(getHasName(input), has(input)).save(consumer, getModConversionRecipeName(output, input));
	}

	public static ShapelessRecipeBuilder oneToOneConversionRecipeBuilder(RecipeCategory category, ItemLike output, ItemLike input, int count) {
		return ShapelessRecipeBuilder.shapeless(category, output, count).requires(input).unlockedBy(getHasName(input), has(input));
	}

	public static ShapelessRecipeBuilder oneToOneConversionRecipeBuilder(ItemLike output, ItemLike input, int count) {
		return oneToOneConversionRecipeBuilder(RecipeCategory.MISC, output, input, count);
	}

	public static void foodCookingRecipes(Consumer<FinishedRecipe> consumer, ItemLike input, ItemLike output) {
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(input), RecipeCategory.FOOD, output, 0.35F, 200).unlockedBy(getHasName(input), has(input)).save(consumer);
		SimpleCookingRecipeBuilder.smoking(Ingredient.of(input), RecipeCategory.FOOD, output, 0.35F, 100).unlockedBy(getHasName(input), has(input)).save(consumer, RecipeBuilder.getDefaultRecipeId(output) + "_from_smoking");
		SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(input), RecipeCategory.FOOD, output, 0.35F, 600).unlockedBy(getHasName(input), has(input)).save(consumer, RecipeBuilder.getDefaultRecipeId(output) + "_from_campfire_cooking");
	}

	public static void leafPileRecipes(Consumer<FinishedRecipe> consumer, ItemLike leaves, ItemLike leafPile) {
		ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, leafPile, 4).requires(leaves).group("leaf_pile").unlockedBy(getHasName(leaves), has(leaves)).save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, leaves).define('#', leafPile).pattern("##").pattern("##").group("leaves").unlockedBy(getHasName(leafPile), has(leafPile)).save(consumer, getModConversionRecipeName(leaves, leafPile));
	}

	public static void stonecutterResultFromBase(Consumer<FinishedRecipe> consumer, RecipeCategory category, ItemLike output, ItemLike input) {
		stonecutterResultFromBase(consumer, category, output, input, 1);
	}

	public static void stonecutterResultFromBase(Consumer<FinishedRecipe> consumer, RecipeCategory category, ItemLike output, ItemLike input, int count) {
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(input), category, output, count).unlockedBy(getHasName(input), has(input)).save(consumer, getModConversionRecipeName(output, input) + "_stonecutting");
	}

	protected static ResourceLocation getModConversionRecipeName(ItemLike output, ItemLike input) {
		return new ResourceLocation(Autumnity.MOD_ID, getConversionRecipeName(output, input));
	}

	public static void conditionalNineBlockStorageRecipes(Consumer<FinishedRecipe> consumer, ICondition condition, RecipeCategory itemCategory, ItemLike item, RecipeCategory storageCategory, ItemLike storage) {
		conditionalNineBlockStorageRecipes(consumer, condition, itemCategory, item, storageCategory, storage, Autumnity.MOD_ID + ":" + getSimpleRecipeName(storage), null, Autumnity.MOD_ID + ":" + getSimpleRecipeName(item), null);
	}

	protected static void conditionalNineBlockStorageRecipes(Consumer<FinishedRecipe> consumer, ICondition condition, RecipeCategory itemCategory, ItemLike item, RecipeCategory storageCategory, ItemLike storage, String storageLocation, @Nullable String itemGroup, String itemLocation, @Nullable String storageGroup) {
		conditionalRecipe(consumer, condition, itemCategory, ShapelessRecipeBuilder.shapeless(itemCategory, item, 9).requires(storage).group(storageGroup).unlockedBy(getHasName(storage), has(storage)), new ResourceLocation(itemLocation));
		conditionalRecipe(consumer, condition, storageCategory, ShapedRecipeBuilder.shaped(storageCategory, storage).define('#', item).pattern("###").pattern("###").pattern("###").group(itemGroup).unlockedBy(getHasName(item), has(item)), new ResourceLocation(storageLocation));
	}

	public static void conditionalRecipe(Consumer<FinishedRecipe> consumer, ICondition condition, RecipeCategory category, RecipeBuilder recipe) {
		conditionalRecipe(consumer, condition, category, recipe, RecipeBuilder.getDefaultRecipeId(recipe.getResult()));
	}

	public static void conditionalRecipe(Consumer<FinishedRecipe> consumer, ICondition condition, RecipeCategory category, RecipeBuilder recipe, ResourceLocation id) {
		ConditionalRecipe.builder().addCondition(condition).addRecipe(consumer1 -> recipe.save(consumer1, id)).generateAdvancement(new ResourceLocation(id.getNamespace(), "recipes/" + category.getFolderName() + "/" + id.getPath())).build(consumer, id);
	}

	public static void chestBoat(Consumer<FinishedRecipe> consumer, ItemLike chestBoat, ItemLike boat) {
		ShapelessRecipeBuilder.shapeless(RecipeCategory.TRANSPORTATION, chestBoat).requires(Tags.Items.CHESTS_WOODEN).requires(boat).group("chest_boat").unlockedBy("has_boat", has(ItemTags.BOATS)).save(consumer);
	}
}