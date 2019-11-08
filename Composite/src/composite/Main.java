package composite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

  public static String elementoAberto = "inicial";
  public static Componente paiDoAtual;
  public static ComponenteComposto elementoCompostoAtual;
  public static Componente elementoAtual;	

	public static void menu(){			//menu que ira se adaptar as possibilidades de criacao, exclusao, etc
		
    Scanner entrada;
    int opcao = -1;
    //int contagemDasOpcoes;
    Menu menu = new Menu();
    
    Map<Integer, Componente> atrelaElementoAoNumero = new HashMap<Integer, Componente>();

    switch(elementoAberto){
      
      //Menu inicial
    	case "inicial":
        	
    		menu.mostrarMenu("inicial");
          
    		do{
        		entrada = new Scanner(System.in);
        		
        		try {
        			opcao = entrada.nextInt();
              
        			if(opcao < 0 || opcao > 2) {
        				throw new InputMismatchException();
        			}
        		}

        		catch (InputMismatchException e) {
        			System.out.println("Opcao invalida!");
        			menu.mostrarMenu("inicial");
        		}
            
        	}while(opcao < 0 || opcao > 2);

        	if(opcao==1){
        		System.out.println("Criar diagrama");
        		criarElemento("diagrama");
        	} 
        	
        	else if(opcao==2){
        		System.out.println("Abrir diagrama");
        		abreDiagrama();
        	} 
          
        	else if(opcao==0){
        		System.exit(0);
        	}
        	
          break;

          
        //Menu de Diagramas   
        case "diagrama":
        	
	          //contagemDasOpcoes = 3;
	          menu.mostrarMenu("diagrama");
	          
	          for(int i = 0; i < elementoCompostoAtual.getFilhos().size(); i++){
	        	  
		          System.out.println((i+1) + " - Abrir a classe " + elementoCompostoAtual.getFilhos().get(i).getNome());
		          
		          int opcaoDaClasse = atrelaElementoAoNumero.size() + 1;
		          
		          atrelaElementoAoNumero.put(opcaoDaClasse, elementoCompostoAtual.getFilhos().get(opcaoDaClasse));
		          
	          }
	        
	          /*for(int i = contagemDasOpcoes; (i-3)<elementoCompostoAtual.getFilhos().size(); i++){
	            System.out.println((i+1) + " - Abrir a classe " + elementoCompostoAtual.getFilhos().get(i-3).getNome());
	            int opcaoDaClasse = i + 1;
	            atrelaElementoAoNumero.put(opcaoDaClasse, elementoCompostoAtual.getFilhos().get(opcaoDaClasse-3));
	          }*/
	          
	          System.out.println("0 - Sair");
	          
	          do{
	        	  
	            entrada = new Scanner(System.in);;
	                      
	            try {
	              opcao = entrada.nextInt();
	              
	              if(opcao < 0 || opcao > 3) {
	                throw new InputMismatchException();
	              }
	              
	            }
	            
	            catch (InputMismatchException e) {
	            	
	            	menu.mostrarMenu("diagrama");
	            	
	            	for(int i=0; i<elementoCompostoAtual.getFilhos().size(); i++){
	            		System.out.println((4+i) + " - Abrir a classe " + elementoCompostoAtual.getFilhos().get(i).getNome());
	            	}
	            	
	            	System.out.println("0 - Sair");
	
	            }
	          
	          }while(opcao < 0 || opcao > 3);
	
	          if(opcao == 0) {
	        	  System.exit(0);
	          }
	          
	          else if(opcao == 1) {
	        	  elementoAberto = "inicial";
	              menu();
	          }
	          
	          else if(opcao == 2) {
	        	  criarElemento("classe");
	          }
	          else if(opcao == 3) {
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
        	menu.mostrarMenu("classe");
        	
        	break;
        	
        case "interface":
        	menu.mostrarMenu("interface");
        	break;

        case "atributo":
        	System.out.println("Selecione a opção que desejar");
        	break;

        case "metodo":
        	System.out.println("Selecione a opção que desejar");
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
				elementoAberto = "classe";
				elementoCompostoAtual = c;
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

	public static void main(String[] args) {

		System.out.println("Instrucoes iniciais:");
		System.out.println();
		menu();
		
	}

}
