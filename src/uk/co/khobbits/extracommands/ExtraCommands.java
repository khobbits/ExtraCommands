package uk.co.khobbits.extracommands;

import java.util.logging.Logger;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import com.earth2me.essentials.IEssentials;
import java.util.List;
import java.util.logging.Level;
import org.bukkit.command.Command;
import org.bukkit.command.PluginCommandYamlParser;

public class ExtraCommands extends JavaPlugin {

    private static final Logger LOGGER = Logger.getLogger("Minecraft");
    private transient IEssentials ess = null;

    @Override
    public void onEnable() {

        final List<Command> commands = PluginCommandYamlParser.parse(this);
        for (Command command : commands) {
            getCommand(command.getName()).setExecutor(new CommandHub(this));
        }

        getServer().getScheduler().scheduleSyncDelayedTask(this,
                new Runnable() {

                    @Override
                    public void run() {
                        hookEss();
                    }
                });
    }

    public void hookEss() {
        final PluginManager pm = this.getServer().getPluginManager();
        final Plugin essPlugin = pm.getPlugin("Essentials");
        if (essPlugin == null || !essPlugin.isEnabled()) {
            this.setEnabled(false);
            LOGGER.warning("Couldn't hook ess.");
            return;
        }
        ess = (IEssentials) essPlugin;
    }

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public IEssentials getEss() {
        if (ess == null) {
            hookEss();
        }
        return ess;
    }
}
