package br.ifba.edu.inf011.command;

import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;

public class PriorizarCommand extends MacroCommandComMemento {

    public PriorizarCommand(GerenciadorDocumentoModel receiver) {
        super(receiver);
        this.add(new UrgenteCommand(receiver));
        this.add(new AssinarCommand(receiver));
    }
}
