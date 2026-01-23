package br.ifba.edu.inf011.strategy;

import br.ifba.edu.inf011.model.documentos.Documento;

public class TesteMatheus implements AutenticadorInterface {

	@Override
	public void autenticar(Documento documento) {
		String numero;
		numero = "THEUUUUUU-" + System.currentTimeMillis(); 
		documento.setNumero(numero);

	}

}
