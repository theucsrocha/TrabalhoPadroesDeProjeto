package br.ifba.edu.inf011.memento;

import br.ifba.edu.inf011.model.documentos.Documento;
import br.ifba.edu.inf011.model.documentos.Privacidade;
import br.ifba.edu.inf011.model.operador.Operador;

public class DocumentoMemento {

    private final String conteudo;
    private final String numero;
    private final Operador proprietario;
    private final Privacidade privacidade;

    public DocumentoMemento(Documento doc) throws Exception {
        this.conteudo = doc.getConteudo();
        this.numero = doc.getNumero();
        this.proprietario = doc.getProprietario();
        this.privacidade = doc.getPrivacidade();
    }

    public void restaurar(Documento doc) throws Exception {
        doc.setConteudo(conteudo);
        doc.setNumero(numero);
        doc.inicializar(proprietario, privacidade);
    }
}
