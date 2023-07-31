package gregtechfoodoption.machines.farmer;

import gregtechfoodoption.block.GTFORootCrop;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GTFORootCropFarmerMode extends GTFOCropFarmerMode {
    @Override
    public boolean canOperate(IBlockState state, MetaTileEntityFarmer farmer, BlockPos pos, World world) {
        return state.getBlock() instanceof GTFORootCrop &&
                (((GTFORootCrop) state.getBlock()).isMaxAge(state) ||
                        hasNoSeeds(farmer, (GTFORootCrop) state.getBlock()) ? ((GTFORootCrop) state.getBlock()).seedHarvestable(state) : ((GTFORootCrop) state.getBlock()).cropHarvestable(state));
    }

    private boolean hasNoSeeds(MetaTileEntityFarmer farmer, GTFORootCrop crop) {
        ItemStack seed = crop.getSeedStack();
        for (int i = 0; i < farmer.getImportItems().getSlots(); i++) {
            if (seed.isItemEqual(farmer.getImportItems().getStackInSlot(i))) {
                return false;
            }
        }
        return true;
    }
}
