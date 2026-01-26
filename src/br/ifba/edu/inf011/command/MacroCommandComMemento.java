package br.ifba.edu.inf011.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.ifba.edu.inf011.memento.DocumentoMemento;
import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;

public class MacroCommandComMemento implements DocumentoCommand {

    protected List<DocumentoCommand> comandos = new ArrayList<>();
    protected DocumentoMemento memento;
    protected GerenciadorDocumentoModel receiver;

    public MacroCommandComMemento(GerenciadorDocumentoModel receiver) {
        this.receiver = receiver;
    }

    protected void add(DocumentoCommand cmd) {
        comandos.add(cmd);
    }

    @Override
    public void execute() throws Exception {
        // 🔴 SNAPSHOT ANTES DA OPERAÇÃO
        memento = new DocumentoMemento(receiver.getDocumentoAtual());

        for (DocumentoCommand cmd : comandos) {
            cmd.execute();
        }
    }

    @Override
    public void undo() throws Exception {
        memento.restaurar(receiver.getDocumentoAtual());
        receiver.setDocumentoAtual(receiver.getDocumentoAtual());
    }
}
