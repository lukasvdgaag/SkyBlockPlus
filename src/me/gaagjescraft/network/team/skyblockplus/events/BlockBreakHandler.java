package me.gaagjescraft.network.team.skyblockplus.events;

import me.gaagjescraft.network.team.skyblockplus.SBItem;
import me.gaagjescraft.network.team.skyblockplus.SkyBlockPlus;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakHandler implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent e) {
        SBItem item = SBItem.matchMaterial(e.getBlock().getType(), e.getBlock().getData());
        if (item != null) {
            if (!item.isAllowCreative() && e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
                return;
            }

            EconomyResponse response = SkyBlockPlus.getEconomy().depositPlayer(e.getPlayer(), item.getSellPrice());
            e.getBlock().getDrops().clear();

            if (response.transactionSuccess()) {
                e.getBlock().setType(Material.AIR);
                e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', SkyBlockPlus.getCfg().getItemSoldMessage().replace("%item%", item.getmaterial().name()).replace("%price%", item.getSellPrice() + "")));
            }

        }
    }

}
