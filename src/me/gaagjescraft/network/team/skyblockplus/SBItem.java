package me.gaagjescraft.network.team.skyblockplus;


import org.bukkit.Material;

public class SBItem {

    private Material material;
    private byte data; // by default this is 0
    private double sellPrice; // by default this is 1
    private boolean allowCreative;

    public SBItem(Material material, byte data, double sellPrice, boolean allowCreative) {
        this.material = material;
        this.data = data;
        this.sellPrice = sellPrice;
        this.allowCreative = allowCreative;
    }

    public static SBItem matchMaterial(Material mat, byte data) {
        for (SBItem item : SkyBlockPlus.getCfg().getCustomItems()) {
            if (item.getmaterial().equals(mat) && item.getData() == data) {
                return item;
            }
        }
        return null;
    }

    public Material getmaterial() {
        return material;
    }

    public byte getData() {
        return data;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public boolean isAllowCreative() {
        return allowCreative;
    }
}
