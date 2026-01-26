package br.ifba.edu.inf011.command;

public class ConsolidarCommand implements DocumentoCommand {

    private CommandManager manager;

    public ConsolidarCommand(CommandManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.consolidar();
    }

    @Override
    public void undo() {
        // Consolidação é irreversível
        // Nada a desfazer
    }
}
