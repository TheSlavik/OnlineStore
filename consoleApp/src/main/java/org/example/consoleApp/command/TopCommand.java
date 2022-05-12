package org.example.consoleApp.command;

import org.example.store.populator.Populator;

public class TopCommand implements Command {

    @Override
    public void execute(Populator populator) {
        populator.top();
    }
}
