package composite;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {

	public static String elementoAberto = "inicial";
	public static Componente paiDoAtual;
	public static ComponenteComposto elementoCompostoAtual;
	public static Componente elementoAtual;	

	public static void menu(){			//menu que ira se adaptar as possibilidades de criacao, exclusao, etc
			
		int opcao = -1;
	    int qtdOpcoes;
	    Menu menu = new Menu();
	    
	    Map<Integer, Componente> atrelaElementoAoNumero = new HashMap<Integer, Componente>();
	    
	    Controller c = new Controller();

	    switch(elementoAberto){
	      
	      //Menu inicial
	    	case "inicial":
	    		abrirDiagrama();
	    		qtdOpcoes = 3;
	        	
	    		menu.mostrarMenu("inicial");
	          
	    		opcao = c.verificarEntrada(elementoAberto, qtdOpcoes);

	        	c.opcoesMenuInicial(opcao);
	    		
	          break;

	          
	        //Menu de Diagramas   
	        case "diagrama":
	        	
		          qtdOpcoes = 4;
		          
		          menu.mostrarMenu("diagrama");
		          
		          for(int i = 4; i < (elementoCompostoAtual.getFilhos().size() + 4); i++){
			          System.out.println((i+1) + " - Abrir a classe " + elementoCompostoAtual.getFilhos().get(i-4).getNome());
			          int opcaoDaClasse = atrelaElementoAoNumero.size();
			          atrelaElementoAoNumero.put(opcaoDaClasse, elementoCompostoAtual.getFilhos().get(opcaoDaClasse));
		          }
		   
		          qtdOpcoes += elementoCompostoAtual.getFilhos().size();
		          
		          System.out.println("0 - Sair");
		          System.out.print("Digite o numero da opcao desejada: ");
		          
		          opcao = c.verificarEntrada(elementoAberto, qtdOpcoes);
		
		          c.opcoesMenuDiagrama(opcao, atrelaElementoAoNumero);
		          
		        break;

		    //Menu de Classes
	        case "classe": 
	        	qtdOpcoes = 4;
	        	menu.mostrarMenu("classe");
	        	
	        	opcao = c.verificarEntrada(elementoAberto, qtdOpcoes);
	        	
	        	c.opcoesMenuClasse(opcao);
	        	
	        	break;
	        	
	        case "interface":
	        	//------------FALTA FAZER--------------
	        	menu.mostrarMenu("interface");
	        	break;

	        case "atributo": 
	        	//------------FALTA FAZER--------------
	        	System.out.print("Digite o numero da opcao desejada: ");
	        	break;

	        case "metodo":
	        	//------------FALTA FAZER--------------
	        	System.out.print("Digite o numero da opcao desejada: ");
	        	break;

	      }
	}

		public static void criarElemento(String oquecriar){	
			
			switch(oquecriar){
			
				case "diagrama":
					Diagrama d = new Diagrama(null);
					elementoAberto = "diagrama";
					elementoCompostoAtual = d;
					menu();
					break;
					
				case "classe":
					Classe c = new Classe(null);
					elementoCompostoAtual.addFilho(c);
					elementoAberto = "classe";
					//elementoCompostoAtual = c;
					menu();
					break;
					
				case "interface":
					Interface i = new Interface(null);
					elementoAberto = "interface";
					elementoCompostoAtual = i;
					menu();
					break;
					
				case "atributo":
					break;
					
				case "metodo":
					break;
					
				case "parametro":
					break;
					
				case "relacionamento":
					break;
			}
		}

		//abre um arquivo contendo um diagrama e passa os elementos para uma lista de elementos
		public static List<Componente> abreDiagrama(){			
			
			List<Componente> teste = new ArrayList<Componente>();
			return teste;
		}
	
	public int verificarEntrada(String elementoAberto, int qtdOpcoes) {
		
		Menu menu = new Menu();
		Scanner entrada = new Scanner(System.in);
		int opcao = -1;
		
		do{
    		entrada = new Scanner(System.in);
    		
    		try {
    			opcao = entrada.nextInt();
          
    			if(opcao < 0 || opcao > qtdOpcoes) {
    				throw new InputMismatchException();
    			}
    		}

    		catch (InputMismatchException e) {
    			System.out.println("Opcao invalida!");
    			
    			if(elementoAberto.equals("inicial)")){
    				menu.mostrarMenu("inicial");
    			}
    			
    			if(elementoAberto.equals("diagrama")) {
    				menu.mostrarMenu("diagrama");
	            	
	            	for(int i = 4; i < (elementoCompostoAtual.getFilhos().size() + 4); i++){
	  		          System.out.println((i+1) + " - Abrir a classe " + elementoCompostoAtual.getFilhos().get(i-4).getNome());
	            	}
	            	
	            	System.out.println("0 - Sair");
	  	          	System.out.print("Digite o numero da opcao desejada: ");
    			}
    		}
        
    	}while(opcao < 0 || opcao > qtdOpcoes);
		
		return opcao;
	}
	
	public static void salvarDiagrama() { 
		try(FileWriter fw = new FileWriter("Composite/diagramasSalvos", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println("teste");
			  //-----fazendo ainda
			    
			} catch (IOException e) {
			    System.out.println("Nao foi possivel salvar o diagrama. Tente novamente.\n");
	        	elementoAberto = "inicial";
	            menu();
			    
			}
	}
	
	public static void abrirDiagrama() { 
		try {
			File f = new File("Composite/diagramasSalvos.txt");
			Scanner input = new Scanner(f);
			Pattern inicioRegex = Pattern.compile("\\[\\d+\\]");
			
			while (input.hasNextLine()) {
				String linha = input.nextLine();
				Matcher matcher = inicioRegex.matcher(linha);
		        boolean matches = matcher.matches();
		        if(matches) {
		        	while(input.hasNextLine()) {
		        		linha = input.nextLine();
		        		int posicaoNomeClasse = linha.indexOf("{");
		        		String nomeClasse;
		        		if(posicaoNomeClasse != -1) {
		        			nomeClasse = linha.substring(0 , posicaoNomeClasse);
		        		}
		        		//-----fazendo ainda
		        		
		        	}
		        	
		        	
		        }
		        //System.out.println("matches: "+matches);
			}
			input.close();
		} catch (FileNotFoundException e){
			System.out.println("Nao ha diagramas salvos.");
		}
	}
	
	public void opcoesMenuInicial(int opcao) {
		if(opcao==1){
    		System.out.println("\nCriar diagrama");
    		criarElemento("diagrama");
    	} 
    	
    	else if(opcao==2){
    		System.out.println("\nAbrir diagrama");
    		abreDiagrama();
    	} 
      
    	else if(opcao==0){
    		System.exit(0);
    	}
    	
	}
	
	public void opcoesMenuDiagrama(int opcao, Map<Integer, Componente> atrelaElementoAoNumero) {
		if(opcao == 0) {
      	  System.exit(0);
        }
        
        else if(opcao == 1) {
      	  System.out.print("\n");
      	  elementoAberto = "inicial";
            menu();
        }
        
        else if(opcao == 2) {
      	  System.out.println("\nCriar classe");
      	  criarElemento("classe");
        }
        else if(opcao == 3) {
      	  System.out.println("\nCriar interface");
      	  criarElemento("interface");
        }
        
        else if(opcao == 4) {
      	  System.out.println("Salvando diagrama...");
      	  salvarDiagrama();
        }
        else {
      	  paiDoAtual = elementoCompostoAtual;
      	  elementoAtual = atrelaElementoAoNumero.get(opcao);
      	  elementoAberto = "classe";
      	  menu();
        }
	}
	
	public void opcoesMenuClasse(int opcao) {
		if(opcao==1){
    		System.out.print("\n");
    		elementoAberto = "diagrama";
    		menu();
    	} 
    	
	}
}
