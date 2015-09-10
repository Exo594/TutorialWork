package com.exo594.tutorial.world;

import com.exo594.tutorial.block.ModBlocks;
import cpw.mods.fml.common.IWorldGenerator;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ModWorldGen implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.dimensionId) {
            case 0: //Overworld
                this.runGenerator(this.gen_tutorial_ore, world, random, chunkX, chunkZ, 20, 0, 64);
                this.runGenerator(this.gen_multi_ore, world, random, chunkX, chunkZ, 10, 0, 16);
                break;
            case -1: //Nether

                break;
            case 1: //End
                this.runGenerator(this.gen_cobblestone, world, random, chunkX, chunkZ, 10, 0, 80);
                break;
        }
    }
    private WorldGenerator gen_tutorial_ore; //Generates Tutorial Ore (used in Overworld)
    private WorldGenerator gen_cobblestone;
    private WorldGenerator gen_multi_ore;

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
}
