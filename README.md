# Sistema de Gestão de Documentos – INF011

## 📌 Visão Geral

Este projeto foi desenvolvido como solução para a **Avaliação da disciplina INF011 – Padrões de Projeto**, do IFBA, ministrada pelo professor **Frederico Barboza**.


* **Strategy** (Questão I)
* **Command** (Questão II)
* **Memento** (Questão II)



---

## 🎯 Questão I – Refatoração do Autenticador (Strategy)

### 📌 Problema Identificado

Na versão original do sistema, a classe `Autenticador` utilizava uma grande estrutura condicional (`if/else`) para definir diferentes regras de geração do número de protocolo do documento.

Essa abordagem apresentava os seguintes problemas:

* Violação do **Princípio do Aberto/Fechado (OCP)**;
* Baixa extensibilidade;
* Forte acoplamento entre regras distintas;
* Dificuldade de manutenção.

---

### 🧠 Padrão Aplicado: Strategy

**Intenção (GoF):** Definir uma família de algoritmos, encapsulá-los e torná-los intercambiáveis, permitindo que o algoritmo varie independentemente dos clientes que o utilizam.

O padrão **Strategy** foi aplicado para encapsular cada regra de autenticação em uma classe própria, permitindo sua seleção dinâmica em tempo de execução.

---

### 📂 Classes Envolvidas e Papéis

| Classe                         | Papel no Padrão                  |
| ------------------------------ | -------------------------------- |
| `AutenticadorInterface`        | Strategy                         |
| `AutenticadorCriminal`         | ConcreteStrategy                 |
| `AutenticadorPessoal`          | ConcreteStrategy                 |
| `AutenticadorExportacao`       | ConcreteStrategy                 |
| `AutenticadorConfidencial`     | ConcreteStrategy                 |
| `Autenticador`                 | Context                          |
| `AutenticadorStrategyRegistry` | Mecanismo de seleção da Strategy |

---

### ⚙️ Funcionamento

1. A interface gráfica fornece um **índice** representando o tipo de autenticação escolhido;
2. O `AutenticadorStrategyRegistry` associa esse índice a uma Strategy concreta;
3. A classe `Autenticador` recebe a Strategy e delega a ela a geração do número do documento;
4. Novas regras podem ser adicionadas sem modificar o código existente.

---

### ✅ Benefícios Obtidos

* Eliminação completa de estruturas condicionais complexas;
* Código aderente ao OCP;
* Alta extensibilidade;
* Clareza semântica das regras de autenticação.

---

## 🎯 Questão II – Produtividade e Segurança (Command + Memento)

A Questão II introduz novos requisitos arquiteturais relacionados à segurança, rastreabilidade e produtividade do sistema.

---

## 🧠 Padrão Command

### 📌 Problema Identificado

As diversas interfaces do sistema (desktop, web, API) chamavam diretamente métodos como:

* `assinar()`
* `proteger()`
* `setConteudo()`

Essa abordagem dificultava:

* Auditoria das operações;
* Implementação de undo/redo;
* Criação de operações compostas;
* Centralização do controle de ações.

---

### 📌 Intenção (GoF)

Encapsular uma solicitação como um objeto, permitindo parametrizar clientes com diferentes requisições, registrar solicitações e suportar operações de **undo**.

---

### 📂 Classes Envolvidas e Papéis

| Classe                      | Papel no Padrão |
| --------------------------- | --------------- |
| `DocumentoCommand`          | Command         |
| `EditarConteudoCommand`     | ConcreteCommand |
| `AssinarCommand`            | ConcreteCommand |
| `ProtegerCommand`           | ConcreteCommand |
| `UrgenteCommand`            | ConcreteCommand |
| `CommandManager`            | Invoker         |
| `GerenciadorDocumentoModel` | Receiver        |

---

### ⚙️ Funcionamento

* A interface gráfica apenas **instancia Commands**;
* O `CommandManager` executa os comandos e os armazena em histórico;
* Nenhuma operação é executada diretamente pela UI;
* O sistema passa a ter um ponto único de controle das ações.

---

## 🧠 Padrão Memento

### 📌 Problema Identificado

O requisito de **reversibilidade universal** exige que qualquer operação — inclusive operações compostas — possa ser desfeita, restaurando o estado anterior do documento.

---

### 📌 Intenção (GoF)

Capturar e externalizar o estado interno de um objeto sem violar seu encapsulamento, permitindo que esse estado seja restaurado posteriormente.

---

### 📂 Classes Envolvidas e Papéis

| Classe                   | Papel no Padrão |
| ------------------------ | --------------- |
| `DocumentoMemento`       | Memento         |
| `Documento`              | Originator      |
| `MacroCommandComMemento` | Caretaker       |

---

### ⚙️ Funcionamento

* Antes da execução de uma **Macro**, o estado do documento é capturado em um `DocumentoMemento`;
* Todas as operações da Macro são executadas;
* Em caso de undo, o Memento restaura completamente o estado anterior;
* A reversão ocorre de forma **atômica**, independentemente do número de operações internas.

---

## 🔄 Undo, Redo e Consolidar

* **Undo:** desfaz a última operação executada;
* **Redo:** refaz uma operação desfeita;
* **Consolidar:** limpa os históricos de undo/redo, confirmando permanentemente as alterações.

Essas funcionalidades são centralizadas no `CommandManager`, conforme exigido pelo enunciado.

---


