package composite;

import java.util.ArrayList;

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
				+ "3 - Inserir Metodo\n"
				+ "4 - Inserir Relacionamento");
		System.out.print("Digite o numero da opcao desejada: ");
	}
	
	public void menuAtributo(){
		//opcoes Aqui
		System.out.println("MENU DE ATRIBUTO");
	}
	
	public void menuMetodo(){
		//opcoes Aqui
		System.out.println("MENU DE METODO");
	}
	
	public void menuParametro(){
		//opcoes Aqui
	}
	
	public void menuRelacionamento(){
		//opcoes Aqui
	}
	public void ShowComponente(ComponenteComposto obj) {
		String nome = obj.getNome();
		String type= "";
		
		ArrayList filhos = (ArrayList) obj.getFilhos();
		ArrayList<String> atributos = new ArrayList();
		ArrayList<String> metodos = new ArrayList();
		
		if(obj instanceof Classe) {
			
			type = "Classe";
		}
		if(obj instanceof Interface) {
		
			type = "Interface";
		}
		String Header="|          "+ type+ " " +obj.getNome()+ "          |";
		
		int TamHeader = Header.length(); 
		String dashes = new String(new char[TamHeader-2]).replace("\0", "-");
		
		for(int i=0;i < filhos.size();i++) {
			
			String typeFilho = "";
			String Conteudo = "";
			
			if (filhos.get(i) instanceof Atributo) {
				
				Atributo davez =(Atributo) filhos.get(i); 
				//System.out.println("Atributo!");
				String ModificadorAcesso = "";	
				
				switch(davez.getModificadoresDeAcesso().toLowerCase()) {
					case "public":
						ModificadorAcesso="+";
					case "private":
						ModificadorAcesso="-";
					case "protected":
						ModificadorAcesso="#";
					case "default":
						ModificadorAcesso="~";
					default:
						ModificadorAcesso="~";
				}
				
				String preConteudo="| "+ ModificadorAcesso + davez.getNome()+ " : "+ davez.getTipo();
				int tamPreConteudo = preConteudo.length();
				String spaces = new String(new char[TamHeader-tamPreConteudo-1]).replace("\0", " ");
				Conteudo = preConteudo + spaces+"|";
						
				atributos.add(Conteudo);
			}
			if (filhos.get(i) instanceof Metodo) {
				Metodo davez =(Metodo) filhos.get(i);
				//System.out.println("Metodo!");
				String ModificadorAcesso = "";		
				
				switch(davez.getModificadoresDeAcesso().toLowerCase()) {
					case "public":
						ModificadorAcesso="+";
					case "private":
						ModificadorAcesso="-";
					case "protected":
						ModificadorAcesso="#";
					case "default":
						ModificadorAcesso="~";
					default:
						ModificadorAcesso="+";
				}
				
				String preConteudo="| "+ ModificadorAcesso + davez.getNome()+ "():"+ davez.getTipoRetorno();
				int tamPreConteudo = preConteudo.length();
				String spaces = new String(new char[TamHeader-tamPreConteudo-1]).replace("\0", " ");
				Conteudo = preConteudo + spaces+"|";
				
				metodos.add(Conteudo);
			}
		}
		
		System.out.println("\n+"+ dashes +"+"); // 25 -  // 2 +
 		System.out.println(Header);
 		System.out.println("+"+ dashes +"+");
 		for(int i=0;i<atributos.size();i++) {
 			System.out.println(atributos.get(i));
 		}
 		System.out.println("+"+ dashes +"+");
 		for(int i=0;i<metodos.size();i++) {
 			System.out.println(metodos.get(i));
 		}
 		System.out.println("+"+ dashes +"+");
	}
}
