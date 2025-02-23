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
	public static ComponenteComposto paiDoAtual;
	public static ComponenteComposto elementoCompostoAtual;
	public static Componente elementoAtual;
	
	public static List<Relacionamento> relacionamentos = new ArrayList<Relacionamento>();
	
	//menu que ira se adaptar as possibilidades de criacao, exclusao, etc
	public static void menu() {	
			
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
	    		opcao = c.verificarEntrada(qtdOpcoes);
	        	c.opcoesMenuInicial(opcao);
	        	break;

	        //Menu de Diagramas   
	        case "diagrama":
	        	if(elementoCompostoAtual.getFilhos().size() > 0) {
	        		imprimirClassesOuInterfaces();
	        		mostrarRelacionamentos(relacionamentos);
	        	}
	        	
		        qtdOpcoes = 5;  
		        menu.mostrarMenu("diagrama");
		        mostrarOpcoesDosFilhos("diagrama", qtdOpcoes, atrelaElementoAoNumero);
		        qtdOpcoes += elementoCompostoAtual.getFilhos().size();
		        
		        System.out.println("0 - Sair");
		        System.out.print("Digite o numero da opcao desejada: ");
		          
		        opcao = c.verificarEntrada(qtdOpcoes);
		        c.opcoesMenuDiagrama(opcao, atrelaElementoAoNumero);  
		        break;

		    //Menu de Classes
	        case "classe": 
	        	qtdOpcoes = 5;
	        	
	        	imprimirClassesOuInterfaces();
	        	mostrarRelacionamentos(relacionamentos);
	        	menu.mostrarMenu("classe");
	        	mostrarOpcoesDosFilhos("classe", qtdOpcoes, atrelaElementoAoNumero);
	        	qtdOpcoes += elementoCompostoAtual.getFilhos().size();
	        	
	        	System.out.println("0 - Sair");
		        System.out.print("Digite o numero da opcao desejada: ");
	        	
	        	opcao = c.verificarEntrada(qtdOpcoes);
	        	c.opcoesMenuClasseOuInterface(opcao, atrelaElementoAoNumero);
	        	break;
	        	
	        case "interface":
	        	qtdOpcoes = 5;
	        	
	        	imprimirClassesOuInterfaces();
	        	mostrarRelacionamentos(relacionamentos);
	        	menu.mostrarMenu("interface");
	        	mostrarOpcoesDosFilhos("interface", qtdOpcoes, atrelaElementoAoNumero);
	        	qtdOpcoes += elementoCompostoAtual.getFilhos().size();
	        	
	        	System.out.println("0 - Sair");
		        System.out.print("Digite o numero da opcao desejada: ");
	        	
	        	opcao = c.verificarEntrada(qtdOpcoes);
	        	c.opcoesMenuClasseOuInterface(opcao, atrelaElementoAoNumero);
	        	break;

	        case "atributo": 
	        	int qtdOpcoesTipo = 9;
	        	int qtdOpcoesModificador = 4;
	        	
	        	menu.mostrarMenu("atributoTipo");
	        	opcaoTipo = c.verificarEntrada(qtdOpcoesTipo);
	        	
	        	if(elementoCompostoAtual instanceof Classe) {
	        		menu.mostrarMenu("atributoModificador");
		        	opcaoModificador = c.verificarEntrada(qtdOpcoesModificador);
	        	}
	        	
	        	if(elementoCompostoAtual instanceof Classe) {
	        		c.opcoesMenuTipoAtributo(opcaoTipo, opcaoModificador);
	        	} else if (elementoCompostoAtual instanceof Interface) {
	        		c.opcoesMenuTipoAtributoInterface(opcaoTipo);
	        	}
		        
	        	break;

	        case "metodo":
	        	int qtdOpcoesTipoRetorno = 10;
	        	int qtdOpcoesModificadorMetodoClasse = 4;
	        	int qtdOpcoesModificadorMetodoInterface = 2;
	        	
	        	menu.mostrarMenu("metodoTipoRetorno");
	        	opcaoTipo = c.verificarEntrada(qtdOpcoesTipoRetorno);
	        	
	        	if(elementoCompostoAtual instanceof Classe) {
	        		menu.mostrarMenu("metodoModificadorClasse");
		        	opcaoModificador = c.verificarEntrada(qtdOpcoesModificadorMetodoClasse);
	        	} else if(elementoCompostoAtual instanceof Interface) {
	        		menu.mostrarMenu("metodoModificadorInterface");
	        		opcaoModificador = c.verificarEntrada(qtdOpcoesModificadorMetodoInterface);
	        	}
	        	
	        	if(elementoCompostoAtual instanceof Classe) {
	        		c.opcoesMenuTipoMetodoClasse(opcaoTipo, opcaoModificador);
	        	} else if (elementoCompostoAtual instanceof Interface) {
	        		c.opcoesMenuTipoMetodoInterface(opcaoTipo, opcaoModificador);
	        	}

	        	break;

	        case "relacionamento":
	        	qtdOpcoes = paiDoAtual.getFilhos().size();
	        	mostrarListaFilhos(paiDoAtual);
	        	opcao = c.verificarEntrada(qtdOpcoes);
	        	c.opcoesMenuRelacionamento(opcao, c);
	        	break;
	      }
	}

	public static void criarElemento(String oQueCriar, String nomeOQueCriar, String tipoAtributo, String modificadorAtributo){
			
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

			case "relacionamento":
				break;
		}
	}
	
	public static void criarElementoMetodo(String nomeOQueCriar, String tipoRetorno, String modificadoresDeAcesso ) {
		int qtdOpcoes = 2;
		int qtdOpcoesTipoParametro = 9;
		int opcao = -1;
		int opcaoTipoParametro = -1;
		
		Menu menu = new Menu();
		Controller c = new Controller();
		Metodo m = new Metodo(nomeOQueCriar, tipoRetorno, modificadoresDeAcesso);
		elementoCompostoAtual.addFilho(m);
		salvarElemento(elementoCompostoAtual);
		elementoAberto = "classe";
		
		do {
			menu.mostrarMenu("adicionarParametro");
			opcao = c.verificarEntrada(qtdOpcoes);
			
			if(opcao == 1) {
				menu.mostrarMenu("tipoParametro");
				opcaoTipoParametro = c.verificarEntrada(qtdOpcoesTipoParametro);
				Parametro p = null;
				
				if (opcaoTipoParametro == 1) {
					p = new Parametro("byte", null);
				} else if (opcaoTipoParametro == 2) {
					p = new Parametro("short", null);
				} else if (opcaoTipoParametro == 3) {
					p = new Parametro("int", null);
				} else if (opcaoTipoParametro == 4) {
					p = new Parametro("long", null);
				} else if (opcaoTipoParametro == 5) {
					p = new Parametro("float", null);
				} else if (opcaoTipoParametro == 6) {
					p = new Parametro("double", null);
				} else if (opcaoTipoParametro == 7) {
					p = new Parametro("boolean", null);
				} else if (opcaoTipoParametro == 8) {
					p = new Parametro("char", null);
				} else if (opcaoTipoParametro == 9) {
					p = new Parametro("String", null);
				}
				
				m.addFilho(p);
			}	
		}while(opcao == 1);
		
		
		menu();
	}
	
	public int verificarEntrada(int qtdOpcoes) {
		Scanner entrada = new Scanner(System.in);
		int opcao = -1;
		
		do{
    		entrada = new Scanner(System.in);
    		try {
    			opcao = entrada.nextInt();
    			if(opcao < 0 || opcao > qtdOpcoes) {
    				throw new InputMismatchException();
    			}
    		} catch (InputMismatchException e) {
    			System.out.print("Opcao invalida! Digite uma opcao valida: ");
    		}
    	}while(opcao < 0 || opcao > qtdOpcoes);	
		return opcao;
	}
	
	public int verificarEntradaRelac(int qtdOpcoes, int opcao1, ComponenteComposto e1, ComponenteComposto e2){
		Scanner entrada = new Scanner(System.in);
		boolean entradaValida = true;
		int opcao = -1;
		do{
    		entrada = new Scanner(System.in);
    		try {
    			opcao = entrada.nextInt();
    			if(opcao < 0 || opcao > qtdOpcoes) {
    				throw new InputMismatchException();
    			}
    			else if(opcao == opcao1){
    				System.out.println("Escolha outro elemento.");
    				throw new InputMismatchException();
    			}
    			else if(e1 != null){
    				ComponenteComposto elementoEscolhido2 = (ComponenteComposto)paiDoAtual.getFilhos().get(opcao);
    				e2 = elementoEscolhido2;
    			}
    			if(e1 != null && e2 != null){
    				if(e2 instanceof Interface){
    					if(opcao != 3 && opcao != 4){
    						System.out.println("Relacionamento inv�lido para Interface");
    						throw new InputMismatchException();
    					}
    				}
    			}
    			entradaValida = true;
    		} catch (InputMismatchException e) {
    			entradaValida = false;
    			System.out.print("Opcao invalida! Digite uma opcao valida: ");
    		}
    	}while(opcao < 0 || opcao > qtdOpcoes || opcao == opcao1 || !entradaValida);	
		return opcao;
	}
	public static void abrirDiagrama() { 
		try {
			File f = new File(".", "diagramasSalvos.txt");
			Scanner input = new Scanner(f);
			Pattern inicioRegex = Pattern.compile("\\[\\d+\\]");
			int qtdOpcoesAbrirDiagrama = 0;
			int opcaoEscolhida = -1;
			int tam = relacionamentos.size() -1;
			
			for (int i=tam;i>=0;i--) { 
				relacionamentos.remove(i);
			}
			
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
							
							if(linhaNovamente.contains("@relac@")) {
								String informacoesLinha[] = linhaNovamente.split(":");
								String nomeClasse1 = informacoesLinha[1];
								String nomeClasse2 = informacoesLinha[2];
								String tipoRelacionamento = informacoesLinha[3];
								String direcao = informacoesLinha[4];
								String navegabilidade = informacoesLinha[5];
								Componente classe1 = null;
								Componente classe2 = null;
								List<Componente> filhos = elementoCompostoAtual.getFilhos();

								try {
									for(int x=0;x<filhos.size();x++) {
										if(filhos.get(x).getNome().equals(nomeClasse1)) {
											classe1 = filhos.get(x);
										} else if(filhos.get(x).getNome().equals(nomeClasse2)) {
											classe2 = filhos.get(x);
										}
									}
								} catch (NullPointerException npe) {
									System.err.println("Classes do relacionamento n�o encontradas!");
								}
								
								Relacionamento relac = new Relacionamento(classe1, classe2, tipoRelacionamento, tipoRelacionamento, direcao, navegabilidade);
								
								relacionamentos.add(relac);

							} else {
								String pegandoNomeClasse[] = linhaNovamente.split("\\{");
								String pegandoClasseOuInterf[] = pegandoNomeClasse[1].split(":");
								String nomeClasse = pegandoNomeClasse[0];
				        		
				        		
								if (pegandoClasseOuInterf[0].equals("multipClasse")) {
									//criando a classe
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
					    		        	
					    		        	Metodo m = new Metodo(metodoNome, retMetodoNome, modifMetodoNome);
					    		        	
					    		        	if(splitDentroMetodos.length > 2) {
					    		        		String paramMetodo[] = splitDentroMetodos[2].split("-");
						    		        	String paramMetodoMaisUm[] = paramMetodo[1].split("\\(");
						    		        	String paramMetodoNome = paramMetodoMaisUm[0];
						    		        	
						    		        	String tipoParametro[] = paramMetodoMaisUm[1].split("_");
						    		        	String tipoParametroMaisUm[] = tipoParametro[1].split("\\)");
						    		        	String tipoParametroNome = tipoParametroMaisUm[0];
						    		        	
						    		        	Parametro p = new Parametro(tipoParametroNome, paramMetodoNome);
						    		        	m.addFilho(p);
					    		        	}
					    		        	
					    		        	// adiciona o metodo na classe
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
								} else if (pegandoClasseOuInterf[0].equals("multipInterface")) {
									Interface interf = new Interface(nomeClasse);
									elementoCompostoAtual.addFilho(interf);
									elementoAberto = "interface";
									paiDoAtual = elementoCompostoAtual;
									elementoCompostoAtual = interf;
									salvarElemento(interf);
									
									String componentesInterface[] = linhaNovamente.split(";");
					        		for(int i=0; i<componentesInterface.length; i++) {        			
					        			
					        			String subComponente[] = componentesInterface[i].split(":");
					        			String multipInterface;
					        			String navegInterface;
					        			String modifInterface;
					        			
					        			Pattern regexVerifInicio = Pattern.compile("\\w*\\{\\w*");
					        			
					    				Matcher matcherPedacoInicio = regexVerifInicio.matcher(subComponente[0]);
					    		        boolean matchesPedacoInicio = matcherPedacoInicio.matches();
			
					    		        if (matchesPedacoInicio) {
					    		        	String splitMultip[] = subComponente[0].split("\\{");
							        		if(splitMultip[1].equals("multipClasse")) {
							        			multipInterface = subComponente[1];
							        			
							        			// adiciona a multiplicidade na interface
							        			interf.setMultiplicidade(multipInterface);
							        		}
							        	} else if (subComponente[0].equals("atributo")) {
					    		        	String splitAtr[] = subComponente[1].split("\\[");
					    		        	String atrNome = splitAtr[0];
					    		        	
					    		        	String splitDentroAtributos[] = splitAtr[1].split(",");
					    		        	String tipoAtr[] = splitDentroAtributos[0].split("-");
					    		        	String tipoAtrNome = tipoAtr[1];
					    		        	
					    		        	String modificadorAtr[] = splitDentroAtributos[1].split("-");
					    		        	String modificadorAtrNome = modificadorAtr[1];
			
					    		        	
					    		        	// adiciona o atributo na interface
					    		        	Atributo a = new Atributo(atrNome, tipoAtrNome, modificadorAtrNome);
					    					elementoCompostoAtual.addFilho(a);
					    					salvarElemento(elementoCompostoAtual);
					    					elementoAberto = "interface";
					    					
					    		        } else if (subComponente[0].equals("metodo")) {
					    		        	String splitMetodo[] = subComponente[1].split("\\[");
					    		        	String metodoNome = splitMetodo[0];
					    		        	
					    		        	String splitDentroMetodos[] = splitMetodo[1].split(",");
					    		        	String retMetodo[] = splitDentroMetodos[0].split("-");
					    		        	String retMetodoNome = retMetodo[1];
					    		        	
					    		        	String modifMetodo[] = splitDentroMetodos[1].split("-");
					    		        	String modifMetodoNome = modifMetodo[1];
					    		        	
					    		        	Metodo m = new Metodo(metodoNome, retMetodoNome, modifMetodoNome);
					    		        	
					    		        	if(splitDentroMetodos.length > 2) {
					    		        		String paramMetodo[] = splitDentroMetodos[2].split("-");
						    		        	String paramMetodoMaisUm[] = paramMetodo[1].split("\\(");
						    		        	String paramMetodoNome = paramMetodoMaisUm[0];
						    		        	
						    		        	String tipoParametro[] = paramMetodoMaisUm[1].split("_");
						    		        	String tipoParametroMaisUm[] = tipoParametro[1].split("\\)");
						    		        	String tipoParametroNome = tipoParametroMaisUm[0];
						    		        	
						    		        	Parametro p = new Parametro(tipoParametroNome, paramMetodoNome);
						    		        	m.addFilho(p);
					    		        	}
					    		        	
					    		        	// adiciona o metodo na interface
					    					elementoCompostoAtual.addFilho(m);
					    					salvarElemento(elementoCompostoAtual);
					    					elementoAberto = "interface";
					    		        	
					    		        } else if (subComponente[0].equals("navegInterface")) {
					    		        	navegInterface = subComponente[1];
					    		        	
					    		        	// adiciona a navegabilidade na interface
					    		        	interf.setNavegabilidade(navegInterface);
					    		        } else if (subComponente[0].equals("modifInterface")) {
					    		        	modifInterface = subComponente[1];
					    		        	
					    		        	// adiciona o modificador na interface
					    		        	interf.setMultiplicidade(modifInterface);
					    		        }
					    		        
					        		}
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
    		criarElemento("diagrama", null, null, null);
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
      	  criarElemento("classe", null, null, null);
        }
        else if(opcao == 3) {
      	  System.out.println("\nCriar interface");
      	  criarElemento("interface", null, null, null);
        }
        
        else if(opcao == 4) {
      	  System.out.println("\nSalvando diagrama...");
      	  try {
      		  DiagramasSalvos.salvarDiagrama(elementoAberto, elementoCompostoAtual, relacionamentos);
      		  elementoAberto = "inicial";
      		  System.out.println("Diagrama salvo com sucesso!");
      		  menu();
      	  } catch (Exception e) {
      		  System.err.println("N�o foi poss�vel salvar o diagrama. Tente novamente.");
      	  }
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
      	  	//paiDoAtual = elementoCompostoAtual;
        	opcao = opcao - 6;
      	  	elementoCompostoAtual = (ComponenteComposto) atrelaElementoAoNumero.get(opcao);
      	  	elementoAberto = "classe";
      	  	menu();
        }
	}
	
	public void opcoesMenuClasseOuInterface(int opcao, Map<Integer, Componente> atrelaElementoAoNumero) {
		if(opcao == 0) {
			System.exit(0);
		}
		
		else if(opcao == 1){
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
		} else {
			opcao = opcao - 6;
			Componente componenteNome = atrelaElementoAoNumero.get(opcao);
			List<Componente> filhosRecuperados = elementoCompostoAtual.getFilhos();
			for(int i = 0;i<filhosRecuperados.size();i++) {
				if (filhosRecuperados.get(i).getNome().equals(componenteNome.getNome())) {
		        	// remove o atributo a ser modificado
		        	elementoCompostoAtual.removerFilho(componenteNome.getNome(), filhosRecuperados.get(i).getClass().getSimpleName());
		        	
		        	// coleta as informa��es para substituir o componente antigo
		        	if(componenteNome instanceof Atributo) {
		        		elementoAberto = "atributo";
		        	} else if(componenteNome instanceof Metodo) {
		        		elementoAberto = "metodo";
		        	}
		      	  	menu();
				}
			}
		}
	}
	
	public void opcoesMenuTipoAtributo(int opcaoTipo, int opcaoModificador) {
		if(opcaoTipo == 1){
    		if(opcaoModificador == 1)
    			criarElemento("atributo", null, "byte", "Default");
    		else if(opcaoModificador == 2)
    			criarElemento("atributo", null, "byte", "Private");
    		else if(opcaoModificador == 3)
    			criarElemento("atributo", null, "byte", "Public");
    		else if(opcaoModificador == 4)
    			criarElemento("atributo", null, "byte", "Protected");
    	} else if(opcaoTipo == 2) {
    		if(opcaoModificador == 1)
    			criarElemento("atributo", null, "short", "Default");
    		else if(opcaoModificador == 2)
    			criarElemento("atributo", null, "short", "Private");
    		else if(opcaoModificador == 3)
    			criarElemento("atributo", null, "short", "Public");
    		else if(opcaoModificador == 4)
    			criarElemento("atributo", null, "short", "Protected");
    	} else if(opcaoTipo == 3) {
    		if(opcaoModificador == 1)
    			criarElemento("atributo", null, "int", "Default");
    		else if(opcaoModificador == 2)
    			criarElemento("atributo", null, "int", "Private");
    		else if(opcaoModificador == 3)
    			criarElemento("atributo", null, "int", "Public");
    		else if(opcaoModificador == 4)
    			criarElemento("atributo", null, "int", "Protected");
    	} else if(opcaoTipo == 4) {
    		if(opcaoModificador == 1)
    			criarElemento("atributo", null, "long", "Default");
    		else if(opcaoModificador == 2)
    			criarElemento("atributo", null, "long", "Private");
    		else if(opcaoModificador == 3)
    			criarElemento("atributo", null, "long", "Public");
    		else if(opcaoModificador == 4)
    			criarElemento("atributo", null, "long", "Protected");
    	} else if(opcaoTipo == 5) {
    		if(opcaoModificador == 1)
    			criarElemento("atributo", null, "float", "Default");
    		else if(opcaoModificador == 2)
    			criarElemento("atributo", null, "float", "Private");
    		else if(opcaoModificador == 3)
    			criarElemento("atributo", null, "float", "Public");
    		else if(opcaoModificador == 4)
    			criarElemento("atributo", null, "float", "Protected");
    	} else if(opcaoTipo == 6) {
    		if(opcaoModificador == 1)
    			criarElemento("atributo", null, "double", "Default");
    		else if(opcaoModificador == 2)
    			criarElemento("atributo", null, "double", "Private");
    		else if(opcaoModificador == 3)
    			criarElemento("atributo", null, "double", "Public");
    		else if(opcaoModificador == 4)
    			criarElemento("atributo", null, "double", "Protected");
    	} else if(opcaoTipo == 7) {
    		if(opcaoModificador == 1)
    			criarElemento("atributo", null, "boolean", "Default");
    		else if(opcaoModificador == 2)
    			criarElemento("atributo", null, "boolean", "Private");
    		else if(opcaoModificador == 3)
    			criarElemento("atributo", null, "boolean", "Public");
    		else if(opcaoModificador == 4)
    			criarElemento("atributo", null, "boolean", "Protected");
    	} else if(opcaoTipo == 8) {
    		if(opcaoModificador == 1)
    			criarElemento("atributo", null, "char", "Default");
    		else if(opcaoModificador == 2)
    			criarElemento("atributo", null, "char", "Private");
    		else if(opcaoModificador == 3)
    			criarElemento("atributo", null, "char", "Public");
    		else if(opcaoModificador == 4)
    			criarElemento("atributo", null, "char", "Protected");
    	} else if(opcaoTipo == 9) {
    		if(opcaoModificador == 1)
    			criarElemento("atributo", null, "String", "Default");
    		else if(opcaoModificador == 2)
    			criarElemento("atributo", null, "String", "Private");
    		else if(opcaoModificador == 3)
    			criarElemento("atributo", null, "String", "Public");
    		else if(opcaoModificador == 4)
    			criarElemento("atributo", null, "String", "Protected");
    	}	
	}
	
	public void opcoesMenuTipoAtributoInterface(int opcaoTipo) {
		if(opcaoTipo == 1){
    		criarElemento("atributo", null, "byte", "Public");
    	} else if(opcaoTipo == 2) {
    		criarElemento("atributo", null, "short", "Public");
    	} else if(opcaoTipo == 3) {
    		criarElemento("atributo", null, "int", "Public");
    	} else if(opcaoTipo == 4) {
    		criarElemento("atributo", null, "long", "Public");
    	} else if(opcaoTipo == 5) {
    		criarElemento("atributo", null, "float", "Public");
    	} else if(opcaoTipo == 6) {
    		criarElemento("atributo", null, "double", "Public");
    	} else if(opcaoTipo == 7) {
    		criarElemento("atributo", null, "boolean", "Public");
    	} else if(opcaoTipo == 8) {
    		criarElemento("atributo", null, "char", "Public");
    	} else if(opcaoTipo == 9) {
    		criarElemento("atributo", null, "String", "Public");
    	}
	}
	
	public void opcoesMenuTipoMetodoClasse(int opcaoTipo, int opcaoModificador) {
		if(opcaoTipo == 1){
    		if(opcaoModificador == 1)
    			criarElementoMetodo(null, "byte", "Default");
    		else if(opcaoModificador == 2)
    			criarElementoMetodo(null, "byte", "Private");
    		else if(opcaoModificador == 3)
    			criarElementoMetodo(null, "byte", "Public");
    		else if(opcaoModificador == 4)
    			criarElementoMetodo(null, "byte", "Protected");
    	} else if(opcaoTipo == 2) {
    		if(opcaoModificador == 1)
    			criarElementoMetodo(null, "short", "Default");
    		else if(opcaoModificador == 2)
    			criarElementoMetodo(null, "short", "Private");
    		else if(opcaoModificador == 3)
    			criarElementoMetodo(null, "short", "Public");
    		else if(opcaoModificador == 4)
    			criarElementoMetodo(null, "short", "Protected");
    	} else if(opcaoTipo == 3) {
    		if(opcaoModificador == 1)
    			criarElementoMetodo(null, "int", "Default");
    		else if(opcaoModificador == 2)
    			criarElementoMetodo(null, "int", "Private");
    		else if(opcaoModificador == 3)
    			criarElementoMetodo(null, "int", "Public");
    		else if(opcaoModificador == 4)
    			criarElementoMetodo(null, "int", "Protected");
    	} else if(opcaoTipo == 4) {
    		if(opcaoModificador == 1)
    			criarElementoMetodo(null, "long", "Default");
    		else if(opcaoModificador == 2)
    			criarElementoMetodo(null, "long", "Private");
    		else if(opcaoModificador == 3)
    			criarElementoMetodo(null, "long", "Public");
    		else if(opcaoModificador == 4)
    			criarElementoMetodo(null, "long", "Protected");
    	} else if(opcaoTipo == 5) {
    		if(opcaoModificador == 1)
    			criarElementoMetodo(null, "float", "Default");
    		else if(opcaoModificador == 2)
    			criarElementoMetodo(null, "float", "Private");
    		else if(opcaoModificador == 3)
    			criarElementoMetodo(null, "float", "Public");
    		else if(opcaoModificador == 4)
    			criarElementoMetodo(null, "float", "Protected");
    	} else if(opcaoTipo == 6) {
    		if(opcaoModificador == 1)
    			criarElementoMetodo(null, "double", "Default");
    		else if(opcaoModificador == 2)
    			criarElementoMetodo(null, "double", "Private");
    		else if(opcaoModificador == 3)
    			criarElementoMetodo(null, "double", "Public");
    		else if(opcaoModificador == 4)
    			criarElementoMetodo(null, "double", "Protected");
    	} else if(opcaoTipo == 7) {
    		if(opcaoModificador == 1)
    			criarElementoMetodo(null, "boolean", "Default");
    		else if(opcaoModificador == 2)
    			criarElementoMetodo(null, "boolean", "Private");
    		else if(opcaoModificador == 3)
    			criarElementoMetodo(null, "boolean", "Public");
    		else if(opcaoModificador == 4)
    			criarElementoMetodo(null, "boolean", "Protected");
    	} else if(opcaoTipo == 8) {
    		if(opcaoModificador == 1)
    			criarElementoMetodo(null, "char", "Default");
    		else if(opcaoModificador == 2)
    			criarElementoMetodo(null, "char", "Private");
    		else if(opcaoModificador == 3)
    			criarElementoMetodo(null, "char", "Public");
    		else if(opcaoModificador == 4)
    			criarElementoMetodo(null, "char", "Protected");
    	} else if(opcaoTipo == 9) {
    		if(opcaoModificador == 1)
    			criarElementoMetodo(null, "String", "Default");
    		else if(opcaoModificador == 2)
    			criarElementoMetodo(null, "String", "Private");
    		else if(opcaoModificador == 3)
    			criarElementoMetodo(null, "String", "Public");
    		else if(opcaoModificador == 4)
    			criarElementoMetodo(null, "String", "Protected");
    	} else if(opcaoTipo == 10) {
    		if(opcaoModificador == 1)
    			criarElementoMetodo(null, "void", "Default");
    		else if(opcaoModificador == 2)
    			criarElementoMetodo(null, "void", "Private");
    		else if(opcaoModificador == 3)
    			criarElementoMetodo(null, "void", "Public");
    		else if(opcaoModificador == 4)
    			criarElementoMetodo(null, "void", "Protected");
    	}
	}
	
	public void opcoesMenuTipoMetodoInterface(int opcaoTipo, int opcaoModificador) {
		if(opcaoTipo == 1){
    		if(opcaoModificador == 1)
    			criarElementoMetodo(null, "byte", "Default");
    		else if(opcaoModificador == 2)
    			criarElementoMetodo(null, "byte", "Public");
    	} else if(opcaoTipo == 2) {
    		if(opcaoModificador == 1)
    			criarElementoMetodo(null, "short", "Default");
    		else if(opcaoModificador == 2)
    			criarElementoMetodo(null, "short", "Public");
    	} else if(opcaoTipo == 3) {
    		if(opcaoModificador == 1)
    			criarElementoMetodo(null, "int", "Default");
    		else if(opcaoModificador == 2)
    			criarElementoMetodo(null, "int", "Public");
    	} else if(opcaoTipo == 4) {
    		if(opcaoModificador == 1)
    			criarElementoMetodo(null, "long", "Default");
    		else if(opcaoModificador == 2)
    			criarElementoMetodo(null, "long", "Public");
    	} else if(opcaoTipo == 5) {
    		if(opcaoModificador == 1)
    			criarElementoMetodo(null, "float", "Default");
    		else if(opcaoModificador == 2)
    			criarElementoMetodo(null, "float", "Public");
    	} else if(opcaoTipo == 6) {
    		if(opcaoModificador == 1)
    			criarElementoMetodo(null, "double", "Default");
    		else if(opcaoModificador == 2)
    			criarElementoMetodo(null, "double", "Public");
    	} else if(opcaoTipo == 7) {
    		if(opcaoModificador == 1)
    			criarElementoMetodo(null, "boolean", "Default");
    		else if(opcaoModificador == 2)
    			criarElementoMetodo(null, "boolean", "Public");
    	} else if(opcaoTipo == 8) {
    		if(opcaoModificador == 1)
    			criarElementoMetodo(null, "char", "Default");
    		else if(opcaoModificador == 2)
    			criarElementoMetodo(null, "char", "Public");
    	} else if(opcaoTipo == 9) {
    		if(opcaoModificador == 1)
    			criarElementoMetodo(null, "String", "Default");
    		else if(opcaoModificador == 2)
    			criarElementoMetodo(null, "String", "Public");
    	} else if(opcaoTipo == 10) {
    		if(opcaoModificador == 1)
    			criarElementoMetodo(null, "void", "Default");
    		else if(opcaoModificador == 2)
    			criarElementoMetodo(null, "void", "Public");
    	}
	}
	
	public void opcoesMenuRelacionamento(int opcao1, Controller c){
		String tipoRelac = "";
		String direcao = "";
		String navegabilidade = "";
		
		int qtdElementos = paiDoAtual.getFilhos().size();
		int qtdOpcoesRelac = 5;
		int qtdDir = 2;
		int qtdOpcoesNaveg = 4;
		
		int opcaoRelac = -1;
		int opcao2 = -1;
		int optDir = -1;
		int optNaveg = -1;
		
		Menu menu = new Menu();
		ComponenteComposto elementoEscolhido1 = (ComponenteComposto)paiDoAtual.getFilhos().get(opcao1);
		if(elementoEscolhido1 instanceof Classe){
			menu.menuRelacionamentoClasse();
			opcaoRelac = c.verificarEntradaRelac(qtdOpcoesRelac, -1, null, null);
			switch(opcaoRelac){
				case 0:
					tipoRelac = "Associacao";
					break;
				case 1:
					tipoRelac = "Agregacao";
					break;
				case 2:
					tipoRelac = "Composicao";
					break;
				case 3:
					tipoRelac = "Generalizacao";
					break;
				case 4:
					tipoRelac = "Especializacao";
					break;
				case 5:
					tipoRelac = "Dependencia";
					break;
			}
			
			
			
		} else if(elementoEscolhido1 instanceof Interface){
			menu.menuRelacionamentoInterface();
			qtdOpcoesRelac = 2;
			opcaoRelac = c.verificarEntradaRelac(qtdOpcoesRelac, -1, null, null);
			switch(opcaoRelac){
				case 0:
					tipoRelac = "Generalizacao";
					break;
				case 1:
					tipoRelac = "Especializacao";
					break;
			}			
		}
		
		System.out.println("Relacionamento escolhido: " + tipoRelac);
		if(tipoRelac.equals("Generalizacao") || tipoRelac.equals("Especializacao")){
			direcao = "Direita";
		}
			
		mostrarListaFilhos(paiDoAtual);
		opcao2 = c.verificarEntradaRelac(qtdElementos, opcao1, elementoEscolhido1, null);
		ComponenteComposto elementoEscolhido2 = (ComponenteComposto)paiDoAtual.getFilhos().get(opcao2);
		
		if(!direcao.equals("Direita")){
			String tipoE1 = elementoEscolhido1.getClass().getSimpleName();
			String tipoE2 = elementoEscolhido2.getClass().getSimpleName();
			menu.menuDirecao(tipoE1, elementoEscolhido1.getNome(), tipoE2, elementoEscolhido2.getNome());
			optDir = c.verificarEntrada(qtdDir);
			
			if(optDir == 0){
				direcao = "Direita";
			}
			else if(optDir == 1){
				direcao = "Esquerda";
			}
			else if(optDir == 2){
				direcao = "Ambos";
			}
		}
		
		menu.menuRelacionamentoMultiplicidade();
		optNaveg = c.verificarEntrada(qtdOpcoesNaveg);
		switch(optNaveg){
		case 0:
			navegabilidade = "1...1";
			break;
		case 1:
			navegabilidade = "1...n";
			break;
		case 2:
			navegabilidade = "n...1";
			break;
		case 3:
			navegabilidade = "n...n";
			break;
		}
		
		Relacionamento r = new Relacionamento(elementoEscolhido1, elementoEscolhido2, tipoRelac, tipoRelac, direcao, navegabilidade);
		relacionamentos.add(r);
		elementoAberto = "classe";
		menu();
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
			Interface copia = new Interface(elementoCompostoAtual.getNome());
			copia = copiarElementoInterface((Interface)elementoCompostoAtual, copia);
			DiagramasSalvos.quickSave(copia);
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
				String tipoFilho = elementoCompostoAtual.getFilhos().get(i-qtdOpcoes).getClass().getSimpleName().toLowerCase();
				System.out.println((i+1) + " - Abrir a "+ tipoFilho +" " + elementoCompostoAtual.getFilhos().get(i-qtdOpcoes).getNome());
		        int opcaoDaClasse = atrelaElementoAoNumero.size();
	            atrelaElementoAoNumero.put(opcaoDaClasse, elementoCompostoAtual.getFilhos().get(opcaoDaClasse));
	        }
		}
		
		else if(tipo.equals("classe") || tipo.equals("interface")){
			for(int i = qtdOpcoes; i < (elementoCompostoAtual.getFilhos().size() + qtdOpcoes); i++){
		        if (elementoCompostoAtual.getFilhos().get(i-qtdOpcoes) instanceof Atributo) {
		        	System.out.println((i+1) + " - Modificar atributo " + elementoCompostoAtual.getFilhos().get(i-qtdOpcoes).getNome());
		        } else if (elementoCompostoAtual.getFilhos().get(i-qtdOpcoes) instanceof Metodo){
		        	System.out.println((i+1) + " - Modificar metodo " + elementoCompostoAtual.getFilhos().get(i-qtdOpcoes).getNome());
		        }
				
		        int opcaoDoAtributo = atrelaElementoAoNumero.size();
		        atrelaElementoAoNumero.put(opcaoDoAtributo, elementoCompostoAtual.getFilhos().get(opcaoDoAtributo));
	         }
      	
		}
	}
	
	public static void mostrarListaFilhos(ComponenteComposto paiDoAtual){
		System.out.println("Selecione um elemento: \n");
		for(int i = 0; i < paiDoAtual.getFilhos().size(); i++){
			String tipoFilho = paiDoAtual.getFilhos().get(i).getClass().getSimpleName();
			String nomeFilho = paiDoAtual.getFilhos().get(i).getNome();
			System.out.println(i + " - "+ tipoFilho + " " + nomeFilho);
		}
		System.out.print("\nDigite o numero da opcao desejada: ");
	}
	
	public static void mostrarRelacionamentos(List<Relacionamento> lista){
		System.out.println("\nRelacionamentos:");
		for(int i=0; i < lista.size(); i++){
			lista.get(i).desenha();
		}
	}
}
