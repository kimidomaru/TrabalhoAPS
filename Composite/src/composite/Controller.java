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
	    		
	    		//// DESCOMENTE PARA TESTAR METODO abrirDiagrama() OU METODO salvarDiagrama() ////
	    		
	    		//salvarDiagrama();
	    		qtdOpcoes = 3;
	        	
	    		menu.mostrarMenu("inicial");
	          
	    		opcao = c.verificarEntrada(elementoAberto, qtdOpcoes);

	        	c.opcoesMenuInicial(opcao);
	    		
	          break;

	          
	        //Menu de Diagramas   
	        case "diagrama":
	        	if(elementoCompostoAtual.getFilhos().size() > 0) {
	        		imprimirClasses();
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
	        	
	        	imprimirClasses();
	        	//menu.ShowComponente(elementoCompostoAtual);

	        	qtdOpcoes = 5;
	        	menu.mostrarMenu("classe");
	        	
	        	mostrarOpcoesDosFilhos("classe", qtdOpcoes, atrelaElementoAoNumero);
	        	
	        	qtdOpcoes += elementoCompostoAtual.getFilhos().size();
	        	
	        	System.out.println("0 - Sair");
		        System.out.print("Digite o numero da opcao desejada: ");
	        	
	        	opcao = c.verificarEntrada(elementoAberto, qtdOpcoes);
	        	
	        	c.opcoesMenuClasse(opcao);
	        	
	        	break;
	        	
	        case "interface":
	        	//------------FALTA FAZER--------------
	        	qtdOpcoes = 5;
	        	menu.mostrarMenu("interface");
	        	
	        	mostrarOpcoesDosFilhos("interface", qtdOpcoes, atrelaElementoAoNumero);
	        	
	        	qtdOpcoes += elementoCompostoAtual.getFilhos().size();
	        	
	        	System.out.println("0 - Sair");
		        System.out.print("Digite o numero da opcao desejada: ");
	        	
	        	opcao = c.verificarEntrada(elementoAberto, qtdOpcoes);
	        	
	        	break;

	        case "atributo": 
	        	int qtdOpcoesTipo = 9;
	        	int qtdOpcoesModificador = 4;
	        	
	        	menu.mostrarMenu("atributoTipo");
	        	opcaoTipo = c.verificarEntrada(elementoAberto, qtdOpcoesTipo);
	        	
	        	menu.mostrarMenu("atributoModificador");
	        	opcaoModificador = c.verificarEntrada(elementoAberto, qtdOpcoesModificador);
	        	
		        c.opcoesMenuTipoAtributo(opcaoTipo, opcaoModificador);
	        	break;

	        case "metodo":
	        	//
	        	break;

	      }
	}

		public static void criarElemento(String oQueCriar, String nomeOQueCriar, boolean abrindoDiagrama, String tipoAtributo, String modificadorAtributo){
			
			switch(oQueCriar){
			
			case "diagrama":
				if (abrindoDiagrama == false) {
					Diagrama d = new Diagrama(nomeOQueCriar);
					elementoAberto = "diagrama";
					elementoCompostoAtual = d;
					salvarElemento(d);
					menu();
					break;
				} else {
					Diagrama d = new Diagrama(nomeOQueCriar);
					elementoAberto = "diagrama";
					elementoCompostoAtual = d;
					salvarElemento(d);
					break;
				}
				
				
			case "classe":
				if (abrindoDiagrama == false) {
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
				} else {
					Classe c = new Classe(nomeOQueCriar);
					elementoCompostoAtual.addFilho(c);
					elementoAberto = "classe";
					paiDoAtual = elementoCompostoAtual;
					elementoCompostoAtual = c;
					salvarElemento(c);
					break;
				}
				
				
			case "interface":
				if (abrindoDiagrama == false) {
					Interface i = new Interface(nomeOQueCriar);
					elementoCompostoAtual.addFilho(i);
					elementoAberto = "interface";
					elementoCompostoAtual = i;
					menu();
					break;
				} else {
					Interface i = new Interface(nomeOQueCriar);
					elementoCompostoAtual.addFilho(i);
					elementoAberto = "interface";
					elementoCompostoAtual = i;
					break;
				}
				
				
			case "atributo":
				if (abrindoDiagrama == false) {
					Atributo a = new Atributo(nomeOQueCriar, tipoAtributo, modificadorAtributo);
					elementoCompostoAtual.addFilho(a);
					salvarElemento(elementoCompostoAtual);
					elementoAberto = "classe";
					menu();
					break;
				} else {
					Atributo a = new Atributo(nomeOQueCriar, tipoAtributo, modificadorAtributo);
					elementoCompostoAtual.addFilho(a);
					salvarElemento(elementoCompostoAtual);
					elementoAberto = "classe";
					break;
				}
				
			case "metodo":
				if (abrindoDiagrama == false) {
					//
				} else {
					
				}
				
			case "parametro":
				break;
				
			case "relacionamento":
				break;
		}
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
    			
    			if(elementoAberto.equals("classe")) {
    				menu.mostrarMenu("classe");
    	        	
    	        	for(int i = qtdOpcoes; i < (elementoCompostoAtual.getFilhos().size() + qtdOpcoes); i++){
    			          System.out.println((i+1) + " - Modificar atributo " + elementoCompostoAtual.getFilhos().get(i-qtdOpcoes).getNome());
    		          }
    	        	
    	        	System.out.println("0 - Sair");
    		        System.out.print("Digite o numero da opcao desejada: ");
    			}
    		}
        
    	}while(opcao < 0 || opcao > qtdOpcoes);
		
		return opcao;
	}
	
	public static void salvarDiagrama() { 
		int qtdDiagramasSalvos = 0;
		boolean emBranco = true;
		
		try {
			File arquivo = new File("Composite/diagramasSalvos.txt");		
			Pattern cabecalhoRegex = Pattern.compile("\\[\\d+\\]");
			Scanner input = new Scanner(arquivo);
			
			while (input.hasNextLine()) {
				String linha = input.nextLine();
				Matcher matcher = cabecalhoRegex.matcher(linha);
			    boolean matches = matcher.matches();
			      
			    if(matches) {
			       	emBranco = false;
			    	qtdDiagramasSalvos++;
			    }
			}
			
			
			Pattern ultimoCabecalhoRegex = Pattern.compile("\\["+Integer.toString(qtdDiagramasSalvos)+"\\]");
			
			try {
				BufferedReader arquivoLer = new BufferedReader(new FileReader(arquivo));
				BufferedWriter arquivoEscrever = new BufferedWriter(new FileWriter(arquivo, true));
				
				if(emBranco) {
					arquivoEscrever.write("[1]");
		        	arquivoEscrever.flush();
		        	
		        	if(elementoAberto.equals("diagrama")) {
		        		arquivoEscrever.write("\n"+ elementoCompostoAtual.getNome());
			        	arquivoEscrever.flush();
		        		List<Componente> listaDeFilhos = elementoCompostoAtual.getFilhos();
						for(int i = 0; i<listaDeFilhos.size();i++) {
							Componente filhoPosicao = listaDeFilhos.get(i);
							if(filhoPosicao instanceof Classe) {
								Classe filhoDaPosicaoClasse = (Classe) filhoPosicao;
								arquivoEscrever.write("\n" + filhoDaPosicaoClasse.getNome() + "{multipClasse:" + filhoDaPosicaoClasse.getMultiplicidade() 
														+ ";navegClasse:" + filhoDaPosicaoClasse.getNavegabilidade()
														+ ";modifClasse:" + filhoDaPosicaoClasse.getModificadorDeAcesso());
					        	arquivoEscrever.flush();
					        	
					        	List<Componente> listaDeFilhosDaClasse = filhoDaPosicaoClasse.getFilhos();
					        	for(int j=0; j<listaDeFilhosDaClasse.size();j++) {
					        		Componente filhoPosicaoClasse = listaDeFilhosDaClasse.get(j);
					        		if(filhoPosicaoClasse instanceof Atributo) {
					        			Atributo filhoAtributo = (Atributo) filhoPosicaoClasse;
					        			arquivoEscrever.write(";atributo:" + filhoAtributo.getNome() + "[tipoAtr-" + filhoAtributo.getTipo()
					        									+ ",modifAtr-" + filhoAtributo.getModificadoresDeAcesso()
					        									+ "]");
					        			arquivoEscrever.flush();
					        		}
					        	}
					        	
					        	for(int k=0; k<listaDeFilhosDaClasse.size();k++) {
					        		Componente filhoPosicaoClasse = listaDeFilhosDaClasse.get(k);
					        		if(filhoPosicaoClasse instanceof Metodo) {
					        			Metodo filhoMetodo = (Metodo) filhoPosicaoClasse;
					        			arquivoEscrever.write(";metodo:" + filhoMetodo.getNome() + "[retornoMetodo-" + filhoMetodo.getTipoRetorno()
					        									+ ",modifMetodo-" + filhoMetodo.getModificadoresDeAcesso());
					        			arquivoEscrever.flush();
					        			
					        			List<Componente> listaDeFilhosDoMetodo = filhoMetodo.getFilhos();
					        			for(int l=0; l<listaDeFilhosDoMetodo.size();l++) {
					        				Componente filhoPosicaoMetodo = listaDeFilhosDoMetodo.get(l);
					        				if(filhoPosicaoMetodo instanceof Parametro) {
					        					Parametro filhoParametro = (Parametro) filhoPosicaoMetodo;
					        					if (l == listaDeFilhosDoMetodo.size()-1) {
					        						arquivoEscrever.write(",paramMetodo-" + filhoParametro.getNome() + "(tipoParam_" + filhoParametro.getTipo() + ")]");
						        					arquivoEscrever.flush();
					        					} else {
					        						arquivoEscrever.write(",paramMetodo-" + filhoParametro.getNome() + "(tipoParam_" + filhoParametro.getTipo() + ")");
						        					arquivoEscrever.flush();
					        					}
					        					
					        				}
					        			}
					        		}
					        	}
					        	
					        	arquivoEscrever.write("}");
			        			arquivoEscrever.flush();
							}
						}
						arquivoEscrever.write("\n=END");
	        			arquivoEscrever.flush();
					}
		        } else {
		        	String line;
					while((line = arquivoLer.readLine()) != null) {
						Matcher matcherLine = ultimoCabecalhoRegex.matcher(line);
				        boolean matchesLine = matcherLine.matches();
				        
				        if(matchesLine) {
				        	while((line = arquivoLer.readLine()) != null){
				        		// percorrendo o ultimo diagrama
				        	}
				        	arquivoLer.readLine();
				        	arquivoEscrever.write("\n\n["+ (qtdDiagramasSalvos+1) +"]");
				        	arquivoEscrever.flush();
				        	
				        	if(elementoAberto.equals("diagrama")) {
				        		arquivoEscrever.write("\n"+ elementoCompostoAtual.getNome());
					        	arquivoEscrever.flush();
				        		List<Componente> listaDeFilhos = elementoCompostoAtual.getFilhos();
								for(int i = 0; i<listaDeFilhos.size();i++) {
									Componente filhoPosicao = listaDeFilhos.get(i);
									if(filhoPosicao instanceof Classe) {
										Classe filhoDaPosicaoClasse = (Classe) filhoPosicao;
										arquivoEscrever.write("\n" + filhoDaPosicaoClasse.getNome() + "{multipClasse:" + filhoDaPosicaoClasse.getMultiplicidade() 
																+ ";navegClasse:" + filhoDaPosicaoClasse.getNavegabilidade()
																+ ";modifClasse:" + filhoDaPosicaoClasse.getModificadorDeAcesso());
							        	arquivoEscrever.flush();
							        	
							        	List<Componente> listaDeFilhosDaClasse = filhoDaPosicaoClasse.getFilhos();
							        	for(int j=0; j<listaDeFilhosDaClasse.size();j++) {
							        		Componente filhoPosicaoClasse = listaDeFilhosDaClasse.get(j);
							        		if(filhoPosicaoClasse instanceof Atributo) {
							        			Atributo filhoAtributo = (Atributo) filhoPosicaoClasse;
							        			arquivoEscrever.write(";atributo:" + filhoAtributo.getNome() + "[tipoAtr-" + filhoAtributo.getTipo()
							        									+ ",modifAtr-" + filhoAtributo.getModificadoresDeAcesso()
							        									+ "]");
							        			arquivoEscrever.flush();
							        		}
							        	}
							        	
							        	for(int k=0; k<listaDeFilhosDaClasse.size();k++) {
							        		Componente filhoPosicaoClasse = listaDeFilhosDaClasse.get(k);
							        		if(filhoPosicaoClasse instanceof Metodo) {
							        			Metodo filhoMetodo = (Metodo) filhoPosicaoClasse;
							        			arquivoEscrever.write(";metodo:" + filhoMetodo.getNome() + "[retornoMetodo-" + filhoMetodo.getTipoRetorno()
							        									+ ",modifMetodo-" + filhoMetodo.getModificadoresDeAcesso());
							        			arquivoEscrever.flush();
							        			
							        			List<Componente> listaDeFilhosDoMetodo = filhoMetodo.getFilhos();
							        			for(int l=0; l<listaDeFilhosDoMetodo.size();l++) {
							        				Componente filhoPosicaoMetodo = listaDeFilhosDoMetodo.get(l);
							        				if(filhoPosicaoMetodo instanceof Parametro) {
							        					Parametro filhoParametro = (Parametro) filhoPosicaoMetodo;
							        					if (l == listaDeFilhosDoMetodo.size()-1) {
							        						arquivoEscrever.write(",paramMetodo-" + filhoParametro.getNome() + "(tipoParam_" + filhoParametro.getTipo() + ")]");
								        					arquivoEscrever.flush();
							        					} else {
							        						arquivoEscrever.write(",paramMetodo-" + filhoParametro.getNome() + "(tipoParam_" + filhoParametro.getTipo() + ")");
								        					arquivoEscrever.flush();
							        					}
							        					
							        				}
							        			}
							        		}
							        	}
							        	
							        	arquivoEscrever.write("}");
					        			arquivoEscrever.flush();
									}
								}
								arquivoEscrever.write("\n=END");
			        			arquivoEscrever.flush();
							}
				        }
					}
		        }
			} catch (IOException ex) {
				System.out.println("Arquivo nao encontrado");
			}
			
		} catch (FileNotFoundException exc) {
			// Criar arquivo
			File file = new File("Composite/", "diagramasSalvos.txt");
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			salvarDiagrama();
		}
		
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
			        		//System.out.println("EM QUE LINHA O PROGRAMA TA: " +linhaNovamente);
			        		//System.out.println("PUTA QUE PARIU: "+elementoCompostoAtual.getNome());
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
      	  salvarDiagrama();
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
        	//DiagramasSalvos.mostrar();
        	menu();
        }
		
        else {
      	  paiDoAtual = elementoCompostoAtual;
      	  elementoAtual = atrelaElementoAoNumero.get(opcao);
      	  elementoAberto = "classe";
      	  menu();
        }
	}
	
	public void opcoesMenuClasse(int opcao) {
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
        	//DiagramasSalvos.mostrar();
        	menu();
		}
	}
	
	public void opcoesMenuTipoAtributo(int opcaoTipo, int opcaoModificador) {
		if(opcaoTipo == 1){
    		if(opcaoModificador == 1)
    			criarElemento("atributo", null, false, "byte", "Default");
    		if(opcaoModificador == 2)
    			criarElemento("atributo", null, false, "byte", "Private");
    		if(opcaoModificador == 3)
    			criarElemento("atributo", null, false, "byte", "Public");
    		if(opcaoModificador == 4)
    			criarElemento("atributo", null, false, "byte", "Protected");
    	} else if(opcaoTipo == 2) {
    		if(opcaoModificador == 1)
    			criarElemento("atributo", null, false, "short", "Default");
    		if(opcaoModificador == 2)
    			criarElemento("atributo", null, false, "short", "Private");
    		if(opcaoModificador == 3)
    			criarElemento("atributo", null, false, "short", "Public");
    		if(opcaoModificador == 4)
    			criarElemento("atributo", null, false, "short", "Protected");
    	} else if(opcaoTipo == 3) {
    		if(opcaoModificador == 1)
    			criarElemento("atributo", null, false, "int", "Default");
    		if(opcaoModificador == 2)
    			criarElemento("atributo", null, false, "int", "Private");
    		if(opcaoModificador == 3)
    			criarElemento("atributo", null, false, "int", "Public");
    		if(opcaoModificador == 4)
    			criarElemento("atributo", null, false, "int", "Protected");
    	} else if(opcaoTipo == 4) {
    		if(opcaoModificador == 1)
    			criarElemento("atributo", null, false, "long", "Default");
    		if(opcaoModificador == 2)
    			criarElemento("atributo", null, false, "long", "Private");
    		if(opcaoModificador == 3)
    			criarElemento("atributo", null, false, "long", "Public");
    		if(opcaoModificador == 4)
    			criarElemento("atributo", null, false, "long", "Protected");
    	} else if(opcaoTipo == 5) {
    		if(opcaoModificador == 1)
    			criarElemento("atributo", null, false, "float", "Default");
    		if(opcaoModificador == 2)
    			criarElemento("atributo", null, false, "float", "Private");
    		if(opcaoModificador == 3)
    			criarElemento("atributo", null, false, "float", "Public");
    		if(opcaoModificador == 4)
    			criarElemento("atributo", null, false, "float", "Protected");
    	} else if(opcaoTipo == 6) {
    		if(opcaoModificador == 1)
    			criarElemento("atributo", null, false, "double", "Default");
    		if(opcaoModificador == 2)
    			criarElemento("atributo", null, false, "double", "Private");
    		if(opcaoModificador == 3)
    			criarElemento("atributo", null, false, "double", "Public");
    		if(opcaoModificador == 4)
    			criarElemento("atributo", null, false, "double", "Protected");
    	} else if(opcaoTipo == 7) {
    		if(opcaoModificador == 1)
    			criarElemento("atributo", null, false, "boolean", "Default");
    		if(opcaoModificador == 2)
    			criarElemento("atributo", null, false, "boolean", "Private");
    		if(opcaoModificador == 3)
    			criarElemento("atributo", null, false, "boolean", "Public");
    		if(opcaoModificador == 4)
    			criarElemento("atributo", null, false, "boolean", "Protected");
    	} else if(opcaoTipo == 8) {
    		if(opcaoModificador == 1)
    			criarElemento("atributo", null, false, "char", "Default");
    		if(opcaoModificador == 2)
    			criarElemento("atributo", null, false, "char", "Private");
    		if(opcaoModificador == 3)
    			criarElemento("atributo", null, false, "char", "Public");
    		if(opcaoModificador == 4)
    			criarElemento("atributo", null, false, "char", "Protected");
    	} else if(opcaoTipo == 9) {
    		if(opcaoModificador == 1)
    			criarElemento("atributo", null, false, "String", "Default");
    		if(opcaoModificador == 2)
    			criarElemento("atributo", null, false, "String", "Private");
    		if(opcaoModificador == 3)
    			criarElemento("atributo", null, false, "String", "Public");
    		if(opcaoModificador == 4)
    			criarElemento("atributo", null, false, "String", "Protected");
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
	public static void imprimirClasses(){
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
