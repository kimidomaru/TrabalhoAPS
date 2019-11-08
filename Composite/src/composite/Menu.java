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
		System.out.println("Digite o numero da opcao desejada:");
		System.out.println("1 - Criar um diagrama");
		System.out.println("2 - Abrir um diagrama");
		System.out.println("0 - Sair");
	}
	
	public void menuDiagrama() {
		System.out.println("Selecione a opção que desejar\n"+
				"1 - Voltar\n"+
				"2 - Criar uma Classe\n"+
				"3 - Criar uma Interface");
	}
	
	public void menuClasse() {
		System.out.println("Selecione a opção que desejar\n"+
				"1 - Voltar\n"+
				"2 - Inserir Atributo\n"+
				"3 - Inserir Método\n"+
				"4 - Inserir Relacionamento\n");
	}
	
	public void menuInterface() {
		System.out.println("Selecione a opção que desejar\n"+
				"1 - Voltar\n"+
				"2 - Inserir Atributo\n"+
				"3 - Inserir Método\n"+
				"4 - Inserir Relacionamento\n");
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
