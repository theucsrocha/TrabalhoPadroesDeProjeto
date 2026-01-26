package br.ifba.edu.inf011.command;

import br.ifba.edu.inf011.decorator.DocumentoDecorator;
import br.ifba.edu.inf011.memento.DocumentoMemento;
import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.documentos.Documento;

public class EditarConteudoCommand implements DocumentoCommand {

    private final GerenciadorDocumentoModel receiver;
    private final String novoConteudo;

    private Documento documentoBase;      // alvo real do conteúdo
    private DocumentoMemento memento;     // snapshot antes

    public EditarConteudoCommand(GerenciadorDocumentoModel receiver, String novoConteudo) {
        this.receiver = receiver;
        this.novoConteudo = novoConteudo;
    }

    @Override
    public void execute() throws Exception {
        Documento doc = receiver.getDocumentoAtual();

        // sempre editar o documento base (não o decorado)
        if (doc instanceof DocumentoDecorator) {
            doc = ((DocumentoDecorator) doc).getDocumentoBase();
        }

        this.documentoBase = doc;
        this.memento = new DocumentoMemento(documentoBase); // snapshot ANTES

        receiver.salvarDocumento(documentoBase, novoConteudo);
        receiver.setDocumentoAtual(documentoBase);
    }

    @Override
    public void undo() throws Exception {
        memento.restaurar(documentoBase);
        receiver.setDocumentoAtual(documentoBase);
    }
}
