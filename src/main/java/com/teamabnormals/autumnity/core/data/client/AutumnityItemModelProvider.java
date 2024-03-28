package com.teamabnormals.autumnity.core.data.client;

import com.teamabnormals.autumnity.core.Autumnity;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile.UncheckedModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import static com.teamabnormals.autumnity.core.registry.AutumnityItems.*;

public class AutumnityItemModelProvider extends ItemModelProvider {

	public AutumnityItemModelProvider(PackOutput output, ExistingFileHelper helper) {
		super(output, Autumnity.MOD_ID, helper);
	}

	@Override
	protected void registerModels() {
		this.generatedItem(MAPLE_BOAT.getFirst().get(), MAPLE_BOAT.getSecond().get(), MAPLE_FURNACE_BOAT.get(), LARGE_MAPLE_BOAT.get());
		this.generatedItem(SAP_BOTTLE.get(), SYRUP_BOTTLE.get(), FOUL_BERRIES.get(), FOUL_BERRY_PIPS.get(), FOUL_SOUP.get(), PUMPKIN_BREAD.get());
		this.generatedItem(SNAIL_SHELL_PIECE.get(), TURKEY_EGG.get());
		this.handheldItem(TURKEY_PIECE.get(), COOKED_TURKEY_PIECE.get());
		this.generatedItem(MAPLE_LEAF_BANNER_PATTERN.get(), SWIRL_BANNER_PATTERN.get());
		this.spawnEggItem(SNAIL_SPAWN_EGG.get(), TURKEY_SPAWN_EGG.get());

//		ResourceLocation chestplate = ForgeRegistries.ITEMS.getKey(SNAIL_SHELL_CHESTPLATE.get());
//		OverrideBuilder chestplateModel = item(SNAIL_SHELL_CHESTPLATE.get(), "generated").override();
//		float trimType = 0.1F;
//		for (String trim : new String[]{"quartz", "iron", "netherite", "redstone", "copper", "gold", "emerald", "diamond", "lapis", "amethyst"}) {
//			ResourceLocation name = new ResourceLocation(chestplate.getNamespace(), chestplate.getPath() + "_" + trim + "_trim");
//			chestplateModel = chestplateModel.model(new UncheckedModelFile(name)).predicate(new ResourceLocation("trim_type"), trimType);
//			trimType += 0.1F;
//			withExistingParent(name.getPath(), "item/generated")
//					.texture("layer0", new ResourceLocation(this.modid, "item/" + chestplate.getPath()))
//					.texture("layer1", "minecraft:trims/items/chestplate_trim"); //_" + trim);
//		}
	}

	private void generatedItem(ItemLike... items) {
		for (ItemLike item : items)
			item(item, "generated");
	}

	private void handheldItem(ItemLike... items) {
		for (ItemLike item : items)
			item(item, "handheld");
	}

	private ItemModelBuilder item(ItemLike item, String type) {
		ResourceLocation itemName = ForgeRegistries.ITEMS.getKey(item.asItem());
		return withExistingParent(itemName.getPath(), "item/" + type).texture("layer0", new ResourceLocation(this.modid, "item/" + itemName.getPath()));
	}

	private void spawnEggItem(ItemLike... items) {
		for (ItemLike item : items) {
			ResourceLocation itemName = ForgeRegistries.ITEMS.getKey(item.asItem());
			withExistingParent(itemName.getPath(), "item/template_spawn_egg");
		}
	}

	private void blockItem(Block block) {
		ResourceLocation name = ForgeRegistries.BLOCKS.getKey(block);
		this.getBuilder(name.getPath()).parent(new UncheckedModelFile(new ResourceLocation(this.modid, "block/" + name.getPath())));
	}
}