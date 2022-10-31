package xaidee.ugpaths;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import xaidee.ugpaths.data.*;

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
            generator.addProvider(true, new UGPBlockStates(generator, helper));
            generator.addProvider(true, new UGPItemModels(generator, helper));
            generator.addProvider(true, new UGPLang(generator));
        }
        if (event.includeServer()) {
            generator.addProvider(true, new UGPLootTables(generator));
            generator.addProvider(true, new UGPBlockTags(generator, helper));
        }
    }
}
