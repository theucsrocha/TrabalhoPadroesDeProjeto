package br.ifba.edu.inf011.command;

import br.ifba.edu.inf011.model.OperacoesLogger;

import java.util.Stack;

public class CommandManager {

	private Stack<DocumentoCommand> undoStack = new Stack<>();
	private Stack<DocumentoCommand> redoStack = new Stack<>();

	private final OperacoesLogger logger = new OperacoesLogger();

	public void executar(DocumentoCommand cmd) throws Exception {
		cmd.execute();
		undoStack.push(cmd);
		redoStack.clear();

		logger.log(cmd.toString());
	}

	public void desfazer() throws Exception {
		if (!undoStack.isEmpty()) {
			DocumentoCommand cmd = undoStack.pop();
			cmd.undo();
			redoStack.push(cmd);

			logger.log("Desfeito " + cmd);
		}
	}

	public void refazer() throws Exception {
		if (!redoStack.isEmpty()) {
			DocumentoCommand cmd = redoStack.pop();
			cmd.execute(); // <-- REDO
			undoStack.push(cmd);

			logger.log("refeito " + cmd);
		}
	}
	public void consolidar() {
        undoStack.clear();
        redoStack.clear();
    }
}
