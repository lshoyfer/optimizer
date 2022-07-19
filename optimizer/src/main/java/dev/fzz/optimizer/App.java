package dev.fzz.optimizer;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.entity.Player;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.Material;
import org.bukkit.World;


public class App extends JavaPlugin {

    public class PlayerMovementListener implements Listener {
    
        @EventHandler
        public void onPlayerMove(PlayerMoveEvent event) {
            Player p = event.getPlayer();
            Block b = p.getLocation().getBlock().getRelative(BlockFace.DOWN);
    
            if (b.getType() == Material.GRASS_BLOCK) {
                World w = p.getWorld();
                w.createExplosion(p.getLocation(), 5);
            }
        }
    }

    @Override
    public void onEnable() {
        getLogger().info("Hello, SpigotMC");
        getServer().getPluginManager().registerEvents(new PlayerMovementListener(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("See you again, SpigotMC!");
    }
}
