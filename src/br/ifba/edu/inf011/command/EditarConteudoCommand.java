package br.ifba.edu.inf011.command;

import br.ifba.edu.inf011.decorator.DocumentoDecorator;
import br.ifba.edu.inf011.memento.DocumentoMemento;
import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.documentos.Documento;

public class EditarConteudoCommand implements DocumentoCommand {

    private GerenciadorDocumentoModel receiver;
    private String novoConteudo;

    private Documento documento;
    private DocumentoMemento memento;

    public EditarConteudoCommand(GerenciadorDocumentoModel receiver, String novoConteudo) {
        this.receiver = receiver;
        this.novoConteudo = novoConteudo;
    }

    @Override
    public void execute() throws Exception {
        Documento doc = receiver.getDocumentoAtual();

        if (doc instanceof DocumentoDecorator) {
            doc = ((DocumentoDecorator) doc).getDocumentoBase();
        }

        receiver.salvarDocumento(doc, novoConteudo);
    }

    @Override
    public void undo() throws Exception {
        memento.restaurar(documento);
        receiver.setDocumentoAtual(documento);
    }
}
