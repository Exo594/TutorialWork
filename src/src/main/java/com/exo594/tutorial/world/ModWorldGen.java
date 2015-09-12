package com.exo594.tutorial.world;

import com.exo594.tutorial.block.ModBlocks;
import cpw.mods.fml.common.IWorldGenerator;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fluids.FluidRegistry;

public class ModWorldGen implements IWorldGenerator {

    private static final int pyramidHeight = 3;

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        int x = chunkX * 16;
        int z = chunkZ * 16;

        switch (world.provider.dimensionId) {
            case 0: //Overworld
                this.runGenerator(this.gen_tutorial_ore, world, random, chunkX, chunkZ, 20, 0, 64);
                this.runGenerator(this.gen_multi_ore, world, random, chunkX, chunkZ, 10, 0, 16);
                this.generateSurface(world, x, z, random);
                break;
            case -1: //Nether

                break;
            case 1: //End
                this.runGenerator(this.gen_cobblestone, world, random, chunkX, chunkZ, 10, 0, 80);
                break;
        }
    }
    private final WorldGenerator gen_tutorial_ore; //Generates Tutorial Ore (used in Overworld)
    private final WorldGenerator gen_cobblestone;
    private final WorldGenerator gen_multi_ore;

    public ModWorldGen() {
        this.gen_tutorial_ore = new WorldGenMinable(ModBlocks.tutorial_ore, 8);
        this.gen_cobblestone = new WorldGenMinable(Blocks.cobblestone, 16, Blocks.end_stone);
        this.gen_multi_ore = new WorldGenSingleMinable(ModBlocks.tutorial_multi_ore);
    }

    private void runGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight) {
        if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight) {
            throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");
        }

        int heightDiff = maxHeight - minHeight + 1;
        for (int i = 0; i < chancesToSpawn; i++) {
            int x = chunk_X * 16 + rand.nextInt(16);
            int y = minHeight + rand.nextInt(heightDiff);
            int z = chunk_Z * 16 + rand.nextInt(16);
            generator.generate(world, rand, x, y, z);
        }
    }

    private void generateSurface(World world, int x, int z, Random random) {
        if (random.nextInt(50) == 0) {
            int randX = x + random.nextInt(16);
            int randZ = z + random.nextInt(16);
            int randY = world.getHeightValue(randX, randZ);

            if (randY <= (world.getHeight() - pyramidHeight) && world.getBiomeGenForCoords(randX, randZ) != BiomeGenBase.river && world.getBiomeGenForCoords(randX, randZ) != BiomeGenBase.ocean) {
                Block block = world.getBlock(randX, randY - 1, randZ);
                //if(block != Blocks.water && block != Blocks.lava && block != Blocks.flowing_water && block != Blocks.flowing_lava) {
                if (!(block instanceof BlockLiquid || FluidRegistry.lookupFluidForBlock(block) != null)) {
                    generatePyramid(world, randX, randY, randZ);
                }
            }
        }

        /*for (int i = 0; i < 5; i++) {     //believed to be random block placing
         int randX = x + random.nextInt(16);
         int randY = 20 + random.nextInt(40);
         int randZ = z + random.nextInt(16);
         flagGen.generate(world, random, randX, randY, randZ);
         }*/
    }

    private void generatePyramid(World world, int x, int y, int z) { //Modify setup to generate pyramid instead of flag
        for (int i = 0; i < pyramidHeight; i++) {                    //Redstone column, supposedly 3 high
            world.setBlock(x, y + i, z, Blocks.redstone_block, 0, 2);
        }
        world.setBlock(x + 1, y + 1, z, Blocks.stonebrick, 0, 2);   //Mid-level of pyramid
        world.setBlock(x - 1, y + 1, z, Blocks.stonebrick, 0, 2);
        world.setBlock(x, y + 1, z + 1, Blocks.stonebrick, 0, 2);
        world.setBlock(x, y + 1, z - 1, Blocks.stonebrick, 0, 2);
        world.setBlock(x + 1, y + 1, z + 1, Blocks.stonebrick, 0, 2);
        world.setBlock(x - 1, y + 1, z + 1, Blocks.stonebrick, 0, 2);
        world.setBlock(x - 1, y + 1, z - 1, Blocks.stonebrick, 0, 2);
        world.setBlock(x + 1, y + 1, z - 1, Blocks.stonebrick, 0, 2);
    }
}
