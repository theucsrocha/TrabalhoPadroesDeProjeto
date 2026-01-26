package br.ifba.edu.inf011.command;

import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;

public class AlterarEAssinarCommand extends MacroCommandComMemento {

    public AlterarEAssinarCommand(GerenciadorDocumentoModel receiver, String novoConteudo) {
        super(receiver);
        this.add(new EditarConteudoCommand(receiver, novoConteudo));
        this.add(new AssinarCommand(receiver));
    }
}
