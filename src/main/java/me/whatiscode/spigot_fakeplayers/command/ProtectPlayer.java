package me.whatiscode.spigot_fakeplayers.command;

import org.bukkit.entity.Player;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * Мне нечего было делать, поэтому я создал для этого отдельный класс
 *
 * Он отвечает за проверку выполнения команды (точно ли её выполнил игрок)
 */
public abstract class ProtectPlayer extends BaseCommand {

    private Player sender;

    public ProtectPlayer(CommandInfo info) {
        super(info);
    }

    public ProtectPlayer(CommandInfo info, int requiredArgs) {
        super(info, requiredArgs);
    }

    public Player getSender() {
        return sender;
    }

    @Override
    public void perform(CommandSender sender, String alias, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Ошибка, Вы не являетесь игроком!");
            return;
        }

        // Если это был игрок, то выполняем команду, либо выдаём ошибку при неправильных аргументах
        perform((Player) sender, alias, args);
    }

    public abstract void perform(Player sender, String alias, String[] args);

}
