package com.teamabnormals.autumnity.core.data.client;

import com.teamabnormals.autumnity.core.Autumnity;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
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
		this.trimmableArmor(SNAIL_SHELL_CHESTPLATE.get());
	}

	private void trimmableArmor(ItemLike... items) {
		for (ItemLike item : items) {
			if (item.asItem() instanceof ArmorItem armor) {
				ResourceLocation location = ForgeRegistries.ITEMS.getKey(armor);
				ItemModelBuilder itemModel = item(armor, "generated");
				int trimType = 1;
				for (String trim : new String[]{"quartz", "iron", "netherite", "redstone", "copper", "gold", "emerald", "diamond", "lapis", "amethyst"}) {
					ResourceLocation name = new ResourceLocation(location.getNamespace(), "item/" + location.getPath() + "_" + trim + "_trim");
					itemModel.override().model(new UncheckedModelFile(name)).predicate(new ResourceLocation("trim_type"), (float) (trimType / 10.0));
					ResourceLocation texture = new ResourceLocation("trims/items/" + armor.getType().getName() + "_trim_" + trim);
					this.existingFileHelper.trackGenerated(texture, PackType.CLIENT_RESOURCES, ".png", "textures");
					withExistingParent(name.getPath(), "item/generated").texture("layer0", new ResourceLocation(this.modid, "item/" + location.getPath())).texture("layer1", texture);
					trimType++;
				}
			}
		}
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