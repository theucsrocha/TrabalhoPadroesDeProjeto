package br.ifba.edu.inf011.command;

import java.util.ArrayList;
import java.util.List;

import br.ifba.edu.inf011.memento.DocumentoMemento;
import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.documentos.Documento;

public class MacroCommandComMemento implements DocumentoCommand {

    protected final List<DocumentoCommand> comandos = new ArrayList<>();
    protected final GerenciadorDocumentoModel receiver;

    protected DocumentoMemento memento;
    protected Documento antes;   // referência antes
    protected Documento depois;  // referência depois (ao final)

    public MacroCommandComMemento(GerenciadorDocumentoModel receiver) {
        this.receiver = receiver;
    }

    protected void add(DocumentoCommand cmd) {
        comandos.add(cmd);
    }

    @Override
    public void execute() throws Exception {
        this.antes = receiver.getDocumentoAtual();
        this.memento = new DocumentoMemento(antes);

        for (DocumentoCommand cmd : comandos) {
            cmd.execute();
        }

        this.depois = receiver.getDocumentoAtual();
    }

    @Override
    public void undo() throws Exception {
        // volta a referência para 'antes'
        receiver.atualizarRepositorio(depois, antes);
        receiver.setDocumentoAtual(antes);

        // restaura o estado interno do documento 'antes'
        memento.restaurar(antes);
    }
}
