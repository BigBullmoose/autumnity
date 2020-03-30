package com.markus1002.autumnity.common.block;

import java.util.Random;

import com.markus1002.autumnity.core.registry.ModParticles;

import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ColoredMapleLeavesBlock extends LeavesBlock
{
	private final int color;

	public ColoredMapleLeavesBlock(Properties properties, int colorIn)
	{
		super(properties);
		this.color = colorIn;
	}

	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand)
	{
		super.animateTick(stateIn, worldIn, pos, rand);

		double d0 = (color >> 16 & 255) / 255.0F;
		double d1 = (color >> 8 & 255) / 255.0F;
		double d2 = (color & 255) / 255.0F;

		if (rand.nextInt(100) == 0)
		{
			BlockPos blockpos = pos.down();
			if (worldIn.isAirBlock(blockpos))
			{
				double d3 = (double)((float)pos.getX() + rand.nextFloat());
				double d4 = (double)pos.getY() - 0.05D;
				double d6 = (double)((float)pos.getZ() + rand.nextFloat());
				worldIn.addParticle(ModParticles.FALLING_LEAF, d3, d4, d6, d0, d1, d2);
			}
		}
	}
}