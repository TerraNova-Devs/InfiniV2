package de.mcterranova.infini.rpg.test.commands;

import de.mcterranova.terranovaLib.commands.AbstractCommand;

import java.util.List;

public class TestCommand extends AbstractCommand {
    public TestCommand() {
        addPlaceholder("$A", () -> List.of("spawn", "save", "load"));
        addPlaceholder("$B", () -> List.of("[name]"));
        registerSubCommand(ItemCommand.class, "item");
        setupHelpCommand();
        initialize();
    }
}
