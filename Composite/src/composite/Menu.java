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
		
		else if(elemento.equals("atributoTipo")){
			menuTipoAtributo();
		}
		
		else if(elemento.equals("atributoModificador")){
			menuModificadorAtributo();
		}
		
		else if(elemento.equals("metodoTipoRetorno")){
			menuMetodoTipoRetorno();
		}
		
		else if(elemento.equals("metodoModificadorClasse")){
			menuModificadorMetodoClasse();
		}
		
		else if(elemento.equals("metodoModificadorInterface")){
			menuModificadorMetodoInterface();
		}
		
		else if(elemento.equals("adicionarParametro")){
			menuAdicionarParametro();
		}
		
		else if(elemento.equals("tipoParametro")){
			menuTipoParametro();
		}
		
		/*else if(elemento.equals("relacionamento")){
			menuRelacionamento();
		}*/
	}
	
	public void menuInicial() {
		System.out.println("\n---Menu Inicial---\n"
				+ "1 - Criar um diagrama\n"
				+ "2 - Abrir um diagrama\n"
				+ "0 - Sair");
		System.out.print("Digite o numero da opcao desejada: ");
	}
	
	public void menuDiagrama() {
		System.out.println("\n---Menu de Diagrama---\n"
				+ "1 - Voltar\n"
				+ "2 - Criar uma Classe\n"
				+ "3 - Criar uma Interface\n"
				+ "4 - Salvar Diagrama\n"
				+ "5 - Ctrl + Z");
	}
	
	public void menuClasse() {
		System.out.println("\n---Menu de Classe---\n"
				+ "1 - Voltar\n"
				+ "2 - Inserir Atributo\n"
				+ "3 - Inserir Metodo\n"
				+ "4 - Inserir Relacionamento\n"
				+ "5 - Ctrl + Z");
	}
	
	public void menuInterface() {
		System.out.println("\n---Menu de Interface---\n"
				+ "1 - Voltar\n"
				+ "2 - Inserir Atributo\n"
				+ "3 - Inserir Metodo\n"
				+ "4 - Inserir Relacionamento\n"
				+ "5 - Ctrl + Z");
	}
	
	public void menuTipoAtributo(){
		System.out.println("\n---Menu do Tipo do Atributo---\n"
				+ "1 - byte\n"
				+ "2 - short\n"
				+ "3 - int\n"
				+ "4 - long\n"
				+ "5 - float\n"
				+ "6 - double\n"
				+ "7 - boolean\n"
				+ "8 - char\n"
				+ "9 - String");
		System.out.print("Digite o numero da opcao desejada: ");
	}
	
	public void menuModificadorAtributo(){
		System.out.println("\n---Menu de Modificador de Acesso de Atributo---\n"
				+ "1 - Default\n"
				+ "2 - Private\n"
				+ "3 - Public\n"
				+ "4 - Protected");
		System.out.print("Digite o numero da opcao desejada: ");
	}
	
	
	public void menuModificadorMetodoClasse(){
		System.out.println("\n---Menu de Modificador de Acesso de Metodo---\n"
				+ "1 - Default\n"
				+ "2 - Private\n"
				+ "3 - Public\n"
				+ "4 - Protected");
		System.out.print("Digite o numero da opcao desejada: ");
	}
	
	public void menuModificadorMetodoInterface(){
		System.out.println("\n---Menu de Modificador de Acesso de Metodo---\n"
				+ "1 - Default\n"
				+ "2 - Public");
		System.out.print("Digite o numero da opcao desejada: ");
	}
	
	public void menuMetodoTipoRetorno(){
		System.out.println("\n---Menu do Tipo do Metodo---\n"
				+ "1 - byte\n"
				+ "2 - short\n"
				+ "3 - int\n"
				+ "4 - long\n"
				+ "5 - float\n"
				+ "6 - double\n"
				+ "7 - boolean\n"
				+ "8 - char\n"
				+ "9 - String\n"
				+ "10 - void");
		System.out.print("Digite o numero da opcao desejada: ");
	}
	
	public void menuAdicionarParametro(){
		System.out.println("\n---Escolha se deseja adicionar um parametro ao metodo---\n"
				+ "1 - Sim\n"
				+ "2 - Nao\n");
		System.out.print("Digite o numero da opcao desejada: ");
	}
	
	public void menuTipoParametro(){
		System.out.println("\n---Menu do Tipo do Parametro---\n"
				+ "1 - byte\n"
				+ "2 - short\n"
				+ "3 - int\n"
				+ "4 - long\n"
				+ "5 - float\n"
				+ "6 - double\n"
				+ "7 - boolean\n"
				+ "8 - char\n"
				+ "9 - String");
		System.out.print("Digite o numero da opcao desejada: ");
	}
	
	public void menuRelacionamentoClasse(){
		System.out.println("\n---Menu do Tipo do Relacionamento---\n"
				+ "0 - Associacao\n"
				+ "1 - Agregacao\n"
				+ "2 - Composicao\n"
				+ "3 - Generalizacao\n"
				+ "4 - Especializacao\n"
				+ "5 - Dependencia\n");
		System.out.print("Digite o numero da opcao desejada: ");
	}
	
	public void menuRelacionamentoInterface(){
		System.out.println("\n---Menu do Tipo do Relacionamento---\n"
				+ "0 - Generalizacao\n"
				+ "1 - Especializacao\n");
		System.out.print("Digite o numero da opcao desejada: ");
	}
	
	public void menuDirecao(String tipoE1, String nomeE1, String tipoE2, String nomeE2){
		System.out.println("\n---Menu de Direcao---\n"
				+ "0 - "+ tipoE1 + " " + nomeE1 + " ----> "+ tipoE2 + " " + nomeE2 + "\n"
				+ "1 - "+ tipoE1 + " " + nomeE1 + " <---- "+ tipoE2 + " " + nomeE2 + "\n"
				+ "2 - "+ tipoE1 + " " + nomeE1 + " <----> "+ tipoE2 + " " + nomeE2 + "\n");
		
		System.out.print("Digite o numero da opcao desejada: ");
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
				
				Atributo daVez =(Atributo) filhos.get(i); 
				//System.out.println("Atributo!");
				String ModificadorAcesso = "";	
				
				switch(daVez.getModificadoresDeAcesso().toLowerCase()) {
					case "public":
						ModificadorAcesso="+";
						break;
					case "private":
						ModificadorAcesso="-";
						break;
					case "protected":
						ModificadorAcesso="#";
						break;
					case "default":
						ModificadorAcesso="~";
						break;
					default:
						ModificadorAcesso="~";
						break;
				}
				
				String preConteudo="| "+ ModificadorAcesso + daVez.getNome()+ " : "+ daVez.getTipo();
				int tamPreConteudo = preConteudo.length();
				String spaces = new String(new char[TamHeader-tamPreConteudo-1]).replace("\0", " ");
				Conteudo = preConteudo + spaces+"|";
						
				atributos.add(Conteudo);
			}
			if (filhos.get(i) instanceof Metodo) {
				Metodo daVez =(Metodo) filhos.get(i);
				//System.out.println("Metodo!");
				String ModificadorAcesso = "";		
				
				switch(daVez.getModificadoresDeAcesso().toLowerCase()) {
					case "public":
						ModificadorAcesso="+";
						break;
					case "private":
						ModificadorAcesso="-";
						break;
					case "protected":
						ModificadorAcesso="#";
						break;
					case "default":
						ModificadorAcesso="~";
						break;
					default:
						ModificadorAcesso="+";
						break;
				}
				
				String preConteudo="| "+ ModificadorAcesso + daVez.getNome()+ "():"+ daVez.getTipoRetorno();
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
