package me.austinfrg.lookreddead;

import org.bukkit.Bukkit;
import org.bukkit.FluidCollisionMode;
import org.bukkit.block.Block;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class LookRedDead extends JavaPlugin implements CommandExecutor, Listener {

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
    }

    @EventHandler
    public void onPlayerMoveEvent(final PlayerMoveEvent event) {
        final Player player = event.getPlayer();
        Block targetBlock = player.getTargetBlockExact(Integer.parseInt(Objects.requireNonNull(getConfig().getString("distance-to-count"))), FluidCollisionMode.SOURCE_ONLY);
        if (targetBlock == null) return;
        if (this.getConfig().getStringList("red-blocks").contains(targetBlock.getType().toString())) {
            player.setHealth(0);
        }
    }
}