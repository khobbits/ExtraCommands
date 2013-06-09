package uk.co.khobbits.extracommands.commands;

import com.earth2me.essentials.User;
import com.earth2me.essentials.utils.DateUtil;
import java.util.Set;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import uk.co.khobbits.extracommands.CommandException;
import uk.co.khobbits.extracommands.ExtraCommand;

public class Commandbanlist extends ExtraCommand {

    public Commandbanlist() {
        super();
    }

    @Override
    public void run(Server server, CommandSender sender, Command cmd, String label, String[] args) throws CommandException {
        Set<OfflinePlayer> banlist = server.getBannedPlayers();

        sender.sendMessage("Banned player list:");
        for (OfflinePlayer bannedplayer : banlist) {
            User banneduser = getEss().getUser(bannedplayer.getName());
            if (banneduser == null) {
                sender.sendMessage("Player: " + bannedplayer.getName() + " Reason: unknown");
                continue;
            }
            if (banneduser.getBanTimeout() != 0) {
                sender.sendMessage("Player: " + bannedplayer.getName() + " Expires: " + DateUtil.formatDateDiff(banneduser.getBanTimeout()) + " Reason: " + banneduser.getBanReason());
            } else {
                sender.sendMessage("Player: " + bannedplayer.getName() + " Reason: " + banneduser.getBanReason());
            }
        }
    }
}