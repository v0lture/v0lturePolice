package me.jazzandmax.alerts;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
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
}
