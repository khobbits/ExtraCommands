package uk.co.khobbits.extracommands;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHub implements CommandExecutor {
    
    private ExtraCommands plugin;
    
    public CommandHub(ExtraCommands plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Server server = plugin.getServer();
        ExtraCommand command;
        Player player = null;
        
        if (sender instanceof Player) {
            player = (Player) sender;
        }
        
        try {
            
            command = (ExtraCommand) CommandHub.class.getClassLoader().loadClass("uk.co.khobbits.extracommands.commands.Command" + cmd.getName().toLowerCase(Locale.ENGLISH)).newInstance();
            command.setEss(plugin.getEss());
            command.setPlugin(plugin);
            try {
                if (player == null) {
                    command.run(server, sender, cmd, label, args);
                } else {
                    command.run(server, player, cmd, label, args);
                }
            } catch (CommandException e) {
                if (e.getMessage() != null && e.getMessage().length() > 0) {
                    sender.sendMessage(e.getMessage());
                }                
            }
            
            return true;
            
        } catch (Exception ex) {
            Logger.getLogger(CommandHub.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
}
