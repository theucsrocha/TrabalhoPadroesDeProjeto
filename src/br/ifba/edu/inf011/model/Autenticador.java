package br.ifba.edu.inf011.model;

import java.time.LocalDate;

import br.ifba.edu.inf011.model.documentos.Documento;
import br.ifba.edu.inf011.model.documentos.Privacidade;
import br.ifba.edu.inf011.strategy.AutenticadorInterface;

public class Autenticador {
	
	private AutenticadorInterface strategy;
	
	public void autenticar( Documento documento) {
		strategy.autenticar(documento);
	}
	
	public void setStrategyAutenticacao(AutenticadorInterface strategy) {
		this.strategy = strategy;
	}

}
