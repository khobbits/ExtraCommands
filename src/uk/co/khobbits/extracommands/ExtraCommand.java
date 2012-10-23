package uk.co.khobbits.extracommands;

import com.earth2me.essentials.IEssentials;
import com.earth2me.essentials.User;
import java.util.List;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class ExtraCommand {

    private transient IEssentials ess;
    private transient ExtraCommands plugin;

    public void run(final Server server, final Player player, final Command cmd, final String commandLabel, final String[] args) throws CommandException {
        run(server, player, cmd, commandLabel, args);
    }

    public void run(final Server server, final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) throws CommandException {
        throw new CommandException("This command is for players only");
    }

    public static String getFinalArg(final String[] args, final int start) {
        final StringBuilder bldr = new StringBuilder();
        for (int i = start; i < args.length; i++) {
            if (i != start) {
                bldr.append(" ");
            }
            bldr.append(args[i]);
        }
        return bldr.toString();
    }

    protected User getPlayer(final String arg, final boolean getOffline) throws CommandException {

        final Server server = ess.getServer();

        if (arg.isEmpty()) {
            throw new CommandException("Player not found");
        }

        final User user = ess.getUser(arg);

        if (user != null) {
            if (!getOffline && (!user.isOnline() || user.isHidden())) {
                throw new CommandException("Player not found");
            }
            return user;
        }

        final List<Player> matches = server.matchPlayer(arg);

        if (!matches.isEmpty()) {

            for (Player player : matches) {
                final User userMatch = ess.getUser(player);
                if (userMatch.getDisplayName().startsWith(arg) && (getOffline || !userMatch.isHidden())) {
                    return userMatch;
                }
            }

            final User userMatch = ess.getUser(matches.get(0));

            if (getOffline || !userMatch.isHidden()) {
                return userMatch;
            }
        }
        throw new CommandException("Player not found");
    }

    public IEssentials getEss() {
        return ess;
    }

    public void setEss(IEssentials ess) {
        this.ess = ess;
    }

    public ExtraCommands getPlugin() {
        return plugin;
    }

    public void setPlugin(ExtraCommands plugin) {
        this.plugin = plugin;
    }
}
