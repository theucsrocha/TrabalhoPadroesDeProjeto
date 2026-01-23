package br.ifba.edu.inf011.strategy;

import br.ifba.edu.inf011.model.documentos.Documento;

public class AutenticadorConfidencial implements AutenticadorInterface {

	@Override
	public void autenticar(Documento documento) {
		String numero;
		numero = "DOC-" + System.currentTimeMillis(); 
		documento.setNumero(numero);
		

	}

}
