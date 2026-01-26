package br.ifba.edu.inf011.command;

import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.documentos.Documento;

public class UrgenteCommand implements DocumentoCommand {

    private final GerenciadorDocumentoModel receiver;
    private Documento antes;
    private Documento depois;

    public UrgenteCommand(GerenciadorDocumentoModel receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() throws Exception {
        this.antes = receiver.getDocumentoAtual();
        this.depois = receiver.tornarUrgente(antes);
    }

    @Override
    public void undo() throws Exception {
        receiver.atualizarRepositorio(depois, antes);
        receiver.setDocumentoAtual(antes);
    }
}
