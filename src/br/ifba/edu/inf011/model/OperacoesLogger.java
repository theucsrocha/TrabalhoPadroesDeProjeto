package br.ifba.edu.inf011.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class OperacoesLogger {
    private final Path arquivolog = Path.of(System.getProperty("user.dir"), "operacoes.log");

    public OperacoesLogger() {
        inicializarLog();
    }

    private void inicializarLog() {
        try {
            Files.writeString(
                    arquivolog,
                    "",
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING
            );
        } catch (IOException e) {
            throw new RuntimeException("Erro ao inicializar arquivo de log", e);
        }
    }

    public void log(String mensagem) {
        try {
            Files.writeString(
                    arquivolog,
                    mensagem + System.lineSeparator(),
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            throw new RuntimeException("Erro ao escrever no arquivo", e);
        }
    }
}
