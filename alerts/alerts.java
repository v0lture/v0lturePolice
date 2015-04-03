package me.jazzandmax.alerts;

import java.util.logging.Logger;

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
		// Testing if departments are defined correctly
		if(getConfig().getString("dprt1").equals("")){
			this.logger.warning(ChatColor.RED + "[Alerts] Department 1 is undefined");
		}
		if(getConfig().getString("dprt2").equals("")){
			this.logger.warning(ChatColor.RED + "[Alerts] Department 2 is undefined");
		}
		if(getConfig().getString("dprt3").equals("")){
			this.logger.warning(ChatColor.RED + "[Alerts] Department 3 is undefined");
		}
		if(getConfig().getString("dprt4").equals("")){
			this.logger.warning(ChatColor.RED + "[Alerts] Department 4 is undefined");
		}
		if(getConfig().getString("dprt5").equals("")){
			this.logger.warning(ChatColor.RED + "[Alerts] Department 5 is undefined");
		}
		if(getConfig().getString("dprt6").equals("")){
			this.logger.warning(ChatColor.RED + "[Alerts] Department 6 is undefined");
		}
		if(getConfig().getString("dprt7").equals("")){
			this.logger.warning(ChatColor.RED + "[Alerts] Department 7 is undefined");
		}
		if(getConfig().getString("dprt8").equals("")){
			this.logger.warning(ChatColor.RED + "[Alerts] Department 8 is undefined");
		}
		if(getConfig().getString("dprt9").equals("")){
			this.logger.warning(ChatColor.RED + "[Alerts] Department 9 is undefined");
		}
		if(getConfig().getString("dprt10").equals("")){
			this.logger.warning(ChatColor.RED + "[Alerts] Department 10 is undefined");
		}
		if(getConfig().getString("dprt11").equals("")){
			this.logger.warning(ChatColor.RED + "[Alerts] Department 11 is undefined");
		}
		if(getConfig().getString("dprt12").equals("")){
			this.logger.warning(ChatColor.RED + "[Alerts] Department 12 is undefined");
		}
		if(getConfig().getString("dprt13").equals("")){
			this.logger.warning(ChatColor.RED + "[Alerts] Department 13 is undefined");
		}
		if(getConfig().getString("dprt14").equals("")){
			this.logger.warning(ChatColor.RED + "[Alerts] Department 14 is undefined");
		}
		if(getConfig().getString("dprt16").equals("")){
			this.logger.warning(ChatColor.RED + "[Alerts] Department 15 is undefined");
		}
	}
		
		// Handle command "/alerts"
		public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
			Player player = (Player) sender;
			// Define command
			if(commandLabel.equalsIgnoreCase("alert")){
				// just /alert
				if(args.length == (0)){
					player.sendMessage(ChatColor.DARK_GREEN + "[Alerts] " + ChatColor.GREEN + "For help type " + ChatColor.GOLD + "/alert help");
				// /alert help
				} else if (args.length == (1) && args[0].equalsIgnoreCase("help")){
					
				// /alert admin
				} else if (args.length == (1) && args[0].equalsIgnoreCase("admin")){
					
				// /alert 1 (or dprt1)
				} else if (args.length == (1) && args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase(getConfig().getString("dprt1"))){
					
				// /alert 2 (or dprt2 name)
				} else if (args.length == (1) && args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase(getConfig().getString("dprt2"))){
					
				// /alert 3 (or dprt2 name)
				} else if (args.length == (1) && args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase(getConfig().getString("dprt3"))){
					
				// /alert 4 (or dprt2 name)
				} else if (args.length == (1) && args[0].equalsIgnoreCase("4") || args[0].equalsIgnoreCase(getConfig().getString("dprt4"))){
					
				// /alert 5 (or dprt2 name)
				} else if (args.length == (1) && args[0].equalsIgnoreCase("5") || args[0].equalsIgnoreCase(getConfig().getString("dprt5"))){
					
				// /alert 6 (or dprt2 name)
				} else if (args.length == (1) && args[0].equalsIgnoreCase("6") || args[0].equalsIgnoreCase(getConfig().getString("dprt6"))){
					
				// /alert 7 (or dprt2 name)
				} else if (args.length == (1) && args[0].equalsIgnoreCase("7") || args[0].equalsIgnoreCase(getConfig().getString("dprt7"))){
					
				// /alert 8 (or dprt2 name)
				} else if (args.length == (1) && args[0].equalsIgnoreCase("8") || args[0].equalsIgnoreCase(getConfig().getString("dprt8"))){
					
				// /alert 9 (or dprt2 name)
				} else if (args.length == (1) && args[0].equalsIgnoreCase("9") || args[0].equalsIgnoreCase(getConfig().getString("dprt9"))){
					
				// /alert 10 (or dprt2 name)
				} else if (args.length == (1) && args[0].equalsIgnoreCase("10") || args[0].equalsIgnoreCase(getConfig().getString("dprt10"))){
					
				// /alert 11 (or dprt2 name)
				} else if (args.length == (1) && args[0].equalsIgnoreCase("11") || args[0].equalsIgnoreCase(getConfig().getString("dprt11"))){
					
				// /alert 12 (or dprt2 name)
				} else if (args.length == (1) && args[0].equalsIgnoreCase("12") || args[0].equalsIgnoreCase(getConfig().getString("dprt12"))){
					
				// /alert 13 (or dprt2 name)
				} else if (args.length == (1) && args[0].equalsIgnoreCase("13") || args[0].equalsIgnoreCase(getConfig().getString("dprt13"))){
					
				// /alert 14 (or dprt2 name)
				} else if (args.length == (1) && args[0].equalsIgnoreCase("14") || args[0].equalsIgnoreCase(getConfig().getString("dprt14"))){
					
				// /alert 15 (or dprt2 name)
				} else if (args.length == (1) && args[0].equalsIgnoreCase("15") || args[0].equalsIgnoreCase(getConfig().getString("dprt15"))){
					
				}
				
				
			}
			return false;
		
	}
}
