package uk.co.khobbits.extracommands;

import java.util.logging.Logger;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import com.earth2me.essentials.IEssentials;

public class ExtraCommands extends JavaPlugin {

    private static final Logger LOGGER = Logger.getLogger("Minecraft");
    private transient IEssentials ess;

    @Override
    public void onEnable() {
        
        getCommand("wc").setExecutor(new CommandHub(this));

        final PluginManager pm = this.getServer().getPluginManager();
        final Plugin essPlugin = pm.getPlugin("Essentials");
        if (essPlugin == null) {
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
        return ess;
    }
}
