package br.ifba.edu.inf011.ui;

import javax.swing.JOptionPane;

import br.ifba.edu.inf011.af.DocumentOperatorFactory;
import br.ifba.edu.inf011.command.*;
import br.ifba.edu.inf011.model.documentos.Privacidade;

public class MyGerenciadorDocumentoUI extends AbstractGerenciadorDocumentosUI{
	
	private CommandManager commandManager;
	
	public MyGerenciadorDocumentoUI(DocumentOperatorFactory factory) {
	    super(factory);
	    this.commandManager = new CommandManager();
	}

	protected JPanelOperacoes montarMenuOperacoes() {
		JPanelOperacoes comandos = new JPanelOperacoes();
		comandos.addOperacao("➕ Criar Publico", e -> this.criarDocumentoPublico());
		comandos.addOperacao("➕ Criar Privado", e -> this.criarDocumentoPrivado());
		comandos.addOperacao("💾 Salvar", e-> this.salvarConteudo());
		comandos.addOperacao("🔑 Proteger", e->this.protegerDocumento());
		comandos.addOperacao("✍️ Assinar", e->this.assinarDocumento());
		comandos.addOperacao("⏰ Urgente", e->this.tornarUrgente());
		comandos.addOperacao("⚡ Priorizar", e -> this.priorizarDocumento());
		comandos.addOperacao("✍️ Alterar e Assinar", e -> this.alterarEAssinar());
		

		comandos.addOperacao("↩️ Undo", e -> this.undo());
	    comandos.addOperacao("↪️ Redo", e -> this.redo());
	    comandos.addOperacao("✅ Consolidar", e -> this.consolidar());


		return comandos;
	 }
	
	protected void criarDocumentoPublico() {
		this.criarDocumento(Privacidade.PUBLICO);
	}
	
	protected void criarDocumentoPrivado() {
		this.criarDocumento(Privacidade.SIGILOSO);
	}
	
	protected void salvarConteudo() {
	    try {
	    	DocumentoCommand cmd =
	    		    new SalvarDocumentoCommand(
	    		        this.controller,
	    		        this.areaEdicao.getConteudo()
	    		    );


	        commandManager.executar(cmd);
	        this.refreshUI();

	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(this, "Erro ao Salvar: " + e.getMessage());
	    }
	}
	
	protected void protegerDocumento() {
	    try {
	    	DocumentoCommand cmd =
	    		    new ProtegerCommand(this.controller);

	        commandManager.executar(cmd);
	        this.atual = controller.getDocumentoAtual();
	        this.refreshUI();

	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(this, "Erro ao proteger: " + e.getMessage());
	    }
	}


	protected void assinarDocumento() {
	    try {
	    	DocumentoCommand cmd =
	    		    new AssinarCommand(this.controller);

	        commandManager.executar(cmd);
	        this.atual = controller.getDocumentoAtual();
	        this.refreshUI();

	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(this, "Erro ao assinar: " + e.getMessage());
	    }
	}
 
	
	protected void tornarUrgente() {
	    try {
	    	DocumentoCommand cmd =
	    		    new UrgenteCommand(this.controller);


	        commandManager.executar(cmd);
	        this.atual = controller.getDocumentoAtual();
	        this.refreshUI();

	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(this, "Erro ao tornar urgente: " + e.getMessage());
	    }
	}
	

	private void criarDocumento(Privacidade privacidade) {
        try {
            int tipoIndex = this.barraSuperior.getTipoSelecionadoIndice();
			DocumentoCommand cmd =
					new CriarDocumentoCommand(this.controller, tipoIndex, privacidade);

			commandManager.executar(cmd);
			this.atual = controller.getDocumentoAtual();

            if (this.atual != null)
				this.barraDocs.addDoc("[" + atual.getNumero() + "]");

			this.refreshUI();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
        }
    }	
	protected void undo() {
	    try {
	        commandManager.desfazer();
	        this.atual = controller.getDocumentoAtual();
	        this.refreshUI();
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(this, "Erro ao desfazer: " + e.getMessage());
	    }
	}

	protected void redo() {
	    try {
	        commandManager.refazer();
	        this.atual = controller.getDocumentoAtual();
	        this.refreshUI();
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(this, "Erro ao refazer: " + e.getMessage());
	    }
	}
	
	protected void priorizarDocumento() {
	    try {
	        DocumentoCommand cmd =
	            new PriorizarCommand(this.controller);

	        commandManager.executar(cmd);
	        this.atual = controller.getDocumentoAtual();
	        this.refreshUI();

	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(this, "Erro ao priorizar: " + e.getMessage());
	    }
	}

	protected void alterarEAssinar() {
	    try {
	        DocumentoCommand cmd =
	            new AlterarEAssinarCommand(
	                this.controller,
	                this.areaEdicao.getConteudo()
	            );

	        commandManager.executar(cmd);
	        this.atual = controller.getDocumentoAtual();
	        this.refreshUI();

	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(this, "Erro ao alterar e assinar: " + e.getMessage());
	    }
	}

	protected void consolidar() {
	    try {
	        DocumentoCommand cmd =
	            new ConsolidarCommand(this.commandManager);

	        commandManager.executar(cmd);

	        JOptionPane.showMessageDialog(this,
	            "Alterações consolidadas. Undo/Redo limpos.");

	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(this,
	            "Erro ao consolidar: " + e.getMessage());
	    }
	}

	

}
