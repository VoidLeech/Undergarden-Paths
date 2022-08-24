package xaidee.ugpaths.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.text.translate.JavaUnicodeEscaper;
import xaidee.ugpaths.UGPRegistry;
import xaidee.ugpaths.UGPaths;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.function.Supplier;

public class UGPLang implements DataProvider {
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().setLenient().create();
    private final Map<String, String> data = new TreeMap<>();
    private final DataGenerator gen;
    private final String modid;
    private final String locale;

    public UGPLang(DataGenerator generator) {
        this.gen = generator;
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
    }

    @Override
    public void run(HashCache cache) throws IOException {
        addTranslations();
        if (!data.isEmpty())
            save(cache, data, this.gen.getOutputFolder().resolve("assets/" + modid + "/lang/" + locale + ".json"));
    }

    @Override
    public String getName() {
        return "Undergarden Paths' Languages: " + locale;
    }

    public void save(HashCache cache, Object object, Path target) throws IOException {
        String data = GSON.toJson(object);
        data = JavaUnicodeEscaper.outsideOf(0, 0x7f).translate(data); // Escape unicode after the fact so that it's not double escaped by GSON
        String hash = DataProvider.SHA1.hashUnencodedChars(data).toString();
        if (!Objects.equals(cache.getHash(target), hash) || !Files.exists(target)) {
            Files.createDirectories(target.getParent());

            try (BufferedWriter bufferedWriter = Files.newBufferedWriter(target)) {
                bufferedWriter.write(data);
            }
        }

        cache.putNew(target, hash);
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
        String value = formatString(ForgeRegistries.BLOCKS.getKey(block.get()).getPath());
        data.putIfAbsent(key, value);
    }

    public void tryItem(Supplier<? extends Item> item) {
        String key = item.get().getDescriptionId();
        String value = formatString(ForgeRegistries.ITEMS.getKey(item.get()).getPath());
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
