package games.enchanted.verticalslabs.registry;

import games.enchanted.verticalslabs.block.CombinableVerticalSlabBlock;
import games.enchanted.verticalslabs.block.OxidizableVerticalSlab;
import games.enchanted.verticalslabs.CommonEntrypoint;
import games.enchanted.verticalslabs.VerticalSlabsConstants;
import games.enchanted.verticalslabs.block.BlockAndItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockBehaviour;

import javax.annotation.Nullable;

public class RegistryHelpers {
    public static BlockItem registerBlockItem(String id, Block block) {
        Item.Properties settings = new Item.Properties();
        return CommonEntrypoint.platformRegister.register(BuiltInRegistries.ITEM.key(), () -> new BlockItem(block, settings), new ResourceLocation(VerticalSlabsConstants.MOD_ID, id));
    }
    public static Block registerVerticalSlabBlock(String id, BlockBehaviour.Properties blockSettings) {
        return CommonEntrypoint.platformRegister.register(BuiltInRegistries.BLOCK.key(), () -> new CombinableVerticalSlabBlock(blockSettings), new ResourceLocation(VerticalSlabsConstants.MOD_ID, id));
    }
    public static Block registerVerticalSlabBlock(String id, BlockBehaviour.Properties blockSettings, WeatheringCopper.WeatherState oxidationLevel) {
        return CommonEntrypoint.platformRegister.register(BuiltInRegistries.BLOCK.key(), () -> new OxidizableVerticalSlab(oxidationLevel, blockSettings), new ResourceLocation(VerticalSlabsConstants.MOD_ID, id));
    }

    public static BlockAndItem registerVerticalSlab(String id, BlockBehaviour.Properties blockProperties) {
        return registerVerticalSlab(id,blockProperties,null);
    }

    public static BlockAndItem registerVerticalSlab(String id, BlockBehaviour.Properties blockProperties, @Nullable WeatheringCopper.WeatherState oxidationLevel) {
        final Block registeredBlock = oxidationLevel == null ? registerVerticalSlabBlock(id, blockProperties) : registerVerticalSlabBlock(id, blockProperties, oxidationLevel);
        final BlockItem registeredBlockItem = registerBlockItem(id, registeredBlock);
        return new BlockAndItem(registeredBlock, registeredBlockItem);
    }
}
