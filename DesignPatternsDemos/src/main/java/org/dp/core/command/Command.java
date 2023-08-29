package org.dp.core.command;

public abstract class Command {
    public abstract void exec();
    public abstract void undo();
}
