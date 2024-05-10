package xaidee.ugpaths.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;
import xaidee.ugpaths.UGPRegistry;
import xaidee.ugpaths.UGPaths;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Supplier;

public class UGPLang extends LanguageProvider {
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().setLenient().create();
    private final Map<String, String> data = new TreeMap<>();
    private final String modid;
    private final String locale;

    public UGPLang(PackOutput packOutput) {
        super(packOutput, UGPaths.MOD_ID, "en_us");
        this.modid = UGPaths.MOD_ID;
        this.locale = "en_us";
    }

    protected void addTranslations() {



        /*
            Automatically create translations for blocks and items based on their registry name.
            This must be at the very bottom to avoid overwriting errors. These functions ignore objects
            that have already been translated above.
         */
        for (int i = 0; UGPRegistry.BLOCKS.getEntries().size() > i; i++) {
            tryBlock(UGPRegistry.BLOCKS.getEntries().stream().toList().get(i));
        }
        for (int i = 0; UGPRegistry.ITEMS.getEntries().size() > i; i++) {
            tryItem(UGPRegistry.ITEMS.getEntries().stream().toList().get(i));
        }
        data.forEach(super::add);
    }

    @Override
    public String getName() {
        return "Undergarden Paths' Languages: " + locale;
    }

    public void save(CachedOutput cache, Object object, Path target) throws IOException {
        JsonObject json = new JsonObject();
        for (Map.Entry<String, String> pair : data.entrySet()) {
            json.addProperty(pair.getKey(), pair.getValue());
        }

        DataProvider.saveStable(cache, json, target);
    }

    public void add(String key, String value) {
        if (data.put(key, value) != null)
            throw new IllegalStateException("Duplicate translation key " + key);
    }

    public void addBlock(Supplier<? extends Block> key, String name) {
        addBlock(key.get(), name);
    }

    public void addBlock(Block key, String name) {
        add(key.getDescriptionId(), name);
    }

    public void addItem(Item key, String name) {
        add(key.getDescriptionId(), name);
    }

    public void tryBlock(Supplier<? extends Block> block) {
        String key = block.get().getDescriptionId();
        String value = formatString(BuiltInRegistries.BLOCK.getKey(block.get()).getPath());
        data.putIfAbsent(key, value);
    }

    public void tryItem(Supplier<? extends Item> item) {
        String key = item.get().getDescriptionId();
        String value = formatString(BuiltInRegistries.ITEM.getKey(item.get()).getPath());
        data.putIfAbsent(key, value);
    }

    private String formatString(String key) {
        String[] strArr = key.split("_");
        StringBuffer res = new StringBuffer();
        for (String str : strArr) {
            char[] stringArray = str.trim().toCharArray();
            stringArray[0] = Character.toUpperCase(stringArray[0]);
            str = new String(stringArray);

            res.append(str).append(" ");
        }
        return res.toString().trim();
    }
}
