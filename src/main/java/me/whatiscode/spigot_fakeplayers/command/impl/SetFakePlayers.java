package me.whatiscode.spigot_fakeplayers.command.impl;

import me.whatiscode.spigot_fakeplayers.FakeplayersPlugin;
import me.whatiscode.spigot_fakeplayers.command.BaseCommand;
import me.whatiscode.spigot_fakeplayers.command.CommandInfo;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class SetFakePlayers extends BaseCommand {

    private FakeplayersPlugin plugin;

    public SetFakePlayers(FakeplayersPlugin plugin) {
        super(new CommandInfo("setfakeplayers", "Установить количество фейк-игроков", "/setfakeplayers <количество>]"), 1);
        this.plugin = plugin;
    }

    @Override
    public void perform(CommandSender sender, String alias, String[] args) {
        // Количество для добавления
        final int online = Integer.parseInt(args[0]);
        if (online < 1) {
            // Очистить обратный вызов
            plugin.getFPacket().clearCallback();
            return;
        }

        // Устанавливаем обратный вызов для изменения онлайн-игроков
        plugin.getFPacket().setCallback(ping -> ping.setPlayersOnline(Bukkit.getOnlinePlayers().size() + online));
    }
}
