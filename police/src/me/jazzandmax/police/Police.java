package me.jazzandmax.police;

import java.util.logging.Logger;
import java.util.Random;

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
	int resistTries;
	
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
	public <dev> dev devDebug(String cmd, Player sender, Player target, String status){
		Bukkit.broadcast(ChatColor.DARK_RED + "[jazzandmax: DEBUG] " + ChatColor.GREEN + "Player "+ChatColor.GOLD+sender+ChatColor.GREEN+" used command "+ChatColor.GOLD+cmd+ChatColor.GREEN+" on "+ChatColor.GOLD+target+ChatColor.GREEN+" with status: "+ChatColor.GOLD+status, "jm.debug");
		return null;
		
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
				devDebug("COP", player, null, "INCOMPLETE");
				player.sendMessage(ChatColor.DARK_GREEN + "[Police] " + ChatColor.GREEN + "Unknown command.");
				 
			} else if (args.length == (2) && args[0].equalsIgnoreCase("teleport")){
				if(this.getServer().getPlayer(args[1])!=null){
					
					Player targetPlayer = this.getServer().getPlayer(args[1]);
					devDebug("COP TELEPORT", player, targetPlayer, "SUCCESS");
					Location targetlocation = targetPlayer.getLocation();
					player.teleport(targetlocation);
					targetPlayer.sendMessage(ChatColor.DARK_GREEN + "[Police] " + ChatColor.RED + player + ChatColor.GREEN + " just teleported to you.");
					player.sendMessage(ChatColor.DARK_GREEN + "[Police] " + ChatColor.GREEN + "You teleported to " + ChatColor.RED + args[1]);
				} else {
					devDebug("COP TELEPORT", player, null, "FAILED: N/A PLAYER");
					player.sendMessage(ChatColor.DARK_GREEN + "[Police] " + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "Teleport failed. Player is not online, check spelling and try again.");
				}
			} else if (args.length == (1) && args[0].equalsIgnoreCase("teleport") && player.hasPermission("jm.police.tp")){
				devDebug("COP TELEPORT", player, null, "FAILED: ARGS");
				player.sendMessage(ChatColor.DARK_GREEN + "[Police] " + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "Insufficient arguments. Please select a player to teleport to.");
			
			} else if (args.length == (1) && args[0].equalsIgnoreCase("teleport") && !player.hasPermission("jm.police.tp")){
				devDebug("COP TELEPORT", player, null, "FAILED: NO PERM");
				player.sendMessage(ChatColor.DARK_GREEN + "[jazzandmax] " + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "Insufficient permissions.");
			
			} else if (args.length == (1) && args[0].equalsIgnoreCase("freeze") && player.hasPermission("jm.police.freeze")){
				devDebug("COP FREEZE", player, null, "FAILED: ARGS");
				player.sendMessage(ChatColor.DARK_GREEN + "[Police] " + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "Insufficient arguments. Please select a player to freeze.");
				
			} else if (args.length == (1) && args[0].equalsIgnoreCase("freeze") && !player.hasPermission("jm.police.freeze")){
				devDebug("COP FREEZE", player, null, "FAILED: NO PERM");
				player.sendMessage(ChatColor.DARK_GREEN + "[jazzandmax] " + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "Insufficient permissions.");
				
			} else if (args.length == (2) && args[0].equalsIgnoreCase("freeze") && player.hasPermission("jm.police.freeze")){
				if (getPlayer(args[1]) != null){
					devDebug("COP FREEZE", player, null, "SUCCESS");
					Player targetPlayer = getPlayer(args[1]);
					resistTries = 3;
					player.sendMessage(ChatColor.DARK_GREEN + "[Police] " + ChatColor.GREEN + "Resist tries = " + resistTries);
					targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 10000, 255));
					targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10000, 255));
					player.sendMessage(ChatColor.DARK_GREEN + "[Police] " + ChatColor.GREEN + "You froze " + ChatColor.RED + args[1] + ChatColor.GREEN + " for 8 minutes.");
					player.sendMessage(ChatColor.GOLD + "[Police] [WARNING] " + ChatColor.DARK_RED + args[1] + ChatColor.GREEN + " can try to unfreeze at anytime by typing " + ChatColor.GOLD + "/cop resist");
					targetPlayer.sendMessage(ChatColor.DARK_GREEN + "[Police] " + ChatColor.GREEN + "You have just been frozen by " + ChatColor.GOLD + player + ChatColor.GREEN + ".");
					targetPlayer.sendMessage(ChatColor.DARK_GREEN + "[Police] " + ChatColor.GREEN + "You can try to resist this by typing " + ChatColor.GOLD + "/cop resist" + ChatColor.GREEN + ". The cop can freeze you again if you resist!");
				} else {
					devDebug("COP FREEZE", player, null, "FAILED: N/A PLAYER");
					player.sendMessage(ChatColor.DARK_GREEN + "[Police] " + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "Freeze failed. Player is not online, check spelling and try again.");
				}
			} else if (args.length == (1) && args[0].equalsIgnoreCase("resist") && player.hasPermission("jm.police.resist") && player.hasPotionEffect(PotionEffectType.BLINDNESS)){
				devDebug("COP RESIST", player, null, "SUCCESS");
				rand = new Random().nextDouble();
				resistOdds = rand;
				if (resistOdds >= .0 && resistOdds <= .1 && resistTries <= 3 && resistTries > 0) {
					player.removePotionEffect(PotionEffectType.BLINDNESS);
					player.removePotionEffect(PotionEffectType.SLOW);
					player.sendMessage(ChatColor.DARK_GREEN + "[Police] " + ChatColor.GREEN + "You have resisted the freeze, the police may freeze you again.");
				}
				else if (resistTries <= 0) {
					player.sendMessage(ChatColor.DARK_GREEN + "[Police] " + ChatColor.GREEN + "You are out of resist tries");
				}
				else {
					resistTries = (resistTries - 1);
					player.sendMessage(ChatColor.DARK_GREEN + "[Police] " + ChatColor.GREEN + "Resist failed you have " + (resistTries) + " tries left");
				}
			} else if (args.length == (1) && args[0].equalsIgnoreCase("resist") && player.hasPermission("jm.police.resist") && !player.hasPotionEffect(PotionEffectType.BLINDNESS)){
				devDebug("COP RESIST", player, null, "FAILED: INVALID");
				player.sendMessage(ChatColor.DARK_GREEN + "[jazzandmax] " + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "Resist failed. You were not frozen or it has expired.");
			
			} else if (args.length == (1) && args[0].equalsIgnoreCase("resist") && !player.hasPermission("jm.police.resist")){
				devDebug("COP RESIST", player, null, "FAILED: NO PERM");
				player.sendMessage(ChatColor.DARK_GREEN + "[jazzandmax] "+ ChatColor.DARK_RED + "Error:  " + ChatColor.RED + "Insufficient permissions.");
				
			} else if (args.length == (2) || args.length == (1) && args[0].equalsIgnoreCase("arrest") && player.hasPermission("jm.police.arrest")){
				if (getConfig().getBoolean("setup") == false){
					player.sendMessage(ChatColor.DARK_GREEN + "[jazzandmax] " + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "The config.yml is not setup properly for this feature.");
					devDebug("COP ARREST", player, null, "FAILED: CONFIG ERROR");
				} else if (getConfig().getBoolean("setup") == false && player.isOp()){
					player.sendMessage(ChatColor.DARK_GREEN + "[jazzandmax] " + ChatColor.RED + "To correct this set the XYZ of the jail location in the config and reload the server.");
				} else if (args.length == (1)){
					devDebug("COP ARREST", player, null, "FAILED: ARGS");
					player.sendMessage(ChatColor.DARK_GREEN + "[jazzandmax] " + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "Insufficient arguments. Please select a player to arrest.");
				} else {
					devDebug("COP ARREST", player, null, "SUCCESS");
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
			
			} else if (args.length == (2) || args.length == (1) && args[0].equalsIgnoreCase("arrest") && !player.hasPermission("jm.police.arrest")){
				devDebug("COP ARREST", player, null, "FAILED: NO PERM");
				player.sendMessage(ChatColor.DARK_GREEN + "[jazzandmax] " + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "Insufficient permissions.");
			} else if (args.length != (0) || args.length != (1) || args.length != (2)) {
				devDebug("COP", player, null, "FAILED: INVALID");
				player.sendMessage(ChatColor.DARK_GREEN + "[jazzandmax] " + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "Too many arguments.");
			} 
		}
		return false;
	}
}
