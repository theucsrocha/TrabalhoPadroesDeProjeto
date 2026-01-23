package br.ifba.edu.inf011.strategy;

import java.util.HashMap;
import java.util.Map;

public class AutenticadorStrategyRegistry {
	 private static final Map<Integer, AutenticadorInterface> strategies = new HashMap<>();

	    static {
	        strategies.put(0, new AutenticadorCriminal());
	        strategies.put(1, new AutenticadorPessoal());
	        strategies.put(2, new AutenticadorExportacao());
	        strategies.put(3, new AutenticadorConfidencial());
	        strategies.put(4, new TesteMatheus());
	    }

	    public static AutenticadorInterface getStrategy(int tipo) {
	        return strategies.getOrDefault(tipo, new AutenticadorConfidencial());
	    }
	}

