package me.LordPrettyASAP.TestPlugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin implements Listener {
	
	@Override
	public void onEnable(){
		getLogger().info("Hello dude");
		getServer().getPluginManager().registerEvents(this, this);
	}
    @Override
	public void onDisable(){
		getLogger().info("Goodbye dude");
	}
    
    @EventHandler
    public void onBoom(EntityExplodeEvent event){
    	Entity entity = event.getEntity();
    	if (entity != null){
    		if ((entity instanceof TNTPrimed)){
    			event.blockList().clear();
    		}
    			
    	}
    	
    }
    
    @EventHandler(priority=EventPriority.HIGHEST)
    public void onPlace(BlockPlaceEvent event){
    	Player player = event.getPlayer();
    	Block block = event.getBlock();
    	if (!player.isOp() && block.getType()==Material.TNT){
    		Bukkit.broadcastMessage(player.getName() + " was naughty, and tried to place TNT!");
    		event.setCancelled(true);
    	}
    }
    
    @EventHandler
    public void onInteract(PlayerInteractEvent event){
    	Player player = event.getPlayer();
    	if (event.getAction()==Action.RIGHT_CLICK_BLOCK){
    		Block block = event.getClickedBlock();
    		if (player.isSneaking()){
    			block.setType(Material.REDSTONE_BLOCK);
    			player.sendMessage("Block Changed to Redstone");
    		}
    	}
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	
    	if (cmd.getName().equalsIgnoreCase("report")){
    	
    		sender.sendMessage("Player reported");
    		
    		return true;
    	}
    	return false;
    }
}
