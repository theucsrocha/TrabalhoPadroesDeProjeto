package br.ifba.edu.inf011.command;

import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.documentos.Documento;
import br.ifba.edu.inf011.model.documentos.Privacidade;

public class CriarDocumentoCommand implements DocumentoCommand {
    private final GerenciadorDocumentoModel receiver;
    private final int tipoIndex;
    private final Privacidade privacidade;

    private Documento antes;
    private Documento depois;

    public CriarDocumentoCommand(GerenciadorDocumentoModel receiver, int tipoIndex, Privacidade privacidade) {
        this.receiver = receiver;
        this.tipoIndex = tipoIndex;
        this.privacidade = privacidade;
    }
    @Override
    public void execute() throws Exception {
        this.antes = receiver.getDocumentoAtual();
        this.depois = receiver.criarDocumento(tipoIndex, privacidade);
    }

    @Override
    public void undo() throws Exception {
        // O sistema não deve excluir remover um documento criado, somente edição, assinatura ou proteção
    }
}
