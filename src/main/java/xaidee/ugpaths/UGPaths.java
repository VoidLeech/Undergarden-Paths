package xaidee.ugpaths;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import xaidee.ugpaths.data.*;

import java.util.concurrent.CompletableFuture;

@Mod(UGPaths.MOD_ID)
public class UGPaths {

    public static final String MOD_ID = "ugpaths";

    public UGPaths() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener(this::gatherData);
        bus.addListener(UGPRegistry::buildContents);

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
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        if (event.includeClient()) {
            generator.addProvider(true, new UGPBlockStates(packOutput, helper));
            generator.addProvider(true, new UGPItemModels(packOutput, helper));
            generator.addProvider(true, new UGPLang(packOutput));
        }
        if (event.includeServer()) {
            generator.addProvider(true, UGPLootTables.create(packOutput));
            generator.addProvider(true, new UGPBlockTags(packOutput, lookupProvider, helper));
        }
    }
}
