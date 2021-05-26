package com.bruberu.gregtechfoodoption;

import com.bruberu.gregtechfoodoption.fluid.GTFOMetaFluids;
import com.bruberu.gregtechfoodoption.machines.GTFOTileEntities;
import com.bruberu.gregtechfoodoption.utils.GTFOLog;
import gregicadditions.GAEnums;
import gregicadditions.GAMaterials;
import gregicadditions.Gregicality;
import gregicadditions.fluid.GAMetaFluids;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.machines.GATileEntities;
import gregicadditions.utils.GALog;
import gregtech.api.GTValues;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.Material;
import gregtech.api.util.GTLog;
import gregtech.common.MetaFluids;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.init.Bootstrap;
import org.apache.logging.log4j.LogManager;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SimpleMaterialTest {

    /**
     * Initialize registries
     */
    @BeforeClass
    public static void bootStrap() {

        // Bootstrap basic Forge registries
        Bootstrap.register();

        // Bootstrap Loggers
        GTLog.init(LogManager.getLogger(GTValues.MODID)); // yes this was necessary
        GALog.init(LogManager.getLogger(Gregicality.MODID));
        GTFOLog.init(LogManager.getLogger(GregTechFoodOption.MODID));

        // Run Early handlers
        Materials.register();
        GAEnums.preInit();

        // Bootstrap Addon Materials
        GAMaterials gaMaterials = new GAMaterials();
        gaMaterials.onMaterialsInit();
        GTFOMaterialHandler gtfoMaterials = new GTFOMaterialHandler();
        gtfoMaterials.onMaterialsInit();
        Material.freezeRegistry();

        // Bootstrap GTCE Blocks
        MetaBlocks.init();

        // Bootstrap MTEs
        MetaTileEntities.init();
        GTFOTileEntities.init();
    }

    /**
     * Basic Nonnull test to try.
     *
     * The real test is in the bootStrap, where if there are conflicting material IDs registered,
     * it will throw an {@link IllegalArgumentException} and fail the test.
     */
    @Test
    public void areMaterialsGenerated() {
        assertNotNull(
                "OreDictUnifier failed to gather a GTCE Material ItemStack",
                Materials.Carbon
        );
        assertNotNull(
                "OreDictUnifier failed to gather a Gregicality Material ItemStack",
                GAMaterials.Tumbaga
        );
        assertNotNull(
                "OreDictUnifier failed to gather a GTFO Material ItemStack",
                GTFOMaterialHandler.AppleCandySyrup
        );
    }

    /**
     * Basic Nonnull test to try.
     *
     * The real test is in bootStrap, where if there are conflicting MTE ID's,
     * it will throw an {@link IllegalArgumentException} and fail the test.
     */
    @Test
    public void areMTEsGenerated() {
        assertNotNull(
                "GTCE MetaTileEntity is still null!",
                MetaTileEntities.DIESEL_ENGINE
        );
        assertNotNull(
                "GTFO MetaTileEntity is still null!",
                GTFOTileEntities.BIOREACTOR
        );
    }
}
