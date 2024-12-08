package dev.berogov.changecolor.core;

import dev.berogov.changecolor.ChangeColorMod;
import dev.berogov.changecolor.block.changecolor.ChangeColorBlock;


import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class Registration {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ChangeColorMod.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ChangeColorMod.MOD_ID);

    public static final RegistryObject<Block> CHANGE_COLOR_BLOCK = BLOCKS.register("change_color_block",
            ChangeColorBlock::new);

    public static final RegistryObject<Item> CHANGE_COLOR_BLOCK_ITEM = ITEMS.register("change_color_block",
            () -> new BlockItem(CHANGE_COLOR_BLOCK.get(),
                    new Item.Properties()
                            .group(ItemGroup.BUILDING_BLOCKS)));

    private Registration() {}

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}