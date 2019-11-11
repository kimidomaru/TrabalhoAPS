package composite;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
	//public static Componente paiDoAtual;
	public static ComponenteComposto paiDoAtual;
	public static ComponenteComposto elementoCompostoAtual;
	public static Componente elementoAtual;

	public static void menu(){			//menu que ira se adaptar as possibilidades de criacao, exclusao, etc
			
		int opcao = -1;
		int opcaoTipo = -1;
    	int opcaoModificador = -1;
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
	        	c.opcoesMenuInicial(opcao);
	        	break;

	        //Menu de Diagramas   
	        case "diagrama":
	        	if(elementoCompostoAtual.getFilhos().size() > 0) {
	        		imprimirClassesOuInterfaces();
	        	}
	        	
		        qtdOpcoes = 5;  
		        menu.mostrarMenu("diagrama");
		        mostrarOpcoesDosFilhos("diagrama", qtdOpcoes, atrelaElementoAoNumero);
		        qtdOpcoes += elementoCompostoAtual.getFilhos().size();
		        
		        System.out.println("0 - Sair");
		        System.out.print("Digite o numero da opcao desejada: ");
		          
		        opcao = c.verificarEntrada(elementoAberto, qtdOpcoes);
		        c.opcoesMenuDiagrama(opcao, atrelaElementoAoNumero);  
		        break;

		    //Menu de Classes
	        case "classe": 
	        	qtdOpcoes = 5;
	        	
	        	imprimirClassesOuInterfaces();
	        	menu.mostrarMenu("classe");
	        	mostrarOpcoesDosFilhos("classe", qtdOpcoes, atrelaElementoAoNumero);
	        	qtdOpcoes += elementoCompostoAtual.getFilhos().size();
	        	
	        	System.out.println("0 - Sair");
		        System.out.print("Digite o numero da opcao desejada: ");
	        	
	        	opcao = c.verificarEntrada(elementoAberto, qtdOpcoes);
	        	c.opcoesMenuClasseOuInterface(opcao);
	        	break;
	        	
	        case "interface":
	        	qtdOpcoes = 5;
	        	
	        	imprimirClassesOuInterfaces();
	        	menu.mostrarMenu("interface");
	        	mostrarOpcoesDosFilhos("interface", qtdOpcoes, atrelaElementoAoNumero);
	        	qtdOpcoes += elementoCompostoAtual.getFilhos().size();
	        	
	        	System.out.println("0 - Sair");
		        System.out.print("Digite o numero da opcao desejada: ");
	        	
	        	opcao = c.verificarEntrada(elementoAberto, qtdOpcoes);
	        	c.opcoesMenuClasseOuInterface(opcao);
	        	break;

	        case "atributo": 
	        	int qtdOpcoesTipo = 9;
	        	int qtdOpcoesModificador = 4;
	        	
	        	menu.mostrarMenu("atributoTipo");
	        	opcaoTipo = c.verificarEntrada(elementoAberto, qtdOpcoesTipo);
	        	
	        	if(elementoCompostoAtual instanceof Classe) {
	        		menu.mostrarMenu("atributoModificador");
		        	opcaoModificador = c.verificarEntrada(elementoAberto, qtdOpcoesModificador);
	        	}
	        	
	        	if(elementoCompostoAtual instanceof Classe) {
	        		c.opcoesMenuTipoAtributo(opcaoTipo, opcaoModificador);
	        	} else if (elementoCompostoAtual instanceof Interface) {
	        		c.opcoesMenuTipoAtributoInterface(opcaoTipo);
	        	}
		        
	        	break;

	        case "metodo":
	        	//
	        	break;

	      }
	}

	public static void criarElemento(String oQueCriar, String nomeOQueCriar, boolean abrindoDiagrama, String tipoAtributo, String modificadorAtributo){
			
		switch(oQueCriar){
			
			case "diagrama":
				Diagrama d = new Diagrama(nomeOQueCriar);
				elementoAberto = "diagrama";
				elementoCompostoAtual = d;
				salvarElemento(d);
				menu();
				break;
				
			case "classe":
				Classe c = new Classe(nomeOQueCriar);
				/////// DEBUG PARA SALVAR DIAGRAMA
				/*
				Metodo m = new Metodo("metTeste");
				Parametro p = new Parametro("int", "paraTeste");
				m.addFilho(p);
				Atributo a = new Atributo("testAtr","int","private");
				Metodo m2 = new Metodo("metTeste2");
				Parametro p2 = new Parametro("int", "paraTeste2");
				Parametro p3 = new Parametro("int", "paraTeste3");
				m2.addFilho(p2);
				m2.addFilho(p3);
				
				c.addFilho(m);
				c.addFilho(a);
				c.addFilho(m2);
				*/
				///////
				elementoCompostoAtual.addFilho(c);
				elementoAberto = "classe";
				paiDoAtual = elementoCompostoAtual;
				elementoCompostoAtual = c;
				salvarElemento(c);
				menu();
				break;
				
			case "interface":
				Interface i = new Interface(nomeOQueCriar);
				elementoCompostoAtual.addFilho(i);
				elementoAberto = "interface";
				paiDoAtual = elementoCompostoAtual;
				elementoCompostoAtual = i;
				salvarElemento(i);
				menu();
				break;
				
			case "atributo":
				Atributo a = new Atributo(nomeOQueCriar, tipoAtributo, modificadorAtributo);
				elementoCompostoAtual.addFilho(a);
				salvarElemento(elementoCompostoAtual);
				elementoAberto = "classe";
				menu();
				break;
				
			case "metodo":
				break;
				
			case "parametro":
				break;
				
			case "relacionamento":
				break;
		}
	}

	public int verificarEntrada(String elementoAberto, int qtdOpcoes) {
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
    			System.out.print("Opcao invalida! Digite uma opcao valida: ");
    		}
        
    	}while(opcao < 0 || opcao > qtdOpcoes);
		
		return opcao;
	}
	
	public static void abrirDiagrama() { 
		try {
			File f = new File("Composite/diagramasSalvos.txt");
			Scanner input = new Scanner(f);
			Pattern inicioRegex = Pattern.compile("\\[\\d+\\]");
			int qtdOpcoesAbrirDiagrama = 0;
			int opcaoEscolhida = -1;
			
			System.out.println("\n---Menu para abrir diagrama---");
			while (input.hasNextLine()) {
				String linha = input.nextLine();
				Matcher matcher = inicioRegex.matcher(linha);
		        boolean matches = matcher.matches();

		        if(matches) {
		        	
		        	// criando o menu
		        	String numeroCabecalho[] = linha.split("\\[");
		        	String numeroCabecalhoSozinho[] = numeroCabecalho[1].split("\\]");
		        	System.out.print(numeroCabecalhoSozinho[0] + " - ");
		        	linha = input.nextLine();
		        	System.out.println(linha);
		        	qtdOpcoesAbrirDiagrama++;
		        }
		    }
			
			System.out.println("0 - Voltar");
	        System.out.print("Digite o numero da opcao desejada: ");
	        
			do{
	        	Scanner escolha = new Scanner(System.in);
	        	try {
	    			opcaoEscolhida = escolha.nextInt();
	          
	    			if(opcaoEscolhida < 0 || opcaoEscolhida > qtdOpcoesAbrirDiagrama) {
	    				throw new InputMismatchException();
	    			}
	    		} catch (InputMismatchException e) {
	    			System.out.print("Opcao invalida! Digite uma opcao valida: ");
	    		}
	        }while(opcaoEscolhida < 0 || opcaoEscolhida > qtdOpcoesAbrirDiagrama);
			
			if(opcaoEscolhida == 0) {
				menu();
			} else {
				Scanner inputNovamente = new Scanner(f);
				Pattern diagramaEscolhidoRegex = Pattern.compile("\\[" + Integer.toString(opcaoEscolhida) + "\\]");
				String linhaNovamente;
				
				while(inputNovamente.hasNextLine()) {
					linhaNovamente = inputNovamente.nextLine();
					Matcher matcherNovamente = diagramaEscolhidoRegex.matcher(linhaNovamente);
			        boolean matchesNovamente = matcherNovamente.matches();
			        
			        if(matchesNovamente) {
			        	linhaNovamente = inputNovamente.nextLine();
			        	
			        	// criando diagrama
			        	Diagrama d = new Diagrama(linhaNovamente);
						elementoAberto = "diagrama";
						elementoCompostoAtual = d;
						salvarElemento(d);
						
						while(inputNovamente.hasNextLine() && !(linhaNovamente = inputNovamente.nextLine()).equals("=END")) {
							elementoCompostoAtual = d;
							int posicaoNomeClasse = linhaNovamente.indexOf("{");
							String nomeClasse = null;
			        		if(posicaoNomeClasse != -1) {
			        			nomeClasse = linhaNovamente.substring(0 , posicaoNomeClasse);
			        		}
			        		
			        		//criando classe e adicionando seus atributos e/ou metodos
			        		Classe c = new Classe(nomeClasse);
							elementoCompostoAtual.addFilho(c);
							elementoAberto = "classe";
							paiDoAtual = elementoCompostoAtual;
							elementoCompostoAtual = c;
							salvarElemento(c);
							
			        		String componentesClasse[] = linhaNovamente.split(";");
			        		for(int i=0; i<componentesClasse.length; i++) {        			
			        			
			        			String subComponente[] = componentesClasse[i].split(":");
			        			String multipClasse;
			        			String navegClasse;
			        			String modifClasse;
			        			
			        			Pattern regexVerifInicio = Pattern.compile("\\w*\\{\\w*");
			        			
			    				Matcher matcherPedacoInicio = regexVerifInicio.matcher(subComponente[0]);
			    		        boolean matchesPedacoInicio = matcherPedacoInicio.matches();
	
			    		        if (matchesPedacoInicio) {
			    		        	String splitMultip[] = subComponente[0].split("\\{");
					        		if(splitMultip[1].equals("multipClasse")) {
					        			multipClasse = subComponente[1];
					        			
					        			// adiciona a multiplicidade na classe
					        			c.setMultiplicidade(multipClasse);
					        		}
					        	} else if (subComponente[0].equals("atributo")) {
			    		        	String splitAtr[] = subComponente[1].split("\\[");
			    		        	String atrNome = splitAtr[0];
			    		        	
			    		        	String splitDentroAtributos[] = splitAtr[1].split(",");
			    		        	String tipoAtr[] = splitDentroAtributos[0].split("-");
			    		        	String tipoAtrNome = tipoAtr[1];
			    		        	
			    		        	String modificadorAtr[] = splitDentroAtributos[1].split("-");
			    		        	String modificadorAtrNome = modificadorAtr[1];
	
			    		        	
			    		        	// adiciona o atributo na classe
			    		        	Atributo a = new Atributo(atrNome, tipoAtrNome, modificadorAtrNome);
			    					elementoCompostoAtual.addFilho(a);
			    					salvarElemento(elementoCompostoAtual);
			    					elementoAberto = "classe";
			    					
			    		        } else if (subComponente[0].equals("metodo")) {
			    		        	String splitMetodo[] = subComponente[1].split("\\[");
			    		        	String metodoNome = splitMetodo[0];
			    		        	
			    		        	String splitDentroMetodos[] = splitMetodo[1].split(",");
			    		        	String retMetodo[] = splitDentroMetodos[0].split("-");
			    		        	String retMetodoNome = retMetodo[1];
			    		        	
			    		        	String modifMetodo[] = splitDentroMetodos[1].split("-");
			    		        	String modifMetodoNome = modifMetodo[1];
			    		        	
			    		        	String paramMetodo[] = splitDentroMetodos[2].split("-");
			    		        	String paramMetodoMaisUm[] = paramMetodo[1].split("\\(");
			    		        	String paramMetodoNome = paramMetodoMaisUm[0];
			    		        	
			    		        	String tipoParametro[] = paramMetodoMaisUm[1].split("_");
			    		        	String tipoParametroMaisUm[] = tipoParametro[1].split("\\)");
			    		        	String tipoParametroNome = tipoParametroMaisUm[0];
			    		        	
			    		        	// adiciona o metodo na classe
			    		        	Metodo m = new Metodo(metodoNome);
			    		        	m.setTipoRetorno(retMetodoNome);
			    		        	m.setModificadoresDeAcesso(modifMetodoNome);
			    		        	Parametro p = new Parametro(tipoParametroNome, paramMetodoNome);
			    		        	m.addFilho(p);
			    		        	
			    					elementoCompostoAtual.addFilho(m);
			    					salvarElemento(elementoCompostoAtual);
			    					elementoAberto = "classe";
			    		        	
			    		        } else if (subComponente[0].equals("navegClasse")) {
			    		        	navegClasse = subComponente[1];
			    		        	
			    		        	// adiciona a navegabilidade na classe
			    		        	c.setNavegabilidade(navegClasse);
			    		        } else if (subComponente[0].equals("modifClasse")) {
			    		        	modifClasse = subComponente[1];
			    		        	
			    		        	// adiciona o modificador na classe
			    		        	c.setMultiplicidade(modifClasse);
			    		        }
			    		        
			        		}
						
						}
						
						// ir para o menu de diagrama
						elementoAberto = "diagrama";
						elementoCompostoAtual = d;
						paiDoAtual = d;
						menu();
			        }
			        
				}
				
				inputNovamente.close();
			}
			
			input.close();
		
		} catch (FileNotFoundException e){
			System.err.println("Nao ha diagramas salvos.");
		}
	}
	
	public void opcoesMenuInicial(int opcao) {
		if(opcao==1){
    		System.out.println("\nCriar diagrama");
    		criarElemento("diagrama", null, false, null, null);
    	} 
    	
    	else if(opcao==2){
    		abrirDiagrama();
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
      	  criarElemento("classe", null, false, null, null);
        }
        else if(opcao == 3) {
      	  System.out.println("\nCriar interface");
      	  criarElemento("interface", null, false, null, null);
        }
        
        else if(opcao == 4) {
      	  System.out.println("Salvando diagrama...");
      	  DiagramasSalvos.salvarDiagrama(elementoAberto, elementoCompostoAtual);
      	  elementoAberto = "inicial";
      	  menu();
        }
		
        else if(opcao == 5) {
        	elementoCompostoAtual = DiagramasSalvos.undo();
        	if(elementoCompostoAtual == null) {
        		paiDoAtual = null;
        		elementoAberto = "inicial";
        	}
        	else if(elementoCompostoAtual instanceof Diagrama) {
        		elementoAberto = "diagrama";
        	}
        	else if(elementoCompostoAtual instanceof Classe) {
        		elementoAberto = "classe";
        	}
        	else if(elementoCompostoAtual instanceof Interface) {
        		elementoAberto = "interface";
        	}
        	menu();
        }
		
        else {
      	  paiDoAtual = elementoCompostoAtual;
      	  elementoAtual = atrelaElementoAoNumero.get(opcao);
      	  elementoAberto = "classe";
      	  menu();
        }
	}
	
	public void opcoesMenuClasseOuInterface(int opcao) {
		if(opcao == 1){
    		System.out.print("\n");
    		elementoCompostoAtual = paiDoAtual;
    		elementoAberto = "diagrama";
    		menu();
    	} 
		
		else if(opcao == 2) {
			System.out.println("\n");
			elementoAberto = "atributo";
			menu();
		} 
		
		else if(opcao == 3) {
			System.out.println("\n");
			elementoAberto = "metodo";
			menu();
		}
		
		else if(opcao == 4) {
			System.out.println("\n");
			elementoAberto = "relacionamento";
			menu();
		}
		
		else if(opcao == 5) {
			paiDoAtual.removerFilho(elementoCompostoAtual.getNome(), elementoCompostoAtual.getClass().getSimpleName());
        	elementoCompostoAtual = DiagramasSalvos.undo();
        	if(elementoCompostoAtual == null) {
        		elementoAberto = "inicial";
        		paiDoAtual = null;
        	}
        	else if(elementoCompostoAtual instanceof Diagrama) {
        		elementoAberto = "diagrama";
        	}
        	else if(elementoCompostoAtual instanceof Classe) {
        		elementoAberto = "classe";
        		paiDoAtual.addFilho(elementoCompostoAtual);
        	}
        	else if(elementoCompostoAtual instanceof Interface) {
        		elementoAberto = "interface";
        		paiDoAtual.addFilho(elementoCompostoAtual);
        	}
        	menu();
		}
	}
	
	public void opcoesMenuTipoAtributo(int opcaoTipo, int opcaoModificador) {
		if(opcaoTipo == 1){
    		if(opcaoModificador == 1)
    			criarElemento("atributo", null, false, "byte", "Default");
    		else if(opcaoModificador == 2)
    			criarElemento("atributo", null, false, "byte", "Private");
    		else if(opcaoModificador == 3)
    			criarElemento("atributo", null, false, "byte", "Public");
    		else if(opcaoModificador == 4)
    			criarElemento("atributo", null, false, "byte", "Protected");
    	} else if(opcaoTipo == 2) {
    		if(opcaoModificador == 1)
    			criarElemento("atributo", null, false, "short", "Default");
    		else if(opcaoModificador == 2)
    			criarElemento("atributo", null, false, "short", "Private");
    		else if(opcaoModificador == 3)
    			criarElemento("atributo", null, false, "short", "Public");
    		else if(opcaoModificador == 4)
    			criarElemento("atributo", null, false, "short", "Protected");
    	} else if(opcaoTipo == 3) {
    		if(opcaoModificador == 1)
    			criarElemento("atributo", null, false, "int", "Default");
    		else if(opcaoModificador == 2)
    			criarElemento("atributo", null, false, "int", "Private");
    		else if(opcaoModificador == 3)
    			criarElemento("atributo", null, false, "int", "Public");
    		else if(opcaoModificador == 4)
    			criarElemento("atributo", null, false, "int", "Protected");
    	} else if(opcaoTipo == 4) {
    		if(opcaoModificador == 1)
    			criarElemento("atributo", null, false, "long", "Default");
    		else if(opcaoModificador == 2)
    			criarElemento("atributo", null, false, "long", "Private");
    		else if(opcaoModificador == 3)
    			criarElemento("atributo", null, false, "long", "Public");
    		else if(opcaoModificador == 4)
    			criarElemento("atributo", null, false, "long", "Protected");
    	} else if(opcaoTipo == 5) {
    		if(opcaoModificador == 1)
    			criarElemento("atributo", null, false, "float", "Default");
    		else if(opcaoModificador == 2)
    			criarElemento("atributo", null, false, "float", "Private");
    		else if(opcaoModificador == 3)
    			criarElemento("atributo", null, false, "float", "Public");
    		else if(opcaoModificador == 4)
    			criarElemento("atributo", null, false, "float", "Protected");
    	} else if(opcaoTipo == 6) {
    		if(opcaoModificador == 1)
    			criarElemento("atributo", null, false, "double", "Default");
    		else if(opcaoModificador == 2)
    			criarElemento("atributo", null, false, "double", "Private");
    		else if(opcaoModificador == 3)
    			criarElemento("atributo", null, false, "double", "Public");
    		else if(opcaoModificador == 4)
    			criarElemento("atributo", null, false, "double", "Protected");
    	} else if(opcaoTipo == 7) {
    		if(opcaoModificador == 1)
    			criarElemento("atributo", null, false, "boolean", "Default");
    		else if(opcaoModificador == 2)
    			criarElemento("atributo", null, false, "boolean", "Private");
    		else if(opcaoModificador == 3)
    			criarElemento("atributo", null, false, "boolean", "Public");
    		else if(opcaoModificador == 4)
    			criarElemento("atributo", null, false, "boolean", "Protected");
    	} else if(opcaoTipo == 8) {
    		if(opcaoModificador == 1)
    			criarElemento("atributo", null, false, "char", "Default");
    		else if(opcaoModificador == 2)
    			criarElemento("atributo", null, false, "char", "Private");
    		else if(opcaoModificador == 3)
    			criarElemento("atributo", null, false, "char", "Public");
    		else if(opcaoModificador == 4)
    			criarElemento("atributo", null, false, "char", "Protected");
    	} else if(opcaoTipo == 9) {
    		if(opcaoModificador == 1)
    			criarElemento("atributo", null, false, "String", "Default");
    		else if(opcaoModificador == 2)
    			criarElemento("atributo", null, false, "String", "Private");
    		else if(opcaoModificador == 3)
    			criarElemento("atributo", null, false, "String", "Public");
    		else if(opcaoModificador == 4)
    			criarElemento("atributo", null, false, "String", "Protected");
    	}	
	}
	
	public void opcoesMenuTipoAtributoInterface(int opcaoTipo) {
		if(opcaoTipo == 1){
    		criarElemento("atributo", null, false, "byte", "Public");
    	} else if(opcaoTipo == 2) {
    		criarElemento("atributo", null, false, "short", "Public");
    	} else if(opcaoTipo == 3) {
    		criarElemento("atributo", null, false, "int", "Public");
    	} else if(opcaoTipo == 4) {
    		criarElemento("atributo", null, false, "long", "Public");
    	} else if(opcaoTipo == 5) {
    		criarElemento("atributo", null, false, "float", "Public");
    	} else if(opcaoTipo == 6) {
    		criarElemento("atributo", null, false, "double", "Public");
    	} else if(opcaoTipo == 7) {
    		criarElemento("atributo", null, false, "boolean", "Public");
    	} else if(opcaoTipo == 8) {
    		criarElemento("atributo", null, false, "char", "Public");
    	} else if(opcaoTipo == 9) {
    		criarElemento("atributo", null, false, "String", "Public");
    	}
	}
	
	//COPIANDO OBJETOS PRA NAO ALTERAR O STATIC
	public static Diagrama copiarElementoDiagrama(Diagrama original, Diagrama copia) {
		copia.setMensagemCriado(original.getMensagemCriado());
		if(original.getFilhos().size() > 0) {
			for(int i = 0; i < original.getFilhos().size(); i++) {
				copia.addFilho(original.getFilhos().get(i));
			}
		}
		return copia;
	}
		
	public static Classe copiarElementoClasse (Classe original, Classe copia) {
		copia.setMultiplicidade(original.getMultiplicidade());
		copia.setModificadorDeAcesso(original.getModificadorDeAcesso());
		copia.setMultiplicidade(original.getMultiplicidade());
		copia.setNavegabilidade(original.getNavegabilidade());
		if(original.getFilhos().size() > 0) {
			for(int i = 0; i < original.getFilhos().size(); i++) {
				copia.addFilho(original.getFilhos().get(i));
			}
		}
		return copia;
	}
	
	public static Interface copiarElementoInterface(Interface original, Interface copia){
		//FALTA FAZER
		return copia;
	}
	
	public static void salvarElemento(ComponenteComposto c){
		if(c instanceof Diagrama){
			Diagrama copia = new Diagrama(elementoCompostoAtual.getNome());
			copia = copiarElementoDiagrama((Diagrama) elementoCompostoAtual, copia);
			DiagramasSalvos.quickSave(copia);
		}
		else if(c instanceof Classe){
			Classe copia = new Classe(elementoCompostoAtual.getNome());
			copia = copiarElementoClasse((Classe)elementoCompostoAtual, copia);
			DiagramasSalvos.quickSave(copia);
		}
		else if(c instanceof Interface){
			//FALTA FAZER
		}
	}
	
	public static void imprimirClassesOuInterfaces(){
		//METODO PRA IMPRIMIR TODAS AS CLASSES
		List <ComponenteComposto> filhosDoDiagrama = new ArrayList<ComponenteComposto>();
		Menu menu = new Menu();
    	for(int i = 0; i < paiDoAtual.getFilhos().size(); i++){
    		filhosDoDiagrama.add((ComponenteComposto)paiDoAtual.getFilhos().get(i));
    		menu.ShowComponente(filhosDoDiagrama.get(i));
    	}
	}
	
	public static void mostrarOpcoesDosFilhos(String tipo, int qtdOpcoes, Map<Integer, Componente> atrelaElementoAoNumero){
		
		if(tipo.equals("diagrama")){
			for(int i = qtdOpcoes; i < (elementoCompostoAtual.getFilhos().size() + qtdOpcoes); i++){
		          System.out.println((i+1) + " - Abrir a classe " + elementoCompostoAtual.getFilhos().get(i-qtdOpcoes).getNome());
		          int opcaoDaClasse = atrelaElementoAoNumero.size();
		          atrelaElementoAoNumero.put(opcaoDaClasse, elementoCompostoAtual.getFilhos().get(opcaoDaClasse));
	          }
		}
		
		else if(tipo.equals("classe") || tipo.equals("interface")){
			for(int i = qtdOpcoes; i < (elementoCompostoAtual.getFilhos().size() + qtdOpcoes); i++){
		          System.out.println((i+1) + " - Modificar atributo " + elementoCompostoAtual.getFilhos().get(i-qtdOpcoes).getNome());
		          int opcaoDoAtributo = atrelaElementoAoNumero.size();
		          atrelaElementoAoNumero.put(opcaoDoAtributo, elementoCompostoAtual.getFilhos().get(opcaoDoAtributo));
	         }
      	
		}
	}
	
}
