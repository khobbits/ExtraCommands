package uk.co.khobbits.extracommands.commands;

import com.earth2me.essentials.IUser;
import com.earth2me.essentials.Warps;
import com.earth2me.essentials.api.IWarps;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import uk.co.khobbits.extracommands.CommandException;
import uk.co.khobbits.extracommands.ExtraCommand;

public class Commandwc extends ExtraCommand {

    public Commandwc() {
        super();
    }

    @Override
    public void run(Server server, Player player, Command cmd, String label, String[] args) throws CommandException {
        int loop;
        try {
            loop = player.getMetadata("warpCycle").get(0).asInt();
        } catch (Exception ex) {
            loop = 0;
        }

        IWarps warps = getEss().getWarps();

        if (warps.isEmpty()) {
            throw new CommandException("No warps defined");
        }

        final List<String> warpNameList = new ArrayList<String>(warps.getList());
        while (warpNameList.size() > loop) {

            final String warpName = warpNameList.get(loop);

            Location loc;
            try {
                loc = warps.getWarp(warpName);
                IUser user = getEss().getUser(player);
                user.sendMessage("Warping to warp No. " + loop + " called: " + warpName);
                user.getTeleport().teleport(loc, null);
                loop++;
                break;
            } catch (Exception ex) {
                player.sendMessage("Warp error: " + ex.getMessage());
                loop++;
            }            
        }
        if (warpNameList.size() <= loop) {
            loop = 0;
        }
        player.setMetadata("warpCycle", new FixedMetadataValue(getPlugin(), loop));
    }
}