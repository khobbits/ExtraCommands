package uk.co.khobbits.extracommands.commands;

import com.earth2me.essentials.User;
import com.earth2me.essentials.utils.DateUtil;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import uk.co.khobbits.extracommands.CommandException;
import uk.co.khobbits.extracommands.ExtraCommand;

public class Commandmutestatus extends ExtraCommand {

    public Commandmutestatus() {
        super();
    }

    @Override
    public void run(Server server, CommandSender sender, Command cmd, String label, String[] args) throws CommandException {

        User mutedUser = getPlayer(args[0], true);

        if (mutedUser.getMuted()) {

            Long time = mutedUser.getMuteTimeout();
            String expires = (time == 0) ? "Never" : DateUtil.formatDateDiff(time);
            sender.sendMessage("Player: " + mutedUser.getName() + " Muted: yes Expires: " + time);

        } else {
            sender.sendMessage("Player: " + mutedUser.getName() + " Muted: No");
        }

    }
}
