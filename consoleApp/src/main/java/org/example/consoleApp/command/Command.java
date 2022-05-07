package org.example.consoleApp.command;

import org.example.store.populator.Populator;

public interface Command {

    void execute(Populator populator);
}
