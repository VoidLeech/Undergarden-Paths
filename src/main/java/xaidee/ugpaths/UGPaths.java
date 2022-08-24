package xaidee.ugpaths;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import net.minecraftforge.registries.DeferredRegister;
import xaidee.ugpaths.data.UGPBlockStates;
import xaidee.ugpaths.data.UGPItemModels;
import xaidee.ugpaths.data.UGPLang;
import xaidee.ugpaths.data.UGPLootTables;

@Mod(UGPaths.MOD_ID)
public class UGPaths {

    public static final String MOD_ID = "ugpaths";

    public UGPaths() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener(this::gatherData);

        DeferredRegister<?>[] registers = {
                UGPRegistry.BLOCKS,
                UGPRegistry.ITEMS
        };

        for (DeferredRegister<?> register : registers) {
            register.register(bus);
        }
    }

    public void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();

        if (event.includeClient()) {
            generator.addProvider(new UGPBlockStates(generator, helper));
            generator.addProvider(new UGPItemModels(generator, helper));
            generator.addProvider(new UGPLang(generator));
        }
        if (event.includeServer()) {
            generator.addProvider(new UGPLootTables(generator));
        }
    }
}
