package br.ifba.edu.inf011.command;

import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.documentos.Documento;

public class ProtegerCommand implements DocumentoCommand {
    private final GerenciadorDocumentoModel receiver;
    private Documento antes, depois;

    public ProtegerCommand(GerenciadorDocumentoModel receiver) { this.receiver = receiver; }

    public void execute() throws Exception {
        antes = receiver.getDocumentoAtual();
        depois = receiver.protegerDocumento(antes);
    }

    public void undo() throws Exception {
        receiver.atualizarRepositorio(depois, antes);
        receiver.setDocumentoAtual(antes);
    }
}
