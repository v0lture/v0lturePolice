package me.jazzandmax.alerts;

import java.util.logging.Logger;

import me.confuser.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class alerts extends JavaPlugin{

	public final Logger logger = Logger.getLogger("Minecraft");
	public static alerts plugin;
	
	
	
	// Shutdown and boot actions
	
	
	@Override
	public void onDisable(){
		PluginDescriptionFile pdfile = this.getDescription();
		this.logger.info(pdfile.getName() + " has shutdown.");
	}
	
	@Override
	public void onEnable(){
		PluginDescriptionFile pdfile = this.getDescription();
		this.logger.info(pdfile.getName() + " has booted.");
		// generate config if one doesn't exist
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
		
		// Handle command "/alerts"
		public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
			Player player = (Player) sender;
			// Define command
			if(commandLabel.equalsIgnoreCase("alert")){
				// add player to cooldown
				if(Cooldowns.tryCooldown(player, "alert", 15000)){
					BarAPI.removeBar(player);
					BarAPI.setMessage(player, ChatColor.RED + "[Cooldown]" + ChatColor.GOLD + " /alert", 15);
					// just /alert
					if(args.length == (0)){
						player.sendMessage(ChatColor.DARK_GREEN + "[Alerts] " + ChatColor.GREEN + "For help type " + ChatColor.GOLD + "/jmps alert");
					// /alert help
					} else if (args.length == (1) && args[0].equalsIgnoreCase("help")){
						player.sendMessage(ChatColor.DARK_GREEN + "[Alerts] " + ChatColor.GREEN + "Command usage: " + ChatColor.GOLD + "/alert [admin/<police department name>]");
					// /alert admin
					} else if (args.length == (1) && args[0].equalsIgnoreCase("admin")){
						player.sendMessage(ChatColor.DARK_GREEN + "[Alerts] " + ChatColor.GREEN + "You have alerted department " + ChatColor.GOLD + "admin");
						Bukkit.broadcast(ChatColor.DARK_PURPLE + "[Alerts] " + ChatColor.DARK_GREEN + player + ChatColor.GREEN + " has requested your assistance.", "jm.alerts.admin");
					// /alert 1 (or dprt1)
					} else if (args.length == (1) && args[0].equalsIgnoreCase("1")){
						if(getConfig().getString("dprt1").equals(null)){
							player.sendMessage(ChatColor.DARK_GREEN + "[JMPS] " + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "The department you request is misconfigured in the config.yml.");
						} else {
							player.sendMessage(ChatColor.DARK_GREEN + "[Alerts] " + ChatColor.GREEN + "You have alerted department " + ChatColor.GOLD + getConfig().getString("dprt1"));
							Bukkit.broadcast(ChatColor.DARK_PURPLE + "[Alerts] " + ChatColor.DARK_GREEN + player + ChatColor.GREEN + " has alerted your police department " + ChatColor.GOLD + getConfig().getString("dprt1"), "jm.alerts.station");
						}
					// /alert 2 (or dprt2 name)
					} else if (args.length == (1) && args[0].equalsIgnoreCase("2")){
						if(getConfig().getString("dprt2").equals(null)){
							player.sendMessage(ChatColor.DARK_GREEN + "[JMPS] " + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "The department you request is misconfigured in the config.yml.");
						} else {
							player.sendMessage(ChatColor.DARK_GREEN + "[Alerts] " + ChatColor.GREEN + "You have alerted department " + ChatColor.GOLD + getConfig().getString("dprt1"));
							Bukkit.broadcast(ChatColor.DARK_PURPLE + "[Alerts] " + ChatColor.DARK_GREEN + player + ChatColor.GREEN + " has alerted your police department " + ChatColor.GOLD + getConfig().getString("dprt1"), "jm.alerts.1");
						}
					// /alert 3 (or dprt2 name)
					} else if (args.length == (1) && args[0].equalsIgnoreCase("3")){
						if(getConfig().getString("dprt3").equals(null)){
							player.sendMessage(ChatColor.DARK_GREEN + "[JMPS] " + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "The department you request is misconfigured in the config.yml.");
						} else {
							player.sendMessage(ChatColor.DARK_GREEN + "[Alerts] " + ChatColor.GREEN + "You have alerted department " + ChatColor.GOLD + getConfig().getString("dprt1"));
							Bukkit.broadcast(ChatColor.DARK_PURPLE + "[Alerts] " + ChatColor.DARK_GREEN + player + ChatColor.GREEN + " has alerted your police department " + ChatColor.GOLD + getConfig().getString("dprt1"), "jm.alerts.1");
						}
					// /alert 4 (or dprt2 name)
					} else if (args.length == (1) && args[0].equalsIgnoreCase("4")){
						if(getConfig().getString("dprt4").equals(null)){
							player.sendMessage(ChatColor.DARK_GREEN + "[JMPS] " + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "The department you request is misconfigured in the config.yml.");
						} else {
							player.sendMessage(ChatColor.DARK_GREEN + "[Alerts] " + ChatColor.GREEN + "You have alerted department " + ChatColor.GOLD + getConfig().getString("dprt1"));
							Bukkit.broadcast(ChatColor.DARK_PURPLE + "[Alerts] " + ChatColor.DARK_GREEN + player + ChatColor.GREEN + " has alerted your police department " + ChatColor.GOLD + getConfig().getString("dprt1"), "jm.alerts.1");
						}
					// /alert 5 (or dprt2 name)
					} else if (args.length == (1) && args[0].equalsIgnoreCase("5")){
						if(getConfig().getString("dprt5").equals(null)){
							player.sendMessage(ChatColor.DARK_GREEN + "[JMPS] " + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "The department you request is misconfigured in the config.yml.");
						} else {
							player.sendMessage(ChatColor.DARK_GREEN + "[Alerts] " + ChatColor.GREEN + "You have alerted department " + ChatColor.GOLD + getConfig().getString("dprt1"));
							Bukkit.broadcast(ChatColor.DARK_PURPLE + "[Alerts] " + ChatColor.DARK_GREEN + player + ChatColor.GREEN + " has alerted your police department " + ChatColor.GOLD + getConfig().getString("dprt1"), "jm.alerts.1");
						}
					// /alert 6 (or dprt2 name)
					} else if (args.length == (1) && args[0].equalsIgnoreCase("6")){
						if(getConfig().getString("dprt6").equals(null)){
							player.sendMessage(ChatColor.DARK_GREEN + "[JMPS] " + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "The department you request is misconfigured in the config.yml.");
						} else {
							player.sendMessage(ChatColor.DARK_GREEN + "[Alerts] " + ChatColor.GREEN + "You have alerted department " + ChatColor.GOLD + getConfig().getString("dprt1"));
							Bukkit.broadcast(ChatColor.DARK_PURPLE + "[Alerts] " + ChatColor.DARK_GREEN + player + ChatColor.GREEN + " has alerted your police department " + ChatColor.GOLD + getConfig().getString("dprt1"), "jm.alerts.1");
						}
					// /alert 7 (or dprt2 name)
					} else if (args.length == (1) && args[0].equalsIgnoreCase("7")){
						if(getConfig().getString("dprt7").equals(null)){
							player.sendMessage(ChatColor.DARK_GREEN + "[JMPS] " + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "The department you request is misconfigured in the config.yml.");
						} else {
							player.sendMessage(ChatColor.DARK_GREEN + "[Alerts] " + ChatColor.GREEN + "You have alerted department " + ChatColor.GOLD + getConfig().getString("dprt1"));
							Bukkit.broadcast(ChatColor.DARK_PURPLE + "[Alerts] " + ChatColor.DARK_GREEN + player + ChatColor.GREEN + " has alerted your police department " + ChatColor.GOLD + getConfig().getString("dprt1"), "jm.alerts.1");
						}
					// /alert 8 (or dprt2 name)
					} else if (args.length == (1) && args[0].equalsIgnoreCase("8")){
						if(getConfig().getString("dprt8").equals(null)){
							player.sendMessage(ChatColor.DARK_GREEN + "[JMPS] " + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "The department you request is misconfigured in the config.yml.");
						} else {
							player.sendMessage(ChatColor.DARK_GREEN + "[Alerts] " + ChatColor.GREEN + "You have alerted department " + ChatColor.GOLD + getConfig().getString("dprt1"));
							Bukkit.broadcast(ChatColor.DARK_PURPLE + "[Alerts] " + ChatColor.DARK_GREEN + player + ChatColor.GREEN + " has alerted your police department " + ChatColor.GOLD + getConfig().getString("dprt1"), "jm.alerts.1");
						}
					// /alert 9 (or dprt2 name)
					} else if (args.length == (1) && args[0].equalsIgnoreCase("9")){
						if(getConfig().getString("dprt9").equals(null)){
							player.sendMessage(ChatColor.DARK_GREEN + "[JMPS] " + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "The department you request is misconfigured in the config.yml.");
						} else {
							player.sendMessage(ChatColor.DARK_GREEN + "[Alerts] " + ChatColor.GREEN + "You have alerted department " + ChatColor.GOLD + getConfig().getString("dprt1"));
							Bukkit.broadcast(ChatColor.DARK_PURPLE + "[Alerts] " + ChatColor.DARK_GREEN + player + ChatColor.GREEN + " has alerted your police department " + ChatColor.GOLD + getConfig().getString("dprt1"), "jm.alerts.1");
						}
					// /alert 10 (or dprt2 name)
					} else if (args.length == (1) && args[0].equalsIgnoreCase("10")){
						if(getConfig().getString("dprt10").equals(null)){
							player.sendMessage(ChatColor.DARK_GREEN + "[JMPS] " + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "The department you request is misconfigured in the config.yml.");
						} else {
							player.sendMessage(ChatColor.DARK_GREEN + "[Alerts] " + ChatColor.GREEN + "You have alerted department " + ChatColor.GOLD + getConfig().getString("dprt1"));
							Bukkit.broadcast(ChatColor.DARK_PURPLE + "[Alerts] " + ChatColor.DARK_GREEN + player + ChatColor.GREEN + " has alerted your police department " + ChatColor.GOLD + getConfig().getString("dprt1"), "jm.alerts.1");
						}
					// /alert 11 (or dprt2 name)
					} else if (args.length == (1) && args[0].equalsIgnoreCase("11")){
						if(getConfig().getString("dprt11").equals(null)){
							player.sendMessage(ChatColor.DARK_GREEN + "[JMPS] " + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "The department you request is misconfigured in the config.yml.");
						} else {
							player.sendMessage(ChatColor.DARK_GREEN + "[Alerts] " + ChatColor.GREEN + "You have alerted department " + ChatColor.GOLD + getConfig().getString("dprt1"));
							Bukkit.broadcast(ChatColor.DARK_PURPLE + "[Alerts] " + ChatColor.DARK_GREEN + player + ChatColor.GREEN + " has alerted your police department " + ChatColor.GOLD + getConfig().getString("dprt1"), "jm.alerts.1");
						}
					// /alert 12 (or dprt2 name)
					} else if (args.length == (1) && args[0].equalsIgnoreCase("12")){
						if(getConfig().getString("dprt12").equals(null)){
							player.sendMessage(ChatColor.DARK_GREEN + "[JMPS] " + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "The department you request is misconfigured in the config.yml.");
						} else {
							player.sendMessage(ChatColor.DARK_GREEN + "[Alerts] " + ChatColor.GREEN + "You have alerted department " + ChatColor.GOLD + getConfig().getString("dprt1"));
							Bukkit.broadcast(ChatColor.DARK_PURPLE + "[Alerts] " + ChatColor.DARK_GREEN + player + ChatColor.GREEN + " has alerted your police department " + ChatColor.GOLD + getConfig().getString("dprt1"), "jm.alerts.1");
						}
					// /alert 13 (or dprt2 name)
					} else if (args.length == (1) && args[0].equalsIgnoreCase("13")){
						if(getConfig().getString("dprt13").equals(null)){
							player.sendMessage(ChatColor.DARK_GREEN + "[JMPS] " + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "The department you request is misconfigured in the config.yml.");
						} else {
							player.sendMessage(ChatColor.DARK_GREEN + "[Alerts] " + ChatColor.GREEN + "You have alerted department " + ChatColor.GOLD + getConfig().getString("dprt1"));
							Bukkit.broadcast(ChatColor.DARK_PURPLE + "[Alerts] " + ChatColor.DARK_GREEN + player + ChatColor.GREEN + " has alerted your police department " + ChatColor.GOLD + getConfig().getString("dprt1"), "jm.alerts.1");
						}
					// /alert 14 (or dprt2 name)
					} else if (args.length == (1) && args[0].equalsIgnoreCase("14")){
						if(getConfig().getString("dprt14").equals(null)){
							player.sendMessage(ChatColor.DARK_GREEN + "[JMPS] " + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "The department you request is misconfigured in the config.yml.");
						} else {
							player.sendMessage(ChatColor.DARK_GREEN + "[Alerts] " + ChatColor.GREEN + "You have alerted department " + ChatColor.GOLD + getConfig().getString("dprt1"));
							Bukkit.broadcast(ChatColor.DARK_PURPLE + "[Alerts] " + ChatColor.DARK_GREEN + player + ChatColor.GREEN + " has alerted your police department " + ChatColor.GOLD + getConfig().getString("dprt1"), "jm.alerts.1");
						}
					// /alert 15 (or dprt2 name)
					} else if (args.length == (1) && args[0].equalsIgnoreCase("15")){
						if(getConfig().getString("dprt15").equals(null)){
							player.sendMessage(ChatColor.DARK_GREEN + "[JMPS] " + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "The department you request is misconfigured in the config.yml.");
						} else {
							player.sendMessage(ChatColor.DARK_GREEN + "[Alerts] " + ChatColor.GREEN + "You have alerted department " + ChatColor.GOLD + getConfig().getString("dprt1"));
							Bukkit.broadcast(ChatColor.DARK_PURPLE + "[Alerts] " + ChatColor.DARK_GREEN + player + ChatColor.GREEN + " has alerted your police department " + ChatColor.GOLD + getConfig().getString("dprt1"), "jm.alerts.1");
						}
					}
				} else {
					BarAPI.removeBar(player);
					BarAPI.setMessage(player, ChatColor.DARK_RED + "[Cooldown] " + ChatColor.GREEN + "Cooldown is still active for " + ChatColor.GOLD + "/alert", 10);
					player.sendMessage(ChatColor.DARK_RED + "[Cooldown] " + ChatColor.RED + "You have to wait " + ChatColor.GOLD + (Cooldowns.getCooldown(player, "alert") / 1000) + "s " + ChatColor.RED + "before you can use " + ChatColor.GOLD + "/alert" + ChatColor.RED + " again.");
				}
				
				
				
			}	
		return false;
		}
}
