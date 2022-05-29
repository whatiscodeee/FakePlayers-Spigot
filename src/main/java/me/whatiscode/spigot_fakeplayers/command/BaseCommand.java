package me.whatiscode.spigot_fakeplayers.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public abstract class BaseCommand implements CommandExecutor {

    private CommandInfo information;
    private int requiredArgs;

    public BaseCommand(CommandInfo information, int requiredArgs) {
        this.information = information;
        this.requiredArgs = requiredArgs;
    }

    public BaseCommand(CommandInfo information) {
        this(information, 0);
    }

    /**
     * Получаем информацию о команде
     * @return информация о команде
     */
    public CommandInfo getInformation() {
        return information;
    }

    /**
     * Получает необходимые аргументы, необходимые для выполнения команды
     *
     * @return Количество обязательных аргументов
     */
    public int getRequiredArgs() {
        return requiredArgs;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        CommandInfo info = this.getInformation();
        if (!this.hasPermission(sender, information.getPermission())) {
            sender.sendMessage(ChatColor.RED + "У вас недостаточно прав, чтобы выполнить данную команду");
            return true;
        }

        if (!(args.length > (requiredArgs - 1))) {
            sender.sendMessage(ChatColor.RED + "Используйте: " + information.getUsage());
            return true;
        }

        // Выполнить начальную команду
        perform(sender, alias, args);
        return true;
    }

    /**
     * Проверяем есть ли у игрока, который выполняет команду права
     *
     * @param sender отправитель
     * @param permission права
     * @return разрешаем отправку, если отправитель имеет право на выполнение
     */
    protected boolean hasPermission(CommandSender sender, String permission) {
        return permission.isEmpty() || !(sender instanceof Player) || sender.isOp() || sender.hasPermission(permission);
    }

    public abstract void perform(CommandSender sender, String alias, String[] args);
}
