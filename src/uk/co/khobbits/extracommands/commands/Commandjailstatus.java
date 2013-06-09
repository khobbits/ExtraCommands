package uk.co.khobbits.extracommands.commands;

import com.earth2me.essentials.User;
import com.earth2me.essentials.utils.DateUtil;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import uk.co.khobbits.extracommands.CommandException;
import uk.co.khobbits.extracommands.ExtraCommand;

public class Commandjailstatus extends ExtraCommand {

    public Commandjailstatus() {
        super();
    }

    @Override
    public void run(Server server, CommandSender sender, Command cmd, String label, String[] args) throws CommandException {

        User jailedUser = getPlayer(args[0], true);

        if (jailedUser.isJailed()) {

            Long time = jailedUser.getJailTimeout();
            String expires = (time == 0) ? "Never" : DateUtil.formatDateDiff(time);
            sender.sendMessage("Player: " + jailedUser.getName() + " Jailed: yes Expires: " + time);

        } else {
            sender.sendMessage("Player: " + jailedUser.getName() + " Jailed: No");
        }

    }
}
