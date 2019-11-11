package composite;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DiagramasSalvos {
	static List<ComponenteComposto> salvos = new ArrayList<ComponenteComposto>();
	
	public static void quickSave(ComponenteComposto comp) {
		salvos.add(comp);
	}
	
	public static ComponenteComposto undo() {
		int ultimo = salvos.size() - 1;
		String tipoElemento, nomeElemento = "";
		//REMOVENDO a ultima posicao da lista
		if(salvos.get(ultimo).getFilhos().size() > 0) {
			//se tiver filhos, pegar o ultimo filho
			int tmp = salvos.get(ultimo).getFilhos().size() - 1;
			tipoElemento = salvos.get(ultimo).getFilhos().get(tmp).getClass().getSimpleName();
			nomeElemento = salvos.get(ultimo).getFilhos().get(tmp).getNome();
		}
		else {
			tipoElemento = salvos.get(ultimo).getClass().getSimpleName();
			nomeElemento = salvos.get(ultimo).getNome();
		}
		//System.out.println("\n_____"+tipo + " "+ salvos.get(ultimo).getNome()+ " removido(a)!_____\n");
		System.err.println("\n"+tipoElemento + " "+ nomeElemento+ " removido(a)!\n");
		salvos.remove(ultimo);
		if(ultimo > 0) {
			ultimo--;
			return salvos.get(ultimo);
		}
		return null;
	}
	
	public static void salvarDiagrama(String elementoAberto, ComponenteComposto elementoCompostoAtual) { 
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
			salvarDiagrama(elementoAberto, elementoCompostoAtual);
		}
		
	}
	
}