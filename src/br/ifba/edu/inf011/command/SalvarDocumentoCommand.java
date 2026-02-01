package br.ifba.edu.inf011.command;

import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.documentos.Documento;

public class SalvarDocumentoCommand implements DocumentoCommand {
    private final GerenciadorDocumentoModel receiver;
    private final String conteudo;

    private Documento antes;
    private Documento depois;

    public SalvarDocumentoCommand(GerenciadorDocumentoModel receiver, String conteudo) {
        this.receiver = receiver;
        this.conteudo = conteudo;
    }

    @Override
    public void execute() throws Exception {
        this.antes = receiver.getDocumentoAtual();
        this.depois = receiver.salvarDocumento(antes, conteudo);
    }

    @Override
    public void undo() throws Exception {
        receiver.atualizarRepositorio(depois, antes);
        receiver.setDocumentoAtual(antes);
    }
}
