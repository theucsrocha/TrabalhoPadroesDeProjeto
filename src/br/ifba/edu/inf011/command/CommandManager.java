package br.ifba.edu.inf011.command;

import java.util.Stack;

public class CommandManager {

	private Stack<DocumentoCommand> undoStack = new Stack<>();
	private Stack<DocumentoCommand> redoStack = new Stack<>();

	public void executar(DocumentoCommand cmd) throws Exception {
		cmd.execute();
		undoStack.push(cmd);
		redoStack.clear();
	}

	public void desfazer() throws Exception {
		if (!undoStack.isEmpty()) {
			DocumentoCommand cmd = undoStack.pop();
			cmd.undo();
			redoStack.push(cmd);
		}
	}

	public void refazer() throws Exception {
		if (!redoStack.isEmpty()) {
			DocumentoCommand cmd = redoStack.pop();
			cmd.execute(); // <-- REDO
			undoStack.push(cmd);
		}
	}
	public void consolidar() {
        undoStack.clear();
        redoStack.clear();
    }
}
