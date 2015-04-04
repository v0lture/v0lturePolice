package me.jazzandmax.police;

import java.util.logging.Logger;
import java.util.Random;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class Police extends JavaPlugin{
	
	double rand = new Random().nextDouble();
	double resistOdds;
	int resistTriesTransfer;
	//String playerName;
	HashMap<Player, Integer> resistTries = new HashMap<Player, Integer>();
	
	public final Logger logger = Logger.getLogger("Minecraft");
	public static Police plugin;
	
	@Override
	public void onDisable(){
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " has shutdown.");
	}
	public void onEnable(){
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " has booted. You're running version " + pdfFile.getVersion() + ".");
		getConfig().options().copyDefaults(true);
		saveConfig();
		if (getConfig().getInt("X") == (0) && getConfig().getInt("Y") == (0) && getConfig().getInt("Z") == (0)){
			this.logger.warning(pdfFile.getName() + " has an config issue. Jail location has NOT been set.");
			getConfig().set("setup", false);
			Bukkit.broadcast(ChatColor.DARK_GREEN + "[jazzandmax] " + ChatColor.DARK_RED + "Module Error: " + ChatColor.RED + "Module POLICE has a config error, check console for more information.", "jm.police.*");
		} else if (getConfig().getInt("Y") == (0)) {
			getConfig().set("setup", false);
			this.logger.warning(pdfFile.getName() + " has an config issue. Jail location is at an unsafe Y axis.");
			Bukkit.broadcast(ChatColor.DARK_GREEN + "[jazzandmax] " + ChatColor.DARK_RED + "Module Error: " + ChatColor.RED + "Module POLICE has a config error, check console for more information.", "jm.police.*");
		} else {
			getConfig().set("setup", true);
		}
		
	}
	
	public Player getPlayer(String name) {
	    for(Player p : Bukkit.getOnlinePlayers()) {
	        if(p.getName().equalsIgnoreCase(name))
	            return p;
	    }
	    return null;
	}
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		Player player = (Player) sender;
		if(commandLabel.equalsIgnoreCase("cop")) {
			if(args.length == (0)){
				player.sendMessage(ChatColor.DARK_GREEN + "[Police] " + ChatColor.GREEN + "Unknown command.");
				 
			} else if (args.length == (2) && args[0].equalsIgnoreCase("teleport")){
				if(this.getServer().getPlayer(args[1])!=null){
					
					Player targetPlayer = this.getServer().getPlayer(args[1]);
					Location targetlocation = targetPlayer.getLocation();
					player.teleport(targetlocation);
					targetPlayer.sendMessage(ChatColor.DARK_GREEN + "[Police] " + ChatColor.RED + player + ChatColor.GREEN + " just teleported to you.");
					player.sendMessage(ChatColor.DARK_GREEN + "[Police] " + ChatColor.GREEN + "You teleported to " + ChatColor.RED + args[1]);
				} else {
					
					player.sendMessage(ChatColor.DARK_GREEN + "[Police] " + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "Teleport failed. Player is not online, check spelling and try again.");
				}
			} else if (args.length == (1) && args[0].equalsIgnoreCase("teleport") && player.hasPermission("jm.police.tp")){
				
				player.sendMessage(ChatColor.DARK_GREEN + "[Police] " + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "Insufficient arguments. Please select a player to teleport to.");
			
			} else if (args.length == (1) && args[0].equalsIgnoreCase("teleport") && !player.hasPermission("jm.police.tp")){
				
				player.sendMessage(ChatColor.DARK_GREEN + "[jazzandmax] " + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "Insufficient permissions.");
			
			} else if (args.length == (1) && args[0].equalsIgnoreCase("freeze") && player.hasPermission("jm.police.freeze")){
				
				player.sendMessage(ChatColor.DARK_GREEN + "[Police] " + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "Insufficient arguments. Please select a player to freeze.");
				
			} else if (args.length == (1) && args[0].equalsIgnoreCase("freeze") && !player.hasPermission("jm.police.freeze")){
				
				player.sendMessage(ChatColor.DARK_GREEN + "[jazzandmax] " + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "Insufficient permissions.");
				
			} else if (args.length == (2) && args[0].equalsIgnoreCase("freeze") && player.hasPermission("jm.police.freeze")){
				if (getPlayer(args[1]) != null){
					Player targetPlayer = getPlayer(args[1]);
					resistTries.put((targetPlayer), 3);
					player.sendMessage(ChatColor.DARK_GREEN + "[Police] " + ChatColor.GREEN + "Resist tries = " + resistTries.get(player));
					targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 10000, 255));
					targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10000, 255));
					targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 10000, 200));
					player.sendMessage(ChatColor.DARK_GREEN + "[Police] " + ChatColor.GREEN + "You froze " + ChatColor.RED + args[1] + ChatColor.GREEN + " for 8 minutes.");
					player.sendMessage(ChatColor.GOLD + "[Police] [WARNING] " + ChatColor.DARK_RED + args[1] + ChatColor.GREEN + " can try to unfreeze at anytime by typing " + ChatColor.GOLD + "/cop resist");
					targetPlayer.sendMessage(ChatColor.DARK_GREEN + "[Police] " + ChatColor.GREEN + "You have just been frozen by " + ChatColor.GOLD + player + ChatColor.GREEN + ".");
					targetPlayer.sendMessage(ChatColor.DARK_GREEN + "[Police] " + ChatColor.GREEN + "You can try to resist this by typing " + ChatColor.GOLD + "/cop resist" + ChatColor.GREEN + ". The cop can freeze you again if you resist!");
				} else {
					
					player.sendMessage(ChatColor.DARK_GREEN + "[Police] " + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "Freeze failed. Player is not online, check spelling and try again.");
				}
			} else if (args.length == (2) && args[0].equalsIgnoreCase("unfreeze") && player.hasPermission("jm.police.freeze")){
				if (getPlayer(args[1]) != null){
					Player targetPlayer = getPlayer(args[1]);
					targetPlayer.removePotionEffect(PotionEffectType.BLINDNESS);
					targetPlayer.removePotionEffect(PotionEffectType.SLOW);
					targetPlayer.removePotionEffect(PotionEffectType.JUMP);
					targetPlayer.sendMessage(ChatColor.DARK_GREEN + "[Police] " + ChatColor.GREEN + "You have been unfrozen by the police");
					player.sendMessage(ChatColor.DARK_GREEN + "[Police] " + ChatColor.GREEN + "You unfroze " + ChatColor.RED + args[1]);
				}
			} else if (args.length == (1) && args[0].equalsIgnoreCase("resist") && player.hasPermission("jm.police.resist") && player.hasPotionEffect(PotionEffectType.BLINDNESS)){
				rand = new Random().nextDouble();
				resistOdds = rand;
				if (resistOdds >= .0 && resistOdds <= .1 && resistTries.get((player)) <= 3 && resistTries.get((player)) > 0) {
					player.removePotionEffect(PotionEffectType.BLINDNESS);
					player.removePotionEffect(PotionEffectType.SLOW);
					player.removePotionEffect(PotionEffectType.JUMP);
					player.sendMessage(ChatColor.DARK_GREEN + "[Police] " + ChatColor.GREEN + "You have resisted the freeze, the police may freeze you again.");
				}
				else if (resistTries.get(player) <= 0) {
					player.sendMessage(ChatColor.DARK_GREEN + "[Police] " + ChatColor.GREEN + "You are out of resist tries");
				}
				else {
					resistTries.put((player), (resistTries.get((player)) - 1));
					player.sendMessage(ChatColor.DARK_GREEN + "[Police] " + ChatColor.GREEN + "Resist failed you have " + (resistTries.get(player)) + " tries left");
				}
			} else if (args.length == (1) && args[0].equalsIgnoreCase("resist") && player.hasPermission("jm.police.resist") && !player.hasPotionEffect(PotionEffectType.BLINDNESS)){
				
				player.sendMessage(ChatColor.DARK_GREEN + "[jazzandmax] " + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "Resist failed. You were not frozen or it has expired.");
			
			} else if (args.length == (1) && args[0].equalsIgnoreCase("resist") && !player.hasPermission("jm.police.resist")){
				
				player.sendMessage(ChatColor.DARK_GREEN + "[jazzandmax] "+ ChatColor.DARK_RED + "Error:  " + ChatColor.RED + "Insufficient permissions.");
				
			} else if (args.length == (2) || args.length == (1) && args[0].equalsIgnoreCase("arrest") && player.hasPermission("jm.police.arrest")){
				if (getConfig().getBoolean("setup") == false){
					player.sendMessage(ChatColor.DARK_GREEN + "[jazzandmax] " + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "The config.yml is not setup properly for this feature.");
					
				} else if (getConfig().getBoolean("setup") == false && player.isOp()){
					player.sendMessage(ChatColor.DARK_GREEN + "[jazzandmax] " + ChatColor.RED + "To correct this set the XYZ of the jail location in the config and reload the server.");
				} else if (args.length == (1)){
					
					player.sendMessage(ChatColor.DARK_GREEN + "[jazzandmax] " + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "Insufficient arguments. Please select a player to arrest.");
				} else {
					
					Player targetPlayer = getPlayer(args[1]);
					targetPlayer.setGameMode(GameMode.ADVENTURE);
					World map = player.getWorld();
					double xaxis = getConfig().getDouble("X");
					double yaxis = getConfig().getDouble("Y");
					double zaxis = getConfig().getDouble("Z");
					Location jail = new Location(map, xaxis, yaxis, zaxis);
					targetPlayer.teleport(jail);
					player.sendMessage(ChatColor.DARK_GREEN + "[Police] " + ChatColor.GREEN + "You sent " + ChatColor.RED + args[1] + ChatColor.GREEN +  " to jail.");
					targetPlayer.sendMessage(ChatColor.DARK_GREEN + "[Police] " + ChatColor.GOLD + "You were sent to jail by " + ChatColor.RED + player + ChatColor.GOLD + ".");
				}
				
			} else if (args.length == (1) && args[0].equalsIgnoreCase("setjail")){
				Location newjail = player.getLocation();
				getConfig().set("X", newjail.getBlockX());
				getConfig().set("Y", newjail.getBlockY());
				getConfig().set("Z", newjail.getBlockZ());
				player.sendMessage(ChatColor.DARK_GREEN + "[Police] " + ChatColor.GREEN + "New jail location set.");
				player.sendMessage(ChatColor.DARK_GREEN + "[X] " + ChatColor.GREEN + newjail.getBlockX());
				player.sendMessage(ChatColor.DARK_GREEN + "[Y] " + ChatColor.GREEN + newjail.getBlockY());
				player.sendMessage(ChatColor.DARK_GREEN + "[Z] " + ChatColor.GREEN + newjail.getBlockZ());
				
			
			} else if (args.length == (2) || args.length == (1) && args[0].equalsIgnoreCase("arrest") && !player.hasPermission("jm.police.arrest")){
				
				player.sendMessage(ChatColor.DARK_GREEN + "[jazzandmax] " + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "Insufficient permissions.");
			} else if (args.length != (0) || args.length != (1) || args.length != (2)) {
				player.sendMessage(ChatColor.DARK_GREEN + "[jazzandmax] " + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "Too many arguments.");
			} 
		}
		return false;
	}
}