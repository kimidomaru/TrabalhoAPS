package composite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
	        	qtdOpcoes = 3;
	        	
	    		menu.mostrarMenu("inicial");
	          
	    		opcao = c.verificarEntrada(elementoAberto, qtdOpcoes);

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
	        	
	          break;

	          
	        //Menu de Diagramas   
	        case "diagrama":
	        	
		          qtdOpcoes = 3;
		          
		          menu.mostrarMenu("diagrama");
		          
		          for(int i = 3; i < (elementoCompostoAtual.getFilhos().size() + 3); i++){
			          System.out.println((i+1) + " - Abrir a classe " + elementoCompostoAtual.getFilhos().get(i-3).getNome());
			          int opcaoDaClasse = atrelaElementoAoNumero.size();
			          atrelaElementoAoNumero.put(opcaoDaClasse, elementoCompostoAtual.getFilhos().get(opcaoDaClasse));
		          }
		   
		          qtdOpcoes += elementoCompostoAtual.getFilhos().size();
		          
		          System.out.println("0 - Sair");
		          System.out.print("Digite o numero da opcao desejada: ");
		          
		          opcao = c.verificarEntrada(elementoAberto, qtdOpcoes);
		
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
		          else {
		        	  paiDoAtual = elementoCompostoAtual;
		        	  elementoAtual = atrelaElementoAoNumero.get(opcao);
		        	  elementoAberto = "classe";
		        	  menu();
		          }
		          
		        break;

		    //Menu de Classes
	        case "classe": 
	        	qtdOpcoes = 4;
	        	menu.mostrarMenu("classe");
	        	
	        	opcao = c.verificarEntrada(elementoAberto, qtdOpcoes);
	        	
	        	if(opcao==1){
	        		System.out.print("\n");
	        		elementoAberto = "diagrama";
	        		menu();
	        	} 
	        	
	        	break;
	        	
	        case "interface":
	        	menu.mostrarMenu("interface");
	        	break;

	        case "atributo": 
	        	System.out.print("Digite o numero da opcao desejada: ");
	        	break;

	        case "metodo":
	        	System.out.print("Digite o numero da opcao desejada: ");
	        	break;

	      }
		}

		public static void criarElemento(String oquecriar){	
			
			switch(oquecriar){
			
				case "diagrama":
					Diagrama d = new Diagrama();
					elementoAberto = "diagrama";
					elementoCompostoAtual = d;
					menu();
					break;
					
				case "classe":
					Classe c = new Classe();
					elementoCompostoAtual.addFilho(c);
					elementoAberto = "classe";
					//elementoCompostoAtual = c;
					menu();
					break;
					
				case "interface":
					Interface i = new Interface();
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
	            	
	            	for(int i = 3; i < (elementoCompostoAtual.getFilhos().size() + 3); i++){
	  		          System.out.println((i+1) + " - Abrir a classe " + elementoCompostoAtual.getFilhos().get(i-3).getNome());
	            	}
	            	
	            	System.out.println("0 - Sair");
	  	          	System.out.print("Digite o numero da opcao desejada: ");
    			}
    		}
        
    	}while(opcao < 0 || opcao > qtdOpcoes);
		
		return opcao;
	}
}
