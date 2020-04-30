package me.gaagjescraft.network.team.skyblockplus;

import me.gaagjescraft.network.team.skyblockplus.events.BlockBreakHandler;
import me.gaagjescraft.network.team.skyblockplus.files.Config;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyBlockPlus extends JavaPlugin {

    private static Economy econ = null;

    private static SkyBlockPlus instance;
    public static SkyBlockPlus get() { return instance; }

    private Config config;

    @Override
    public void onEnable() {
        instance = this;
        this.config = new Config();

        if (!setupEconomy() ) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        Bukkit.getPluginManager().registerEvents(new BlockBreakHandler(), this);
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Config getCfg() {
        return instance.config;
    }

    public static Economy getEconomy() {
        return econ;
    }

}
