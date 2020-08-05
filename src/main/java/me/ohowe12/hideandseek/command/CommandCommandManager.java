package me.ohowe12.hideandseek.command;

import me.ohowe12.hideandseek.Plugin;
import me.ohowe12.hideandseek.utils.Language;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public abstract class CommandCommandManager extends CommandManager implements CommandExecutor, TabCompleter {

    @Override
    public String getFullName() {
        return getName();
    }

    public CommandCommandManager(Plugin plugin, Language language) {
        super(plugin, language);
        setCommandExecutor();
    }

    public CommandCommandManager(Plugin plugin) {
        this(plugin, plugin.getLanguage());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (label.equalsIgnoreCase(getName())) {
            processCommand(sender, args);
            return true;
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return processTabComplete(sender, args);
    }

    private void setCommandExecutor() {
        Objects.requireNonNull(plugin.getCommand(getName())).setExecutor(this);
    }
}