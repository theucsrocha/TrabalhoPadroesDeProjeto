package br.ifba.edu.inf011.strategy;

import java.time.LocalDate;

import br.ifba.edu.inf011.model.documentos.Documento;

public class AutenticadorCriminal implements AutenticadorInterface {

	
	public void autenticar(Documento documento) {
		String numero;
		numero = "CRI-" + LocalDate.now().getYear() + "-" + documento.hashCode();
		documento.setNumero(numero);

	}

}
