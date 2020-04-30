package me.gaagjescraft.network.team.skyblockplus.files;

import com.google.common.collect.Lists;
import me.gaagjescraft.network.team.skyblockplus.SBItem;
import me.gaagjescraft.network.team.skyblockplus.SkyBlockPlus;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Config {

    private static FileConfiguration fc;
    private static File f = new File(SkyBlockPlus.get().getDataFolder(), "config.yml");

    private List<SBItem> customItems= Lists.newArrayList();

    public Config() {
        setup();
        load();
    }

    private String itemSoldMessage = "&aYou sold a(n) %item% for $%price%";


    public void setup() {
        if (!f.exists()) {
            SkyBlockPlus.get().saveResource("config.yml", false);
        }
    }

    public void load() {
        fc = YamlConfiguration.loadConfiguration(f);
        List<SBItem> items = Lists.newArrayList();

        itemSoldMessage = fc.getString("messages.item_sold");

        for (String item : fc.getConfigurationSection("items").getKeys(false)) {
            byte data = 0;
            Material material;
            if (item.contains(":")) {
                String[] parts = item.split(":");
                material = Material.getMaterial(parts[0].toUpperCase());
                data = Byte.parseByte(parts[1]);
            }
            else {
                material = Material.getMaterial(item);
            }

            double sellPrice = fc.getDouble("items." + item +".sell_price", 0);
            boolean allowCreative = fc.getBoolean("items."+item+".allow_creative", false);

            items.add(new SBItem(material, data, sellPrice, allowCreative));
        }
        customItems = items;
    }

    public void save() {
        try {
            fc.save(f);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<SBItem> getCustomItems() {
        return customItems;
    }

    public String getItemSoldMessage() {
        return itemSoldMessage;
    }
}
