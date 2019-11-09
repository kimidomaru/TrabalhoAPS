package composite;


public class Menu {
	public void mostrarMenu(String elemento) {
		
		if(elemento.equals("inicial")){
			menuInicial();
		}
		
		else if(elemento.equals("diagrama")){
			menuDiagrama();
		}
		
		else if(elemento.equals("classe")){
			menuClasse();
		}
		
		else if(elemento.equals("interface")){
			menuInterface();
		}
		
		else if(elemento.equals("atributo")){
			menuAtributo();
		}
		
		else if(elemento.equals("metodo")){
			menuMetodo();
		}
		
		else if(elemento.equals("parametro")){
			menuParametro();
		}
		
		else if(elemento.equals("relacionamento")){
			menuRelacionamento();
		}
	}
	
	public void menuInicial() {
		System.out.println("---Menu Inicial---\n"
				+ "1 - Criar um diagrama\n"
				+ "2 - Abrir um diagrama\n"
				+ "0 - Sair");
		System.out.print("Digite o numero da opcao desejada: ");
	}
	
	public void menuDiagrama() {
		System.out.println("---Menu de Diagrama---\n"
				+ "1 - Voltar\n"
				+ "2 - Criar uma Classe\n"
				+ "3 - Criar uma Interface\n"
				+ "4 - Salvar");
	}
	
	public void menuClasse() {
		System.out.println("---Menu de Classe---\n"
				+ "1 - Voltar\n"
				+ "2 - Inserir Atributo\n"
				+ "3 - Inserir Metodo\n"
				+ "4 - Inserir Relacionamento");
		System.out.print("Digite o numero da opcao desejada: ");
	}
	
	public void menuInterface() {
		System.out.println("---Menu de Interface---\n"
				+ "1 - Voltar\n"
				+ "2 - Inserir Atributo\n"
				+ "3 - Inserir M�todo\n"
				+ "4 - Inserir Relacionamento");
		System.out.print("Digite o numero da opcao desejada: ");
	}
	
	public void menuAtributo(){
		//opcoes Aqui
	}
	
	public void menuMetodo(){
		//opcoes Aqui
	}
	
	public void menuParametro(){
		//opcoes Aqui
	}
	
	public void menuRelacionamento(){
		//opcoes Aqui
	}
}
