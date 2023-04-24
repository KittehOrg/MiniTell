package org.kitteh.minitell;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public final class MiniTell extends JavaPlugin implements CommandExecutor {
    @Override
    public void onEnable() {
        this.getCommand("minitell").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length < 2) {
            return false;
        }
        if (args[0].equalsIgnoreCase("@a")) {
            Component message = this.getMini(args);
            this.getServer().getOnlinePlayers().forEach(p -> p.sendMessage(message));
            return true;
        }
        Player target = this.getServer().getPlayer(args[0]);
        if (target == null) {
            return false;
        }
        target.sendMessage(this.getMini(args));
        return true;
    }

    public @NotNull Component getMini(@NotNull String[] args) {
        return MiniMessage.miniMessage().deserialize(String.join(" ", Arrays.copyOfRange(args, 1, args.length)));
    }
}
